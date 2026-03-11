package com.example.medmate;

import java.util.ArrayList;

public class doctormodel {
    String id,name,speciality,dob,gender,experience,email,hospital,mobilenumber,password,document;

    public doctormodel(String id, String name, String speciality, String dob, String gender, String experience, String email,String hospital, String mobilenumber, String password, String document) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.dob = dob;
        this.gender = gender;
        this.experience = experience;
        this.email = email;
        this.hospital = hospital;
        this.mobilenumber = mobilenumber;
        this.password = password;
        this.document = document;
    }

    public doctormodel(doctorlistactivity doctorlistactivity, ArrayList<doctormodel> dataModelArrayList) {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getExperience() {
        return experience;
    }

    public String getEmail() {
        return email;
    }

    public String getHospital() {
        return hospital;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getPassword() {
        return password;
    }

    public String getDocument() {
        return document;
    }
}
