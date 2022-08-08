package com.example.bowendai_comp304sec_003_lab01_ex02;
import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;

import android.widget.TextView;



public class VRActivity extends AppCompatActivity {

    TextView tv;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_v_r);

        tv = (TextView) findViewById(R.id.messages2);



        tv.setText(tv.getText() + "\n" + "onCreate Executed");

    }



    @Override

    protected void onStart() {

        super.onStart();

        tv.setText(tv.getText() + "\n" + "onStart Executed");

    }



    @Override

    protected void onStop() {

        super.onStop();

        tv.setText(tv.getText() + "\n" + "onStop Executed");

    }



    @Override

    protected void onDestroy() {

        super.onDestroy();

        tv.setText(tv.getText() + "\n" + "onDestroy Executed");

    }

}