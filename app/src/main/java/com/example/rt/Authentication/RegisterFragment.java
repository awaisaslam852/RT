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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.example.rt.ModelClasses.StaffData;
import com.example.rt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private EditText et1_fullname, et2_email, et3_pass, et4_cnfrmpass ;
    private TextView tv_terms, tv_policy, loginIntentBtn ;
    private Button registerBtn ;
    private ImageView backBtn ;
    private CheckBox checkBox ;
    /*......*/
    private FirebaseAuth auth ;
    private DatabaseReference refRegister;
    private ValueEventListener listener ;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_register_fragment, null);
        fragmentManager = getActivity().getSupportFragmentManager();
        auth = FirebaseAuth.getInstance();

        initilize(rootView);
        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector_login);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
            loginIntentBtn.setTextColor(csl);
            tv_terms.setTextColor(csl);
            tv_policy.setTextColor(csl);
        } catch (Exception e) {
        }
        /*..................*/
        return rootView ;
    }

    public void initilize(View view){

        et1_fullname  = view.findViewById(R.id.et1_name_register_frag);
        et2_email     = view.findViewById(R.id.et2_email_register_frag);
        et3_pass      = view.findViewById(R.id.et3_pass_register_frag);
        et4_cnfrmpass = view.findViewById(R.id.et4_cnfrm_pass_register_frag);

        tv_terms = view.findViewById(R.id.tv_terms_register_frag);
        tv_policy     = view.findViewById(R.id.tv_policy_register_frag);
        loginIntentBtn = view.findViewById(R.id.login_btn_register_frag);
        registerBtn      = view.findViewById(R.id.signup_btn_register_frag);
        backBtn = view.findViewById(R.id.back_btn_register_frag);
        checkBox = view.findViewById(R.id.checkbox_id_register_frag);

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

        final String fullname       = et1_fullname.getText().toString().toLowerCase().trim();
        final String  email         = et2_email.getText().toString().trim();
        final String  password      = et3_pass.getText().toString().trim();
        final String  cnfrmPassword = et4_cnfrmpass.getText().toString().trim();
        //Email Validation pattern
        String emailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

        if (TextUtils.isEmpty(fullname) || fullname.length()<6) {
            et1_fullname.setError("Invalid username");
        } else if (TextUtils.isEmpty(email)) {
            et2_email.setError("Email Address");
        }else if (!email.matches(emailPattern)) {
            et2_email.setError("Email is not valid");
        } else if (TextUtils.isEmpty(password) || password.length()<6) {
            et3_pass.setError("Invalid Password");
        } else if (!cnfrmPassword.equals(password)) {
            et4_cnfrmpass.setError("Password not matched");
        } else if (!checkBox.isChecked()) {
            Toast.makeText(getContext(), "Please click on checkbox to accept Terms & Conditions.", Toast.LENGTH_SHORT).show();
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
                                createDatabase(fullname, email);
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
    private void sendEmailVerification(final String username, final String email) {
        final FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                createDatabase(username, email);
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
    private void createDatabase(String fullname, String email) {

        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userUid = firebaseUser.getUid();

        Long currentTime = System.currentTimeMillis(); //getting current time in millis
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime);
        String showTime = String.format("%1$tI:%1$tM:%1$tS %1$Tp" +" ", cal);
        Date now = new Date();
        long timestamp = now.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String dateStr = sdf.format(timestamp);
        String dateStamp = showTime + dateStr;

                /*............. Database Staff ..............**/
                refRegister = FirebaseDatabase.getInstance().getReference("Staff_Accounts").child(userUid).child("Profile_Info");
                final DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("All_Users_List").child(userUid);
                final StaffData data = new StaffData(
                        userUid,
                        dateStamp,
                        ""+fullname,
                        ""+email,
                        "",
                        "",
                        "",
                        "",
                        ""+fullname.toLowerCase(),
                        "staff",
                        "no",
                        "",
                        "",
                        ""
                );
                refRegister.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            ref2.setValue(data);
                            et1_fullname.setText("");
                            et2_email.setText("");
                            et3_pass.setText("");
                            et4_cnfrmpass.setText("");
                            Toast.makeText(getContext(), "Succeed ! Account created", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent( getActivity(), MainActivity.class );
                            startActivity(intent);
                            getActivity().finish();
                            System.out.println("Database of Staff created -=0000000000000000000000000000000000");
                        } else {
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                /*............................................*/

    }

    //==============================================================================================



}
