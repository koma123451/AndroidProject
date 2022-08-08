package com.example.bowen_komal_group1_comp304lab5_sec_003;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private HospitalViewModel hospitalViewModel;
    private ArrayList<Patient> patientsList = new ArrayList<>();
    private Handler handler;

    TextView nurseGreetingText, textViewNurseDepartment, patientsNumberText, testsNumberText;
    Nurse nurse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hospitalViewModel = new ViewModelProvider(this).get(HospitalViewModel.class);
        handler = new Handler();

        nurse = (Nurse) getIntent().getSerializableExtra("Nurse");

        nurseGreetingText = findViewById(R.id.nurseGreetingText);
        textViewNurseDepartment = findViewById(R.id.textViewDepartmentPlace);
        patientsNumberText = findViewById(R.id.patientsTextView);
        testsNumberText = findViewById(R.id.testCountText);

        hospitalViewModel.getAllPatientsForNurse(nurse.getNurseId()).observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        patientsNumberText.setText(getString(R.string.nursePatientCount, patients.size()));

                    }
                });
            }
        });

        hospitalViewModel.getTestsForNurse(nurse.getNurseId()).observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        testsNumberText.setText(getString(R.string.nurseTestCount, tests.size()));

                    }
                });
            }
        });


        nurseGreetingText.setText(getResources().getString(R.string.nurseGreeting, nurse.getFirstName()));
        textViewNurseDepartment.setText(getResources().getString(R.string.nurseDepartment, nurse.getDepartment()));

    }

    public void goToPatients(View v)
    {
        Intent intent = new Intent(this, PatientActivity.class);
        intent.putExtra("Nurse", nurse);
        startActivity(intent);
    }

}