package com.example.bowendai_comp304sec_003_lab01_ex02;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.content.Intent;



import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.Toast;



import androidx.annotation.Nullable;



public class Fragment1 extends Fragment {



    @Nullable

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment1, container, false);

        Toast.makeText(getActivity(), "Fragment1 created", Toast.LENGTH_SHORT).show();

        return v;

    }



    @Override

    public void onStart() {

        super.onStart();



        String[] items = getResources().getStringArray(R.array.items);



        ArrayAdapter<String> adapter = (new ArrayAdapter<String>(getActivity(), R.layout.activity__, items));



        ListView listView = (ListView) getView().findViewById(R.id.listView);

        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,

                                    int position, long id) {

                if(position == 0) {

                    Intent i = new Intent(getActivity().getApplicationContext(), AIActivity.class);

                    startActivity(i);

                } else {

                    Intent i = new Intent(getActivity().getApplicationContext(), VRActivity.class);

                    startActivity(i);

                }

            }

        });



        Toast.makeText(getActivity(), "Fragment1 started", Toast.LENGTH_SHORT).show();

    }

}