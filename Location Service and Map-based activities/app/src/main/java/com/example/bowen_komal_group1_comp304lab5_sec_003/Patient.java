package com.example.bowen_komal_group1_comp304lab5_sec_003;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Patient implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "patientsId")
    private int patientsId;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "department")
    private String department;

    @ColumnInfo(name = "room")
    private String room;

    @ForeignKey(entity = Nurse.class, parentColumns = "nurseId", childColumns = "nurseId")
    private int nurseId;

    public Patient(){}
    public Patient(String firstName, String lastName,
                   String department, String room, int nurseId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.room = room;
        this.nurseId = nurseId;
    }

    public int getPatientsId()
    {
        return patientsId;
    }
    public void setPatientsId(int patientsId)
    {
        this.patientsId = patientsId;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDepartment()
    {
        return department;
    }
    public void setDepartment(String department)
    {
        this.department = department;
    }
    public String getRoom()
    {
        return room;
    }
    public void setRoom(String room)
    {
        this.room = room;
    }
    public int getNurseId()
    {
        return nurseId;
    }
    public void setNurseId(int nurseId)
    {
        this.nurseId = nurseId;
    }
}
