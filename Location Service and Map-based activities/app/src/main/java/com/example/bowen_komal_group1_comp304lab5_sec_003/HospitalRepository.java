package com.example.bowen_komal_group1_comp304lab5_sec_003;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HospitalRepository
{
    private PatientsDao mPatientsDao;
    private NurseDao mNurseDao;
    private TestDao mTestDao;

    private LiveData<List<Patient>> mAllPatients;
    private LiveData<List<Nurse>> mAllNurses;
    private LiveData<List<Test>> mAllTestsByPatientByNurse;
    private LiveData<List<Test>> mAllTests;
    private LiveData<List<Patient>> mPatientForNurse;

    HospitalRepository(Application application)
    {
        HospitalDatabase db = HospitalDatabase.getDatabase(application);

        mPatientsDao = db.patientsDao();
        mNurseDao = db.nurseDao();
        mTestDao = db.testDao();

        mAllPatients = mPatientsDao.getAllPatients();
        mAllNurses = mNurseDao.getAllNurses();
    }

    //GET ALL PATIENTS
    LiveData<List<Patient>> getAllPatients() {return mAllPatients;}
    LiveData<List<Nurse>> getAllNurses() {return mAllNurses;}
    LiveData<List<Test>> getAllTests() {return mAllTests;}

    //GET ALL BY FILTER (NurseID)
    LiveData<List<Patient>> getPatientsForNurse(int nurseId)
    {
        return mPatientsDao.getPatientsByNurseId(nurseId);
    }

    LiveData<List<Test>> getTestsForNurse(int nurseId)
    {
        return mTestDao.getTestForNurse(nurseId);
    }

    LiveData<List<Test>> getTestByPatientByNurse(int nurseId, int patientId)
    {
        mAllTestsByPatientByNurse = mTestDao.getTestsByPatientByNurse(nurseId, patientId);
        return mAllTestsByPatientByNurse;
    }

    //GET ONE BY FILTER
    Nurse getNurseByUsername(String username)
    {
        return mTestDao.getNurseByUsername(username);
    }

    //INSERT METHODS
    void insertPatient(Patient patient)
    {
        HospitalDatabase.databaseWriteExecutor.execute(() ->
        {
            mPatientsDao.insert(patient);
        });
    }

    void insertNurse(Nurse nurse)
    {
        HospitalDatabase.databaseWriteExecutor.execute(() ->
        {
            mNurseDao.insert(nurse);
        });
    }

    public void insertTest(Test test)
    {
        HospitalDatabase.databaseWriteExecutor.execute(() ->
        {
            mTestDao.insert(test);
        });
    }

    //UPDATE METHODS
    void updatePatient(Patient patient)
    {
        HospitalDatabase.databaseWriteExecutor.execute(() ->
        {
            mPatientsDao.update(patient);
        });
    }

    void updateTest(Test test)
    {
        HospitalDatabase.databaseWriteExecutor.execute(() ->
        {
            mTestDao.update(test);
        });
    }


}