package com.example.medmate;

public class userprescription_modellist {
    String id,doctorname,doctorphone,username,userphone,prescription,date;

    public userprescription_modellist(String id, String doctorname, String doctorphone, String username, String userphone, String prescription, String date) {
        this.id = id;
        this.doctorname = doctorname;
        this.doctorphone = doctorphone;
        this.username = username;
        this.userphone = userphone;
        this.prescription = prescription;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public String getDoctorphone() {
        return doctorphone;
    }

    public String getUsername() {
        return username;
    }

    public String getUserphone() {
        return userphone;
    }

    public String getPrescription() {
        return prescription;
    }

    public String getDate() {
        return date;
    }
}
