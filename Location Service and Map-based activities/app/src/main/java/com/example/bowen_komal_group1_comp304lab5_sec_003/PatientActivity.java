package com.example.bowen_komal_group1_comp304lab5_sec_003;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends AppCompatActivity implements PatientListAdapter.OnCardListener
{
    private HospitalViewModel hospitalViewModel;

    private PatientListAdapter patientListAdapter;

    private ArrayList<Patient> patientsList = new ArrayList<>();

    SharedPreferences preferences;
    int nurseId;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        preferences = getSharedPreferences("nurseId", Context.MODE_PRIVATE);
        nurseId = preferences.getInt("nurseId", 0);
        handler = new Handler();

        initList();
        RecyclerView recyclerView = findViewById(R.id.patientRecyclerView);
        recyclerView.setHasFixedSize(true);
        patientListAdapter = new PatientListAdapter(this, patientsList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(patientListAdapter);
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        //patientsList.clear();
//        patientListAdapter.notifyDataSetChanged();
        initList();
    }

    private void initList()
    {

        hospitalViewModel = new ViewModelProvider(this).get(HospitalViewModel.class);
        hospitalViewModel.getAllPatientsForNurse(nurseId).observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                patientsList.clear();
                patientsList.addAll(patients);
                patientListAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onCardClick(int position) {
        Log.d("LOGGER", "clicked at position: " + position);

        Intent intent = new Intent(this, PatientInfo.class);
        Patient patient = patientsList.get(position);
        intent.putExtra("Patient", patient);
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putInt("patientId", patient.getPatientsId());
        prefEditor.putString("patientName", patient.getFirstName());
        prefEditor.apply();
        startActivity(intent);
    }

    public void addNewPatient(View view)
    {
        Intent intent = new Intent(this, PatientInfo.class);
        startActivity(intent);
    }
}