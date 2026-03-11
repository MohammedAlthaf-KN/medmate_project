package com.example.medmate;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Doctor_sessionmanager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    private static final int PRIVATE_MODE = 0;  // Use final since this value isn't changing
    private static final String PREF_NAME = "Login";
    private static final String IS_LOGIN = "IsLoggedIn";

    // Constructor
    public Doctor_sessionmanager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String id, String name, String speciality, String dob, String gender, String experience, String email,String hospital, String mobilenumber, String password) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing doctor details in shared preferences
        editor.putString("id", id);
        editor.putString("name", name);
        editor.putString("speciality", speciality);
        editor.putString("dob", dob);
        editor.putString("gender", gender);
        editor.putString("experience", experience);
        editor.putString("email", email);
        editor.putString("hospital", hospital);
        editor.putString("mobilenumber", mobilenumber);
        editor.putString("password", password);

        // Commit changes asynchronously (apply instead of commit)
        editor.apply();
    }

    public boolean checkLogin() {
        // Check login status
        return isLoggedIn();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        // Fetching user details from shared preferences
        user.put("id", pref.getString("id", ""));
        user.put("name", pref.getString("name", ""));
        user.put("speciality", pref.getString("speciality", ""));
        user.put("dob", pref.getString("dob", ""));
        user.put("gender", pref.getString("gender", ""));
        user.put("experience", pref.getString("experience", ""));
        user.put("email", pref.getString("email", ""));
        user.put("hospital", pref.getString("hospital", ""));
        user.put("mobilenumber", pref.getString("mobilenumber", ""));
        user.put("password", pref.getString("password", ""));

        return user;
    }

    public void logoutUser() {
        // Clearing all data from SharedPreferences
        editor.clear();
        editor.apply(); // Apply instead of commit for asynchronous operation
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
