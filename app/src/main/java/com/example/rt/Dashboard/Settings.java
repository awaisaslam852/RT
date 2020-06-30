package com.example.rt.Dashboard;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rt.Authentication.Authentication;
import com.example.rt.R;
import com.google.firebase.auth.FirebaseAuth;
public class Settings extends AppCompatActivity {

    private ImageView backBtn ;
    private TextView btn1_changepass, btn2_terms, btn3_policy, btn4_others, btn5_auction, btn6_logout ;
    ///
    private FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        auth = FirebaseAuth.getInstance();

        backBtn = findViewById(R.id.back_btn_settings);
        btn1_changepass = findViewById(R.id.btn1_change_pass_settings);
        btn2_terms   = findViewById(R.id.btn2_terms_settings);
        btn3_policy  = findViewById(R.id.btn3_policy_settings);
        btn4_others  = findViewById(R.id.btn4_other_settings);
        btn5_auction = findViewById(R.id.btn5_auction_settings);
        btn6_logout  = findViewById(R.id.btn6_logout_settings);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /*...Button1 Click Listener...*/
        btn1_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, ResetPassword.class);
                startActivity(intent);
            }
        });

        /*...Button2 Click Listener...*/
        btn2_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*...Button3 Click Listener...*/
        btn3_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*...Button4 Click Listener...*/
        btn4_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*...Button5 Click Listener...*/
        btn5_auction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*...Button6 Click Listener...*/
        btn6_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // data is found
                final ProgressDialog dialog = new ProgressDialog(Settings.this);
                ((ProgressDialog) dialog).setMessage("Please Wait...");
                dialog.setCancelable(false);
                dialog.show();
                auth.signOut();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        Intent intent = new Intent(Settings.this, Authentication.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finishAffinity();
                        Toast.makeText(Settings.this, "Succeed ! Logout", Toast.LENGTH_SHORT).show();
                    }
                },2000);


            }
        });




    }
}