package com.example.bowendai_comp304sec003_lab01_ex01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity  {
    public static final String input1="input";
    private TextView tx;
    private String txx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tx=findViewById(R.id.txtview);
        Intent i=getIntent();
        txx=i.getStringExtra(input1);
        tx.setText(txx);



    }
}