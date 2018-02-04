package com.example.babimaji.stayintouch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFrag extends Fragment {

    private ImageView picture;
    private TextView userName;
    private TextView preferredContact;
    private TextView userMantra;
    private View rootview;


    public ProfileFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_profile, container, false);
        picture = rootview.findViewById(R.id.picture);
        userName = rootview.findViewById(R.id.userName);
        preferredContact = rootview.findViewById(R.id.preferred_contact_tv);
        userMantra = rootview.findViewById(R.id.mantra);

        Bundle bundle = new Bundle();



        return rootview;

    }

}
