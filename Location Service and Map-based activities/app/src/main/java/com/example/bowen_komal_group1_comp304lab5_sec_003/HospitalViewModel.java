package com.example.bowen_komal_group1_comp304lab5_sec_003;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class HospitalViewModel extends AndroidViewModel
{
    private HospitalRepository mRepository;

    private LiveData<List<Patient>> mAllPatients;
    private LiveData<List<Nurse>> mAllNurses;
    private LiveData<List<Patient>> mPatientForNurse;
    private LiveData<List<Test>> mTestByPatientByNurse;
    private LiveData<List<Test>> mTestForNurse;

    public HospitalViewModel(Application application)
    {
        super(application);
        mRepository = new HospitalRepository(application);
        mAllPatients = mRepository.getAllPatients();
        mAllNurses = mRepository.getAllNurses();
    }

    LiveData<List<Patient>> getAllPatients() {return mAllPatients;}
    LiveData<List<Nurse>> getAllNurses() {return mAllNurses;}

    LiveData<List<Patient>> getAllPatientsForNurse(int nurseId)
    {
        mPatientForNurse = mRepository.getPatientsForNurse(nurseId);
        return mPatientForNurse;
    }

    LiveData<List<Test>> getTestsForNurse(int nurseId)
    {
        mTestForNurse = mRepository.getTestsForNurse(nurseId);
        return mTestForNurse;
    }

    Nurse getNurseByUsername(String username)
    {
        return mRepository.getNurseByUsername(username);
    }

    //INSERT METHODS
    public void insertPatient(Patient patient) { mRepository.insertPatient(patient); }
    public void insertNurse(Nurse nurse) { mRepository.insertNurse(nurse); }
    public void insertTest(Test test)
    {
        mRepository.insertTest(test);
    }

    //UPDATE METHODS
    public void updatePatient(Patient patient)
    {
        mRepository.updatePatient(patient);
    }
    public void updateTest(Test test) {mRepository.updateTest(test);}

    public LiveData<List<Test>> getTestsByPatientByNurse(int nurseId, int patientId)
    {
        mTestByPatientByNurse = mRepository.getTestByPatientByNurse(nurseId, patientId);
        return mTestByPatientByNurse;
    }


}