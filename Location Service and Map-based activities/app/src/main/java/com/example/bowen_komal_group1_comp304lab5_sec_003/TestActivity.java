package com.example.bowen_komal_group1_comp304lab5_sec_003;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements TestListAdapter.OnCardListener
{
    private HospitalViewModel hospitalViewModel;

    private TestListAdapter testListAdapter;

    private ArrayList<Test> testsList = new ArrayList<>();

    SharedPreferences preferences;
    int nurseId;
    int patientId;

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        preferences = getSharedPreferences("nurseId", Context.MODE_PRIVATE);
        nurseId = preferences.getInt("nurseId", 0);
        patientId = preferences.getInt("patientId", 0);
        handler = new Handler();

        initList();
        RecyclerView recyclerView = findViewById(R.id.testViewRecyclerView);
        recyclerView.setHasFixedSize(true);
        testListAdapter = new TestListAdapter(this, testsList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(testListAdapter);
    }
    private void initList()
    {
        hospitalViewModel = new ViewModelProvider(this).get(HospitalViewModel.class);
        hospitalViewModel.getTestsByPatientByNurse(nurseId, patientId).observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                testsList.clear();
                testsList.addAll(tests);
                testListAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        initList();
    }


    @Override
    public void onCardClick(int position) {
        Log.d("LOGGER", "clicked at position: " + position);

        Intent intent = new Intent(this, ViewTestInfoActivity.class);
        Test test = testsList.get(position);
        intent.putExtra("Test", test);
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putInt("testId", test.getTestId());
        prefEditor.apply();
        startActivity(intent);
    }

    public void addNewTest(View view)
    {
        Intent intent = new Intent(this, ViewTestInfoActivity.class);
        startActivity(intent);
    }
}