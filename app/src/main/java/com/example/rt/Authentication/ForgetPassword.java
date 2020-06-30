package com.example.rt.Authentication;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.rt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static androidx.constraintlayout.widget.Constraints.TAG;
public class ForgetPassword extends Fragment {

    private EditText emailEt ;
    private TextView tv_hint ;
    //////
    private FirebaseAuth auth ;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_forget_password, null);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        auth = FirebaseAuth.getInstance();
        initilize(rootView);

        /*..............................*/

        return rootView ;
}

    public void initilize(View view){

        emailEt = view.findViewById(R.id.et_email_forget_pass_frag);
        tv_hint = view.findViewById(R.id.tv_hint_forget_pass_frag);
        Button sentBtn = view.findViewById(R.id.sent_btn_forget_pass_frag);
        ImageView backBtn = view.findViewById(R.id.back_btn_forget_pass_frag);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity()!=null) {
                    getActivity().onBackPressed();
                }
            }
        });

        sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailEt.getText().toString().trim();
                //Email Validation pattern
                String emailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

                if (email.equals("")){
                    emailEt.setError("Enter Email");
                    Toast.makeText(getContext(), "Please Enter email address", Toast.LENGTH_SHORT).show();
                }else if (!email.matches(emailPattern)){
                    emailEt.setError("Invalid Email");
                    Toast.makeText(getContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
//                                hintTv.setVisibility(View.GONE);
//                                textView.setVisibility(View.VISIBLE);
                                emailEt.setText("");
                                tv_hint.setText("An password recovery email has sent to "+email+"\n Please check your mailbox.");
                                Toast.makeText(getContext(), "An Recovery email has sent to "+email, Toast.LENGTH_SHORT).show();
                                Toast.makeText(getContext(), email+" Please check your mailbox.", Toast.LENGTH_SHORT).show();
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }


}
