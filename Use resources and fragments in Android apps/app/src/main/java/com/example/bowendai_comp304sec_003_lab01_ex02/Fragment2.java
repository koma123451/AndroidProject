package com.example.bowendai_comp304sec_003_lab01_ex02;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.TextView;

import android.widget.Toast;



import androidx.annotation.Nullable;



public class Fragment2 extends Fragment {

    TextView tv;



    @Nullable

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment2, container, false);

        Toast.makeText(getActivity(), "Fragment2 created", Toast.LENGTH_SHORT).show();

        return v;

    }



    @Override

    public void onStart() {

        super.onStart();

        Toast.makeText(getActivity(), "Fragment2 started", Toast.LENGTH_SHORT).show();

    }



    public void addMessage(String s) {

        tv = (TextView) getView().findViewById(R.id.messages);

        tv.setText(tv.getText() + "\n" + s);

    }

}