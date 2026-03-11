package com.example.medmate;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
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

public class Health extends AppCompatActivity {

    // Declare UI elements
    EditText hag, hhei, hwei;
    Spinner hgender, hcc;
    Button hbtn;

    // URLs for API requests
    String url = config.baseurl + "health.php";
    String dietUrl = "https://seccloudstorage.in/aihealthsuggestion/aihealth1.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        // Initialize fields
        hag = findViewById(R.id.ha);
        hhei = findViewById(R.id.hhe);
        hwei = findViewById(R.id.hwe);
        hcc = findViewById(R.id.chronic_conditions);
        hbtn = findViewById(R.id.regbtn);

        hgender = findViewById(R.id.hgender);

        // Spinner for gender
        String[] gender = {"Select Gender", "Male", "Female", "Other"};
        hgender.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gender));

        String[] dis = {"Select Symptoms", "Fever", "Headache,Fever", "Frequent urination,Excessive thirst,Fatigue,Blurred vision","Headache,Dizziness,Chest pain,Shortness of breath","Headache,Dizziness,Chest pain,Shortness of breath","Shortness of breath,Wheezing,Coughing,Chest tightness","Chest pain,Shortness of breath,Fatigue,Irregular heartbeat","Joint pain,Stiffness,Swelling,Reduced mobility","joint pain,stiffness,swelling,reduced mobility",
                "Unexplained weight loss,Fatigue,Persistent pain,Lumps","Swelling,Fatigue,Nausea,Frequent urination","Memory loss,Confusion,Difficulty Speaking,Mood changes","Persistent sadness,Loss of interest,Sleep disturbances","Severe headache,Nausea,light sensitivity,Dizziness"};
        hcc.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dis));



        // Set click listener for button
        hbtn.setOnClickListener(v -> {
            // Register the user data
            register();

            // Call fetchDietDetails() only if chronic conditions (hcc) are filled
            String chronicConditions = hcc.getSelectedItem().toString().trim();
            if (!TextUtils.isEmpty(chronicConditions)) {
                fetchDietDetails(chronicConditions);
            } else {
                Toast.makeText(Health.this, "Please enter chronic conditions.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Register user data to server
    private void register() {
        // Get input values
        String h1 = hag.getText().toString().trim();
        String h2 = hhei.getText().toString().trim();
        String h3 = hgender.getSelectedItem().toString().trim();
        String h4 = hwei.getText().toString().trim();
        String h5 = hcc.getSelectedItem().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(h1)) {
            hag.setError("Enter age");
            return;
        }
        if (TextUtils.isEmpty(h2)) {
            hhei.setError("Enter height");
            return;
        }
        if (TextUtils.isEmpty(h4)) {
            hwei.setError("Enter weight");
            return;
        }
//        if (TextUtils.isEmpty(h5)) {
//            hcc.setError("Enter chronic conditions");
//            return;
//        }
        if (hgender.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show progress dialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering...");
        progressDialog.show();

        // Send data to server
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(Health.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Health.this, "Error in response!", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    progressDialog.dismiss();
                    Toast.makeText(Health.this, "Network Error!", Toast.LENGTH_SHORT).show();
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("age", h1);
                params.put("height", h2);
                params.put("gender", h3);
                params.put("weight", h4);
                params.put("conditions", h5);
                return params;
            }
        };

        // Adding the request to the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // Fetch diet suggestions based on chronic conditions
    private void fetchDietDetails(String condition) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Details...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, dietUrl,
                response -> {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
                        String details = jsonObject.optString("details", "No details available!");

                        // Show diet suggestions in a dialog if response is successful
                        if (status.equals("1")) {
                            showDietDetailsDialog(condition, details);
                        } else {
                            Toast.makeText(Health.this, "No details available!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Health.this, "Error in response format!", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    progressDialog.dismiss();
                    Toast.makeText(Health.this, "Network Error!", Toast.LENGTH_SHORT).show();
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("conditions", condition);
                return params;
            }
        };

        // Adding the request to the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // Display the diet details in a dialog
    private void showDietDetailsDialog(String condition, String dietDetails) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Suggestions for " + condition);
        builder.setMessage(dietDetails);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}
