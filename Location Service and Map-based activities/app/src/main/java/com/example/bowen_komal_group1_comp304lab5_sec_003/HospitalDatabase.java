package com.example.bowen_komal_group1_comp304lab5_sec_003;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database( entities = {
        Patient.class, Nurse.class, Test.class
}, version = 1, exportSchema = false)
public abstract class HospitalDatabase
        extends RoomDatabase
{
    public abstract PatientsDao patientsDao();
    public abstract NurseDao nurseDao();
    public abstract TestDao testDao();

    private static volatile HospitalDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final String DATABASE_NAME = "HospitalDB";

    static HospitalDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (HospitalDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            HospitalDatabase.class,
                            DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
