package com.example.medmate;

public class dr_responsemodel {
    String id, docname, docemail, username, usernumber, useremail, userage, reason, date, time, status;

    public dr_responsemodel(String id, String docname, String docemail, String username, String usernumber, String useremail, String userage, String reason, String date, String time, String status) {
        this.id = id;
        this.docname = docname;
        this.docemail = docemail;
        this.username = username;
        this.usernumber = usernumber;
        this.useremail = useremail;
        this.userage = userage;
        this.reason = reason;
        this.date = date;
        this.time = time;
        this.status = status;
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

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }
}
