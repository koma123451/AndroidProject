package com.example.lab4;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.MyViewHolder>
{
    private Context context;
    private String[] places;
    private List<String> placeName = new ArrayList<>();
    private List<String> placeAddress = new ArrayList<>();
    private List<String> latitude = new ArrayList<>();
    private List<String> longitude = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    public PlacesAdapter(Context context, String[] places)
    {
        this.context = context;
        this.places = places;
        this.sharedPreferences = context.getSharedPreferences("options", 0);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView placeNameTextView;
        private TextView placeAddressTextView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            placeNameTextView = itemView.findViewById(R.id.recycler_item_place_name);
            placeAddressTextView = itemView.findViewById(R.id.recycler_item_place_address);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        for (String item:places)
        {
            String[] temp  = item.split("#");
            placeName.add(temp[0]);
            placeAddress.add(temp[1]);
            latitude.add(temp[2]);
            longitude.add(temp[3]);
        }
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = new View(context);
        listItem = inflater.inflate(R.layout.recycler_item_place, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        holder.placeNameTextView.setText(placeName.get(position));
        holder.placeAddressTextView.setText(placeAddress.get(position));
        holder.itemView.setOnClickListener(v ->
        {
            Intent intent = new Intent(context.getApplicationContext(), MapsActivity.class).setFlags(FLAG_ACTIVITY_NEW_TASK); // todo change to map acticity
            editor.putString("place_selected", places[position]);
            //editor.putFloat("place_lat", Float.parseFloat(String.valueOf(latitude)));
            //editor.putFloat("place_lon", Float.parseFloat(String.valueOf(longitude)));
            editor.apply();
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount()
    {
        return places.length;
    }
}
