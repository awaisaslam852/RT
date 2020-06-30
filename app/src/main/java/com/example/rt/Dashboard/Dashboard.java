package com.example.rt.Dashboard;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rt.Dashboard.Fragments.Feed;
import com.example.rt.Dashboard.Fragments.Post;
import com.example.rt.Dashboard.Fragments.Profile;
import com.example.rt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    BottomNavigationView navigation ;
//    private TextView tv_heading ;
    //////
    private FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        auth = FirebaseAuth.getInstance();


        navigation = findViewById(R.id.bottom_navigation_id);
//        tv_heading = findViewById(R.id.tv_title_dashboard);

        loadFragment(new Feed());

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null ;
                switch (menuItem.getItemId()){
                    case R.id.f1:
                        fragment = new Feed();
//                        tv_heading.setText("Feed");
                        loadFragment(fragment);
                        break;
                    case R.id.f2:
                        fragment = new Post();
//                        tv_heading.setText("Post");
                        loadFragment(fragment);
                        break;
                    case R.id.f3:
                        fragment = new Profile();
//                        tv_heading.setText("Profile");
                        loadFragment(fragment);
                        break;
                }
                return true;
            }      });




    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
            return true;
        }
        return false ;
    }

    Long firstClick = 1L;
    Long secondClick = 0L;

    @Override
    public void onBackPressed() {
        secondClick = System.currentTimeMillis();
        if ((secondClick - firstClick) / 1000 < 2) {
            super.onBackPressed();
        } else {
            firstClick = System.currentTimeMillis();
            Toast.makeText(Dashboard.this, "Touch again to exit", Toast.LENGTH_SHORT).show();
        }
    }
    /*...................*/
}