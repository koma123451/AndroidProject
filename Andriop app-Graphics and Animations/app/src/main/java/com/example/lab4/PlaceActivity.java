package com.example.lab4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_activity);
        this.getSupportActionBar().setTitle("Landmarks");

        RecyclerView recyclerView = findViewById(R.id.placesRecicleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] landmarks = getResources().getStringArray(R.array.landmark_names);
        SharedPreferences sharedpreferences = getSharedPreferences("options", MODE_PRIVATE);
        String type = sharedpreferences.getString("type_selected","");
        TextView typeTexView = findViewById(R.id.typeText);
        typeTexView.setText(type);

        List<String> filtered = new ArrayList();
        for (String item:landmarks) {
            String[] info  = item.split("#");
            if(info[info.length - 1].equals(type)) {
                filtered.add(item);
            }
        }
        PlacesAdapter placesAdapter = new PlacesAdapter(getApplicationContext(), filtered.toArray(new String[filtered.size()]));
        recyclerView.setAdapter(placesAdapter);
    }

}
