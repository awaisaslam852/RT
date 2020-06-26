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

public class Profile extends Fragment {

    private EditText emailEt ;
    private TextView tv_hint ;
    ///
    private FirebaseAuth auth ;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_profile_fragment, null);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        auth = FirebaseAuth.getInstance();
        initilize(rootView);

        /*..............................*/
        return rootView ;
}

    public void initilize(View view) {


    }
}
