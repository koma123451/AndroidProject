package com.example.bowen_komal_group1_comp304lab5_sec_003;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Login extends AppCompatActivity
{
    private static final String TAG = "Login Activity";
    private Nurse nurse;
    private HospitalViewModel hospitalViewModel;
    private List<Nurse> nurses;
    private EditText inputUsername, inputPassword;
    private Button loginBtn;
    private SharedPreferences preferences;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        hospitalViewModel = new ViewModelProvider(this).get(HospitalViewModel.class);

        handler = new Handler();
        inputUsername = findViewById(R.id.username_login);
        inputPassword = findViewById(R.id.password_login);
        loginBtn = findViewById(R.id.login_btn);

        preferences = getSharedPreferences("nurseId", MODE_PRIVATE);

    }

    public void login(View view)
    {
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        if (username.isEmpty())
        {
            inputUsername.setError("Please Enter your name");
        }
        else if (password.isEmpty())
        {
            inputPassword.setError("Please Enter your password");
        }
        else
        {
            try
            {
                AsyncTask.execute(() ->
                {
                    nurse = hospitalViewModel.getNurseByUsername(username);

                    if (nurse != null)
                    {
                        if (nurse.getPassword().equals(password))
                        {

                            SharedPreferences.Editor prefEditor = preferences.edit();
                            prefEditor.putInt("nurseId", nurse.getNurseId());
                            prefEditor.putString("nurseName", nurse.getFirstName());
                            prefEditor.apply();

                            Intent intent = new Intent(this, HomeActivity.class);
                            intent.putExtra("Nurse", nurse);
                            startActivity(intent);

                        }
                        else
                        {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    inputPassword.setError("Incorrect Password");

                                }
                            });

                        }
                    } else
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                inputUsername.setError("Invalid user name");

                            }
                        });
                    }

                });
            }
            catch (Error error)
            {
            }
        }
    }

//    public void showIncorrectPasswordDialog()
//    {
//        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//        builder1.setMessage("Incorrect Password. Please try again");
//        builder1.setCancelable(true);
//
//        builder1.setPositiveButton(
//                "Ok",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//
//        AlertDialog alert11 = builder1.create();
//        alert11.show();
//    }

    public void createAccountPage(View view)
    {

    }
}