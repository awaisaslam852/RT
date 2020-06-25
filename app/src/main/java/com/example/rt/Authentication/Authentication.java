package com.example.rt.Authentication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.rt.HelperClasses.Utils;
import com.example.rt.R;
public class Authentication extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        fragmentManager = getSupportFragmentManager();


        fragmentManager.beginTransaction().replace(R.id.authentication_frame_container,
                new LoginFragment(), Utils.LoginFragment).commit();


        /*...................................................................*/


    }

    public void replaceLoginFragment() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.authentication_frame_container, new LoginFragment(),
                        Utils.LoginFragment).commit();
    }
    @Override
    public void onBackPressed() {

        Fragment SignUp_Fragment = fragmentManager.findFragmentByTag(Utils.RegisterFragment);
        Fragment ForgotPassword_Fragment = fragmentManager
                .findFragmentByTag(Utils.ForgetPassword);

//        if (SignUp_Fragment != null)
//            replaceLoginFragment();
//        else
        if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }


}
