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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.rt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class Post extends Fragment {

    private EditText emailEt ;
    private TextView tv_hint ;
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


        BannerSlider bannerSlider = (BannerSlider) view.findViewById(R.id.banner_slider1);

        List<Banner> banners=new ArrayList<>();

        //add banner using image url
        banners.add(new RemoteBanner("https://previews.123rf.com/images/tul/tul1705/tul170500028/78538164-news-vector-trendy-banner-design-concept-modern-style-with-thin-line-art-news-icons-on-trendy-colors.jpg"));
        //add banner using resource drawable
        banners.add(new DrawableBanner(R.drawable.z1));
        banners.add(new DrawableBanner(R.drawable.image_contaner));
        banners.add(new DrawableBanner(R.drawable.z1));

        bannerSlider.setBanners(banners);

    }
}
