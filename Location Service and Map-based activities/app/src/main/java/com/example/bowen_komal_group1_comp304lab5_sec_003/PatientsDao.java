package com.example.bowen_komal_group1_comp304lab5_sec_003;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientsDao
{
    @Insert
    void insert(Patient patient);

    @Update
    void update(Patient patient);

    @Query("select * from Patient order by firstName")
    LiveData<List<Patient>> getAllPatients();

    @Query("select * from Patient where nurseId = :nurseId")
    LiveData<List<Patient>> getPatientsByNurseId(int nurseId);

}