package com.example.rt.Authentication;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.rt.HelperClasses.Utils;
import com.example.rt.MainActivity;
import com.example.rt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
public class RegisterFragment extends Fragment {
    private static FragmentManager fragmentManager;

    private EditText userNameEt, emailEt, passEt ;
    private AutoCompleteTextView autoCompleteTextView ;
    private LinearLayout loginIntentBtn, mainLinarLayoyut ;
    private RelativeLayout relativeLayout ;
    private TextView noneTv ;
    private Button registerBtn ;
    private String userType = null, cityString ;
    private ImageView backBtn ;
    private List<String> listUsername ;
    /*......*/
    private FirebaseAuth auth ;
    private DatabaseReference refRegister, refUsername ;
    private ValueEventListener listener ;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_register_fragment, null);
        fragmentManager = getActivity().getSupportFragmentManager();
        auth = FirebaseAuth.getInstance();

//        initilize(rootView);
//        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector_login);
//        try {
//            ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
//            noneTv.setTextColor(csl);
//        } catch (Exception e) {
//        }
        /*..................*/
        return rootView ;
    }

    public void initilize(View view){

        userNameEt  = view.findViewById(R.id.et1_name_register_frag);
        emailEt     = view.findViewById(R.id.et2_email_register_frag);
        passEt      = view.findViewById(R.id.et3_pass_register_frag);
        noneTv      = view.findViewById(R.id.et4_cnfrm_pass_register_frag);
//        loginIntentBtn = view.findViewById(R.id.login_btn_intent_register);
        relativeLayout = view.findViewById(R.id.relative_bg_register);
//        backBtn     = view.findViewById(R.id.back_btn_register_fragment);
        registerBtn      = view.findViewById(R.id.signup_btn_register_frag);
//        mainLinarLayoyut = view.findViewById(R.id.linear_main_register_fragment);
        mainLinarLayoyut.requestFocus();


        listUsername = new ArrayList<>();

        if (getActivity()!=null) {
//            ArrayAdapter<String> adapterio = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mlist);
//            autoCompleteTextView.setAdapter(adapterio);
        }

        loginIntentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                        .replace(R.id.authentication_frame_container, new LoginFragment(),
                                Utils.LoginFragment).commit();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity()!=null) {
                    getActivity().onBackPressed();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerMethod();
            }
        });


    }
    /*....OnStart Method.....*/

    @Override
    public void onStart() {
        super.onStart();



    }

    /*...........Register Method Student.........*/
    private void registerMethod() {

        final String username   = userNameEt.getText().toString().toLowerCase().trim();
        final String  email     = emailEt.getText().toString().trim();
        final String  city      = autoCompleteTextView.getText().toString().trim();
        final String  password  = passEt.getText().toString().trim();
        //Email Validation pattern
        String emailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

        if (TextUtils.isEmpty(username) || username.length()<6) {
            userNameEt.setError("Invalid username");
        } else if (listUsername.contains(username)) {
            userNameEt.setError("Username already exist");
            Toast.makeText(getContext(), "Username already exist.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)) {
            emailEt.setError("Email Address");
        }else if (!email.matches(emailPattern)) {
            emailEt.setError("Email is not valid");
        } else if (TextUtils.isEmpty(password) || password.length()<6) {
            passEt.setError("Invalid Password");
        } else if (TextUtils.isEmpty(city)) {
            autoCompleteTextView.setError("Select City");
        }
        else if (userType==null) {
            Toast.makeText(getContext(), "Please Select UserType.", Toast.LENGTH_SHORT).show();
        }
        else {

            /*....*/
            // data is found
            final Dialog getDialog = new ProgressDialog(getContext());
            ((ProgressDialog) getDialog).setMessage("Loading Please wait...");
            getDialog.show();
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                createDatabase(username, email, city);
//                                sendEmailVerification(username, email);
                                getDialog.dismiss();
                            } else {
                                // If sign up fails, display a message to the user.
                                getDialog.dismiss();
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        }/////

    }
    //    ==============================================================================================
    private void sendEmailVerification(final String username, final String email, final String city) {
        final FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                createDatabase(username, email, city);
                                Toast.makeText(getContext(), "Verification email sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Log.e(TAG, "sendEmailVerification", task.getException());
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }
    ////////////////////////////////////////////////////////////////////////
//    ---------- Create database ----------
    private void createDatabase(String username, String email, String city) {

        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userid = firebaseUser.getUid();

        Long currentTime = System.currentTimeMillis(); //getting current time in millis
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime);
        String showTime = String.format("%1$tI:%1$tM:%1$tS %1$Tp" +" ", cal);
        Date now = new Date();
        long timestamp = now.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String dateStr = sdf.format(timestamp);
        String dateStamp = showTime + dateStr;

        if (userType!=null){
            if (userType.equals("Student")){
                /*............. Database Student ..............**/
                refRegister = FirebaseDatabase.getInstance().getReference("Student_Accounts").child(userid).child("Profile_Info");
                final DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("All_Users_List").child(userid);
//                final UsersData data = new UsersData(
//                        userid,
//                        dateStamp,
//                        username,
//                        "",
//                        email,
//                        "",
//                        city,
//                        "",
//                        "",
//                        username.toLowerCase(),
//                        "Student",
//                        "no",
//                        "",
//                        0,
//                        0,
//
//                        "",
//                        ""
//                );
                HashMap data = new HashMap();
                refRegister.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            refUsername.removeEventListener(listener);
                            auth.signOut();
                            emailEt.setText("");
                            passEt.setText("");
                            userNameEt.setText("");
                            autoCompleteTextView.setText("");
                            Toast.makeText(getContext(), "Succeed ! Account created", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent( getActivity(), MainActivity.class );
                            startActivity(intent);
                            getActivity().finish();
                            System.out.println("Database of Student created 0000000000000000000000000000000000");
                        } else {
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                /*............................................*/
            }

        }

    }

    //==============================================================================================



}
