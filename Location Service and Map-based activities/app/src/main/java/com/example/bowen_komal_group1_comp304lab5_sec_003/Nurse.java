package com.example.bowen_komal_group1_comp304lab5_sec_003;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Nurse implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "nurseId")
    private int nurseId;

    @ColumnInfo(name = "nurseUsername")
    private String username;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "department")
    private String department;

    @ColumnInfo(name = "password")
    private String password;

    public Nurse(){}
    public Nurse(int nurseId, String username, String firstName, String lastName,
                 String department, String password)
    {
        this.nurseId    = nurseId;
        this.username   = username;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.department = department;
        this.password   = password;
    }

    public int getNurseId()
    {
        return nurseId;
    }
    public void setNurseId(int nurseId)
    {
        this.nurseId = nurseId;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
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
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
}