package com.tutorials.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    ProfileData profileData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        TextView name = view.findViewById(R.id.name);
        TextView occupation = view.findViewById(R.id.occupation);
        TextView description = view.findViewById(R.id.description);
        TextView age = view.findViewById(R.id.age);


        if(profileData != null){
            name.setText(this.profileData.getName());
            occupation.setText(this.profileData.getOccupation());
            description.setText(this.profileData.getDescription());
            age.setText("" + this.profileData.getAge());
        }

        return view;
    }

    public void setProfileData (ProfileData profileData){
        this.profileData = profileData;
    }
}