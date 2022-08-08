package com.example.bowendai_comp304sec003_lab01_ex01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView txt;
    private Button bt;
    private String input;


    public TextView getTxt() {
        return txt;
    }

    public void setTxt(TextView txt) {
        this.txt = txt;
    }

    public Button getBt() {
        return bt;
    }

    public void setBt(Button bt) {
        this.bt = bt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt=findViewById(R.id.txtview);
         bt=findViewById(R.id.btn);
         bt.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        openActivity2();


    }
    public void openActivity2(){
        input=txt.getText().toString().trim();


        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtra(MainActivity2.input1,input);

        startActivity(intent);

    }
}