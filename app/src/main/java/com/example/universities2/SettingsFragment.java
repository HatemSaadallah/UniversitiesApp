package com.example.universities2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.universities2.ui.login.LoginActivity;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    public SettingsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        final Button button = getActivity().findViewById(R.id.logout);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(getActivity().getApplicationContext(), LoginActivity.class);
//                startActivity(i);
////                Toast.makeText(getActivity().getApplicationContext(),"تم تسجيل الخروج بنجاح!", Toast.LENGTH_LONG).show();
//            }
//        });
        View view;
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button shareButton = (Button) view.findViewById(R.id.share);
        shareButton.setOnClickListener(this);

        Button logOut = (Button) view.findViewById(R.id.logout);
        logOut.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Mohammed's Project");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "www.google.com");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                break;
            case R.id.logout:
                Toast.makeText(getActivity(), "تم تسجيل الخروج بنجاح", Toast.LENGTH_LONG).show();
                Intent i=new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
        }
    }
}
