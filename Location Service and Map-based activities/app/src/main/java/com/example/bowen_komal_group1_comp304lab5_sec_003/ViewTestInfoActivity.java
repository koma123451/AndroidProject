package com.example.bowen_komal_group1_comp304lab5_sec_003;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewTestInfoActivity extends AppCompatActivity
{
    SharedPreferences preferences;
    int nurseId;
    String nurseName;
    int patientId;
    String patientName;
    Nurse nurse;
    Patient patient;
    Test test;
    private HospitalViewModel hospitalViewModel;

    TextView textViewTitlePatientName, textViewNurseName, textViewBPL, textViewBPH, textViewTemperature, textViewCholesterol;
    Button addOrUpdateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);
        preferences = getSharedPreferences("nurseId", Context.MODE_PRIVATE);
        nurseId = preferences.getInt("nurseId", 0);
        patientId = preferences.getInt("patientId", 0);
        patientName = preferences.getString("patientName", "");
        nurseName = preferences.getString("nurseName", "");

        nurse = (Nurse) getIntent().getSerializableExtra("Nurse");
        patient = (Patient) getIntent().getSerializableExtra("Patient");
        test = (Test) getIntent().getSerializableExtra("Test");

        hospitalViewModel = new ViewModelProvider(this).get(HospitalViewModel.class);

        textViewTitlePatientName = findViewById(R.id.insert_test_title);
        textViewNurseName = findViewById(R.id.input_nurse_name);
        textViewBPH = findViewById(R.id.input_test_BPH);
        textViewBPL = findViewById(R.id.input_test_BPL);
        textViewTemperature = findViewById(R.id.input_test_temp);
        textViewCholesterol = findViewById(R.id.input_test_Cholesterol);
        addOrUpdateBtn = findViewById(R.id.add_or_update_test);

        textViewTitlePatientName.setText(String.format("Test info for %s", patientName));
        textViewNurseName.setText(nurseName);

        if (test == null)
        {
            addOrUpdateBtn.setText("INSERT");
            addOrUpdateBtn.setOnClickListener(this::insertTest);
        }
        else
        {
            addOrUpdateBtn.setText("UPDATE");
            addOrUpdateBtn.setOnClickListener(this::updateTest);
            textViewTemperature.setText(test.getTemperature());
            textViewBPH.setText(test.getBPH());
            textViewBPL.setText(test.getBPL());
            textViewCholesterol.setText(test.getCholesterol());
        }
    }

    public void backToPatient(View view)
    {
        finish();
    }

    public void updateTest(View view)
    {
        test.setTemperature(textViewTemperature.getText().toString());
        test.setBPH(textViewBPH.getText().toString());
        test.setBPL(textViewBPL.getText().toString());
        test.setCholesterol(textViewCholesterol.getText().toString());
        hospitalViewModel.updateTest(test);
        finish();
    }

    public void insertTest(View view)
    {
        Test newTest = new Test();

        if (textViewTemperature.getText().toString().isEmpty() &&
                textViewBPH.getText().toString().isEmpty() &&
                textViewBPL.getText().toString().isEmpty() &&
                textViewCholesterol.getText().toString().isEmpty()
        )
        {
            textViewTemperature.setError("Please, enter information");
            textViewBPH.setError("Please, enter information");
            textViewBPL.setError("Please, enter information");
            textViewCholesterol.setError("Please, enter information");
            return;
        }

        newTest.setTemperature(textViewTemperature.getText().toString());
        newTest.setBPH(textViewBPH.getText().toString());
        newTest.setBPL(textViewBPL.getText().toString());
        newTest.setCholesterol(textViewCholesterol.getText().toString());
        newTest.setPatientsId(patientId);
        newTest.setNurseId(nurseId);

        hospitalViewModel.insertTest(newTest);
        finish();
    }
}