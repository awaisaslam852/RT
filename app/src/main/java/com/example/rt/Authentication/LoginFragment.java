package com.example.rt.Authentication;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import com.example.rt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;
public class LoginFragment extends Fragment {
    private static FragmentManager fragmentManager ;

    private EditText emailEt, passEt ;
    private TextView forgetPassBtn, registerIntntBtn ;
    /*....*/
    private FirebaseAuth auth ;
    private DatabaseReference refLogin ;
    private ValueEventListener listener ;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_login_fragment, null) ;
        fragmentManager = getActivity().getSupportFragmentManager();
        auth = FirebaseAuth.getInstance();

        initilize(rootView);

        @SuppressLint("ResourceType")
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector_login);
        try {
            final ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
//            forgetPassBtn.setTextColor(csl);
            registerIntntBtn.setTextColor(csl);
        } catch (Exception e) { }
        /*......................................*/
        return rootView ;
    }
    public void initilize(View view){

        emailEt = view.findViewById(R.id.et1_email_login_fragment);
        passEt  = view.findViewById(R.id.et2_pass_login_fragment);
//        tvNone  = view.findViewById(R.id.tv_none_login);
        forgetPassBtn    = view.findViewById(R.id.forget_pass_btn_login_fragment);
        registerIntntBtn = view.findViewById(R.id.register_btn_login_fragment);
        Button loginBtn = view.findViewById(R.id.signin_btn_login_fragment);
//        ImageView backBtn = view.findViewById(R.id.back_btn_login_fragment);


        forgetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.authentication_frame_container, new ForgetPassword(),
                                Utils.ForgetPassword).commit();
            }
        });
        registerIntntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.authentication_frame_container, new RegisterFragment(),
                                Utils.RegisterFragment).commit();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinMethod();
            }
        });
    }

    /*......Login Method Main......*/
    public void signinMethod() {
        String email = emailEt.getText().toString();
        String password = passEt.getText().toString();
        //Email Validation pattern
        String emailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

        if (TextUtils.isEmpty(email)) {
            emailEt.setError("Email ?");
        }else if (!email.matches(emailPattern)) {
            emailEt.setError("Invalid Email");
        } else if (TextUtils.isEmpty(password) || password.length()<6) {
            passEt.setError("Invalid Password");
        }
        else {
            // data is found
            final ProgressDialog dialog = new ProgressDialog(getContext());
            ((ProgressDialog) dialog).setMessage("Please Wait...");
            dialog.show();
            auth.signInWithEmailAndPassword(email , password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//

                            }
                            else {
                                dialog.dismiss();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }


                            // ...
                        }
                    });
        }
    }

    /*...Login Student...*/
    private void loginMethod(String userUid){
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Student_Accounts").child(userUid).child("Profile_Info");
        final DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("All_Users_List").child(userUid);
        HashMap map = new HashMap();
        map.put("isVerified", "Yes");
        ref1.updateChildren(map);
        ref2.updateChildren(map);
        ////////////////////////////////////////////////////
        int aa = 1111;
//        Intent intent = new Intent(getActivity(), Dashboard.class);
//        intent.putExtra("on", aa);
//        refLogin.removeEventListener(listener);
//        intent.putExtra("userType", "Student");
//        intent.putExtra("showRipple", "true");
//        intent.putExtra("userid", userUid);
//        startActivity(intent);
//        getActivity().finish();
    }



    @Override
    public void onStop() {
        super.onStop();
        if (auth.getCurrentUser()!=null) {
            refLogin.removeEventListener(listener);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (auth.getCurrentUser()!=null) {
            refLogin.removeEventListener(listener);
        }
    }

    /*.................................*/

}


// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
// intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);