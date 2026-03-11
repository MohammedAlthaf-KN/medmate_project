package com.example.medmate;

public class bookingviewmodel {

    String id,docname, docemail, username, usernumber, useremail, userage,reason, time, date;

    public bookingviewmodel(String id, String docname, String docemail, String username, String usernumber, String useremail, String userage, String reason, String time, String date) {
        this.id = id;
        this.docname = docname;
        this.docemail = docemail;
        this.username = username;
        this.usernumber = usernumber;
        this.useremail = useremail;
        this.userage = userage;
        this.reason = reason;
        this.time = time;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getDocname() {
        return docname;
    }

    public String getDocemail() {
        return docemail;
    }

    public String getUsername() {
        return username;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getUserage() {
        return userage;
    }

    public String getReason() {
        return reason;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
