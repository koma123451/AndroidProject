package com.example.bowen_komal_group1_comp304lab5_sec_003;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.MyViewHolder> {

    private ArrayList<Patient> patientsList;
    private OnCardListener mOnCardListener;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public TextView department;
        public TextView room;
        OnCardListener onCardListener;

        public MyViewHolder (View itemView, OnCardListener onCardListener){
            super(itemView);
            name = itemView.findViewById(R.id.textView_patient_name_list);
            department = itemView.findViewById(R.id.textView_patient_department_list);
            room = itemView.findViewById(R.id.textView_patient_room_list);
            this.onCardListener = onCardListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCardListener.onCardClick(getAdapterPosition());
        }
    }

    public interface OnCardListener{
        void onCardClick(int position);
    }

    public PatientListAdapter(Context context, ArrayList<Patient> patientsList, OnCardListener onCardListener)
    {
        this.patientsList = patientsList;
        this.context = context;
        this.mOnCardListener = onCardListener;
    }

    @Override
    public PatientListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_patient_list_item, parent, false);

        return new MyViewHolder(v, mOnCardListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        // replace the contents of the layout_listitem view with elements from data set
        holder.name.setText(patientsList.get(position).getFirstName());
        holder.department.setText(patientsList.get(position).getDepartment());
        holder.room.setText(patientsList.get(position).getRoom());

    }

    @Override
    public int getItemCount() {
        return patientsList.size();
    }


}
