package com.example.medmate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Doctor_Profile extends AppCompatActivity {
    EditText name, dateofbirth, specialisation, gender, experience, email, hs, mobilenumber, password;
    Button b1, log2;
    String url = config.baseurl + "doctorprofileupdation.php";

    String id, sname, sdateofbirth, sspeciality, sgender, sexperience, semail, shs, smobilenumber, spassword, status, message;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        name = findViewById(R.id.drprofilename);
        dateofbirth = findViewById(R.id.drprofiledob);
        specialisation = findViewById(R.id.drspecialisation);
        gender = findViewById(R.id.drprofilegender);
        experience = findViewById(R.id.drprofileExperience);
        email = findViewById(R.id.drprofileemail);
        hs = findViewById(R.id.drprofilehospital);
        mobilenumber = findViewById(R.id.drprofilemobilenumber);
        password = findViewById(R.id.drprofilepassword);
        b1 = findViewById(R.id.drprofileupdatebutton);
        log2 = findViewById(R.id.log2);

        log2.setOnClickListener(view -> log1());

        // Get data from session
        HashMap<String, String> data = new Doctor_sessionmanager(Doctor_Profile.this).getUserDetails();
        id = data.get("id");
        sname = data.get("name");
        sspeciality = data.get("speciality");
        sdateofbirth = data.get("dob");
        sgender = data.get("gender");
        sexperience = data.get("experience");
        semail = data.get("email");
        shs = data.get("hospital");
        smobilenumber = data.get("mobilenumber");
        spassword = data.get("password");

        // Set initial values in fields
        name.setText(sname);
        specialisation.setText(sspeciality);
        dateofbirth.setText(sdateofbirth);
        gender.setText(sgender);
        experience.setText(sexperience);
        email.setText(semail);
        hs.setText(shs);
        mobilenumber.setText(smobilenumber);
        password.setText(spassword);

        // Handle the update button click
        b1.setOnClickListener(v -> Doctor());
    }

    private void Doctor() {
        // Get updated data from input fields
        sname = name.getText().toString();
        sspeciality = specialisation.getText().toString();
        sdateofbirth = dateofbirth.getText().toString();
        sgender = gender.getText().toString();
        sexperience = experience.getText().toString();
        semail = email.getText().toString();
        shs = hs.getText().toString();
        smobilenumber = mobilenumber.getText().toString();
        spassword = password.getText().toString();

        // Validation checks
        if (TextUtils.isEmpty(sname)) {
            name.setError("required field");
            name.requestFocus();
            return;
        }
        if (sdateofbirth.isEmpty()) {
            dateofbirth.setError("required field");
            dateofbirth.requestFocus();
            return;
        }
        if (sspeciality.isEmpty()) {
            specialisation.setError("required field");
            specialisation.requestFocus();
            return;
        }
        if (sgender.isEmpty()) {
            gender.setError("required field");
            gender.requestFocus();
            return;
        }

        if (semail.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(semail).matches()) {
            email.setError("Valid email is required");
            email.requestFocus();
            return;
        }
        if (shs.isEmpty()) {
            hs.setError("Hospital name required");
            hs.requestFocus();
            return;
        }
        // Validate mobile number
        if (smobilenumber.isEmpty()) {
            mobilenumber.setError("Mobile number is required");
            mobilenumber.requestFocus();
            return;
        } else if (smobilenumber.length() != 10 || !smobilenumber.matches("[0-9]+")) {
            mobilenumber.setError("Valid 10-digit mobile number is required");
            mobilenumber.requestFocus();
            return;
        }

        // Validate password
        if (spassword.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        // Send request to update profile
        StringRequest str = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Doctor_Profile.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject json = new JSONObject(response);
                    status = json.getString("status");
                    message = json.getString("message");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if ("0".equals(status)) {
                    Toast.makeText(Doctor_Profile.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Doctor_Profile.this, "Update successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Doctor_Profile.this, Doctor_login.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Doctor_Profile.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", sname);
                params.put("speciality", sspeciality);
                params.put("dob", sdateofbirth);
                params.put("gender", sgender);
                params.put("experience", sexperience);
                params.put("email", semail);
                params.put("hospital", shs);
                params.put("mobilenumber", smobilenumber);
                params.put("password", spassword);
                return params;
            }
        };

        // Add the request to the queue
        RequestQueue rq = Volley.newRequestQueue(Doctor_Profile.this);
        rq.add(str);
    }


    private void log1() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout from your account?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(getApplicationContext(),chosseuser.class));
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

}
