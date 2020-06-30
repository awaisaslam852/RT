package com.example.rt.Dashboard;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static androidx.constraintlayout.widget.Constraints.TAG;
public class ResetPassword extends AppCompatActivity {

    private EditText et1_pass, et2_newpass, et3_cnfrmpass ;
    //    private TextView tv_hint ;
    ///
    private FirebaseAuth auth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        et1_pass      = findViewById(R.id.et1_crnt_pass_reset_password);
        et2_newpass   = findViewById(R.id.et2_new_pass_reset_password);
        et3_cnfrmpass = findViewById(R.id.et3_cnfrm_pass_reset_password);
        Button submitBtn = findViewById(R.id.submit_btn_reset_password);
        ImageView backBtn = findViewById(R.id.back_btn_reset_password);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.

// Prompt the user to re-provide their sign-in credentials


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current_passs = et1_pass.getText().toString().trim();
                String new_passs     = et2_newpass.getText().toString().trim();
                final String cnfrm_passs   = et3_cnfrmpass.getText().toString().trim();

                if (TextUtils.isEmpty(current_passs)){
                    et1_pass.setError("Enter current Password");
                }else if (TextUtils.isEmpty(new_passs)){
                    et2_newpass.setError("Enter new Password");
                }else if (TextUtils.isEmpty(cnfrm_passs)) {
                    et3_cnfrmpass.setError("Retype Password");
                }else if (!cnfrm_passs.equals(new_passs)){
                    et3_cnfrmpass.setError("Password not matched");
                    et2_newpass.setError("Password not matched");
                    Toast.makeText(ResetPassword.this, "Failed ! Password not matched. Try again", Toast.LENGTH_SHORT).show();
                }
                else {
                    // data is found
                    final ProgressDialog dialog = new ProgressDialog(ResetPassword.this);
                    ((ProgressDialog) dialog).setMessage("Please Wait...");
                    dialog.setCancelable(false);
                    dialog.show();

                    AuthCredential credential = EmailAuthProvider
                            .getCredential(user.getEmail(), current_passs);
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        user.updatePassword(cnfrm_passs).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "Password updated");
                                                    et1_pass.setText("");
                                                    et2_newpass.setText("");
                                                    et3_cnfrmpass.setText("");
                                                    dialog.dismiss();
                                                    Toast.makeText(ResetPassword.this, "Succeed ! Password Updated", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    dialog.dismiss();
                                                    Log.d(TAG, "Error password not updated");
                                                    Toast.makeText(ResetPassword.this, "Failed ! may some network Issue.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    } else {
                                        dialog.dismiss();
                                        Log.d(TAG, "Error auth failed");
                                    }
                                }
                            });
                }
            }
        });

    }
}