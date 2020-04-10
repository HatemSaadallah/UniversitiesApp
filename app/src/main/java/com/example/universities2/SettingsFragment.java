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

public class SettingsFragment extends Fragment {
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
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }
}
