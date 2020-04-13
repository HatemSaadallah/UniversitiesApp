package com.example.universities2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.universities2.ui.login.LoginActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {
    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton simpleImageButton = (ImageButton) view.findViewById(R.id.imageView3);
        simpleImageButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(getActivity(), GazaUniversity.class);
        startActivity(i);
    }


}
