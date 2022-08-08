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

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class TypesAdapter extends RecyclerView.Adapter<TypesAdapter.MyViewHolder>
{
    private Context context;
    private String[] types;
    private SharedPreferences sharedPreferences;

    public TypesAdapter(Context context, String[] types)
    {
        this.context = context;
        this.types = types;
        this.sharedPreferences = context.getSharedPreferences("options", 0);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView type;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            type = itemView.findViewById(R.id.recycler_item_name);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = new View(context);
        listItem = inflater.inflate(R.layout.recycler_item_types, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        holder.type.setText(types[position]);
        holder.itemView.setOnClickListener(v ->
        {
            Intent intent = new Intent(context.getApplicationContext(), PlaceActivity.class).setFlags(FLAG_ACTIVITY_NEW_TASK);
            editor.putString("type_selected", types[position]);
            editor.apply();
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount()
    {
        return types.length;
    }
}
