package com.example.bowendai_comp304sec_003_lab01_ex02;
import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;



public class MainActivity extends AppCompatActivity {



    Fragment2 fragment2;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        Fragment1 fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1);

        fragment2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2);



        fragment2.addMessage("onCreate Executed");

    }



    @Override

    protected void onStart() {

        super.onStart();

        fragment2.addMessage("onStart Executed");

    }



    @Override

    protected void onStop() {

        super.onStop();

        fragment2.addMessage("onStop Executed");

    }



    @Override

    protected void onDestroy() {

        super.onDestroy();

        fragment2.addMessage("onDestroy Executed");

    }

}