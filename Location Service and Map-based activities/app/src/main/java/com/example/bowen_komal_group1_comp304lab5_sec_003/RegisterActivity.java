package com.example.bowen_komal_group1_comp304lab5_sec_003;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class RegisterActivity extends AppCompatActivity
{
    private static final String TAG = "Register Activity";
    private HospitalViewModel hospitalViewModel;
    private Button createAccount, backToLoginPage;
    private EditText editTextNurseUsername, editTextNurseName, editTextNurseLastName,
            editTextNurseDepartment, editTextNursePassword;
    Nurse nurse;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        handler = new Handler();

        createAccount = findViewById(R.id.login_btn);
        hospitalViewModel = new ViewModelProvider(this).get(HospitalViewModel.class);
        nurse = new Nurse();

        editTextNurseUsername = findViewById(R.id.username_login);
        editTextNurseName = findViewById(R.id.firstName_input);
        editTextNurseLastName = findViewById(R.id.lastName_input);
        editTextNurseDepartment = findViewById(R.id.department_input);
        editTextNursePassword = findViewById(R.id.password_login);
    }

    public void insertNurse(View view)
    {

        if (editTextNurseUsername.getText().toString().isEmpty())
        {
            editTextNurseUsername.setError("username required");
            return;
        }

        if (editTextNurseName.getText().toString().isEmpty())
        {
            editTextNurseName.setError("First name required");
            return;
        }

        if (editTextNurseLastName.getText().toString().isEmpty())
        {
            editTextNurseLastName.setError("First name required");
            return;
        }

        if (editTextNurseDepartment.getText().toString().isEmpty())
        {
            editTextNurseDepartment.setError("Department required");
            return;
        }

        if (editTextNursePassword.getText().toString().isEmpty())
        {
            editTextNursePassword.setError("Password required");
            return;
        }


        try {

            AsyncTask.execute(() ->
                    {
                        Nurse newNurse = hospitalViewModel.getNurseByUsername(editTextNurseUsername.getText().toString());
                        if (newNurse != null)
                        {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    editTextNurseUsername.setError("Username already exists");

                                }
                            });

                            return;
                        } else {

                            nurse.setUsername(editTextNurseUsername.getText().toString());
                            nurse.setFirstName(editTextNurseName.getText().toString());
                            nurse.setLastName(editTextNurseLastName.getText().toString());
                            nurse.setDepartment(editTextNurseDepartment.getText().toString());
                            nurse.setPassword(editTextNursePassword.getText().toString());

                            hospitalViewModel.insertNurse(nurse);
                            finish();


                        }
                    }

            );

        } catch (Error error)
        {
            Log.d(TAG, "Error -> " + error.getMessage());

        }


    }

    public void backToMainPage(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}