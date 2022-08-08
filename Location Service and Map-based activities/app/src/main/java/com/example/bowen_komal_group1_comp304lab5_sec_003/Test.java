package com.example.bowen_komal_group1_comp304lab5_sec_003;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tests",
        foreignKeys = {
                @ForeignKey(entity =  Patient.class, parentColumns = "patientsId", childColumns = "patientsId"),
                @ForeignKey(entity =  Nurse.class, parentColumns = "nurseId", childColumns = "nurseId")
        })
public class Test implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name =  "testId")
    private int testId;

    @NonNull
    private int patientsId;

    @NonNull
    private int nurseId;

    @ColumnInfo(name =  "BPL")
    private String BPL;

    @ColumnInfo(name =  "BPH")
    private String BPH;

    @ColumnInfo(name =  "temperature")
    private String temperature;

    @ColumnInfo(name =  "cholesterol")
    private String cholesterol;

    public Test(){}
    public Test(int patientsId, int nurseId,
                String BPL, String BPH, String temperature, String cholesterol)
    {
        this.patientsId = patientsId;
        this.nurseId = nurseId;
        this.BPH = BPH;
        this.BPL = BPL;
        this.temperature = temperature;
        this.cholesterol = cholesterol;
    }

    public int getPatientsId()
    {
        return patientsId;
    }
    public int getNurseId()
    {
        return nurseId;
    }
    public int getTestId()
    {
        return testId;
    }
    public String getBPL()
    {
        return BPL;
    }
    public void setBPL(String BPL)
    {
        this.BPL = BPL;
    }
    public String getBPH()
    {
        return BPH;
    }
    public void setBPH(String BPH)
    {
        this.BPH = BPH;
    }
    public String getTemperature()
    {
        return temperature;
    }
    public void setTemperature(String temperature)
    {
        this.temperature = temperature;
    }
    public String getCholesterol()
    {
        return cholesterol;
    }
    public void setCholesterol(String cholesterol)
    {
        this.cholesterol = cholesterol;
    }
    public void setPatientsId(int patientsId)
    {
        this.patientsId = patientsId;
    }
    public void setNurseId(int nurseId)
    {
        this.nurseId = nurseId;
    }
    public void setTestId(int testId)
    {
        this.testId = testId;
    }
}
