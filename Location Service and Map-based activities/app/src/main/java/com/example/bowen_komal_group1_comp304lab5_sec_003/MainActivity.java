package com.example.bowen_komal_group1_comp304lab5_sec_003;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "Main Activity";
    private HospitalViewModel hospitalViewModel;
    private Button btnInsertPatient, goToRegister;
    private EditText editTextPatientId, editTextPatientFirstName, editTextPatientLastName,
            editTextPatientDepartment, editTextPatientRoom;
    private EditText editTextNurseId, editTextNurseName;
    private TextView textViewDisplayPatient, textViewDisplayNurse;
    Patient patient;
    Nurse nurse;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //writeIntoDatabase();

        //textViewDisplayPatient = findViewById(R.id.textViewAllPatients);
        textViewDisplayNurse = findViewById(R.id.textViewAllNurses);
        goToRegister = findViewById(R.id.goToRegister);
        hospitalViewModel = new ViewModelProvider(this).get(HospitalViewModel.class);
//        patient = new Patient();
        nurse = new Nurse();
//
        hospitalViewModel.getAllNurses().observe(this, nurses ->
        {
            String output = "Nurses:\n";
            for (Nurse nurse : nurses)
            {
                output += "username: " + nurse.getUsername() + " | password: " + nurse.getPassword() + "\n";
            }
            textViewDisplayNurse.setText(output);
        });

    }

    public void goToRegister(View view)
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void goToLogin(View view)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void insertPatient(View view)
    {
//        editTextPatientId = findViewById(R.id.editTextPatientId);
//        editTextPatientFirstName = findViewById(R.id.editTextPatientFirstName);
//        editTextPatientLastName = findViewById(R.id.editTextPatientLastName);
//        editTextPatientDepartment = findViewById(R.id.editTextPatientDepartment);
//        editTextPatientRoom = findViewById(R.id.editTextPatientRoom);
//        editTextNurseId = findViewById(R.id.editTextNurseId);

//        patient.setPatientsId(Integer.parseInt(editTextPatientId.getText().toString()));
//        patient.setFirstName(editTextPatientFirstName.getText().toString());
//        patient.setLastName(editTextPatientLastName.getText().toString());
//        patient.setDepartment(editTextPatientDepartment.getText().toString());
//        patient.setRoom(editTextPatientRoom.getText().toString());
//        patient.setNurseId(Integer.parseInt(editTextNurseId.getText().toString()));
//
//        patientViewModel.insertPatient(patient);
        //patients.child(String.valueOf(patient.getPatientsId())).setValue(patient);

    }

}