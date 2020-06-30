package com.example.rt.Dashboard.Fragments;

import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.rt.Dashboard.Settings;
import com.example.rt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends Fragment {

    private TextView title_tv ;
    private ImageView iv_profile, settings_btn ;
    private RecyclerView recyclerView ;
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

        title_tv = view.findViewById(R.id.tv_title_profile_frag);
        iv_profile = view.findViewById(R.id.iv_profile_frag);
        settings_btn = view.findViewById(R.id.settings_btn_profile_frag);
        recyclerView = view.findViewById(R.id.recyclerview_id_profile_frag);

        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Settings.class);
                startActivity(intent);
            }
        });

    }
}
