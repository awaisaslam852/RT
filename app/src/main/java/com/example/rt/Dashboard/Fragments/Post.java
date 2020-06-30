package com.example.rt.Dashboard.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.rt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;
public class Post extends Fragment {

    private CardView add_file_btn ;
    private PowerSpinnerView spinner_states, spinner_country ;
    private EditText et_content ;
    private Button post_btn ;
    ///
    private FirebaseAuth auth ;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_post_fragment, null);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        auth = FirebaseAuth.getInstance();

        initilize(rootView);

        /*..............................*/
        return rootView ;
}

    public void initilize(View view) {
        add_file_btn    = view.findViewById(R.id.add_gallary_btn_post_frag);
        spinner_states  = view.findViewById(R.id.spinner_states_post_frag);
        spinner_country = view.findViewById(R.id.spinner_country_post_frag);
        et_content  = view.findViewById(R.id.et_content_post_frag);
        post_btn    = view.findViewById(R.id.post_btn_post_frag);

        add_file_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /*...........*/



    }
}
