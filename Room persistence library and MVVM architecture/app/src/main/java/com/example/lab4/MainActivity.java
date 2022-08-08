package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getSupportActionBar().setTitle("Places of Toronto");

        RecyclerView listOfTypesView = findViewById(R.id.list_of_types);
        listOfTypesView.setLayoutManager(new LinearLayoutManager(this));

        String[] types = getResources().getStringArray(R.array.landmark_types);
        TypesAdapter typesAdapter = new TypesAdapter(getApplicationContext(), types);
        listOfTypesView.setAdapter(typesAdapter);
    }
}