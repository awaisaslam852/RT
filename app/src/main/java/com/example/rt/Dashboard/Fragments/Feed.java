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

import com.example.rt.HelperClasses.SliderAdapterExample;
import com.example.rt.ModelClasses.SliderItem;
import com.example.rt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
public class Feed extends Fragment {

    private EditText emailEt ;
    private TextView tv_hint ;
    private SliderView sliderView ;
    ///
    private FirebaseAuth auth ;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_feed_fragment, null);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        auth = FirebaseAuth.getInstance();
        initilize(rootView);



        /*..............................*/
        return rootView ;
}

    public void initilize(View view){

        sliderView = view.findViewById(R.id.imageSlider);


        SliderAdapterExample sliderAdapterExample = new SliderAdapterExample(getContext());

        sliderView.setSliderAdapter(sliderAdapterExample);
        sliderView.setIndicatorEnabled(true);
        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

        SliderItem item = new SliderItem();

        item.setDescription("Slider Item Added Manually");

        item.setImageUrl("https://previews.123rf.com/images/tul/tul1705/tul170500028/78538164-news-vector-trendy-banner-design-concept-modern-style-with-thin-line-art-news-icons-on-trendy-colors.jpg");
//        item.setImageUrl("");
        sliderAdapterExample.addItem(item);


    }

}
