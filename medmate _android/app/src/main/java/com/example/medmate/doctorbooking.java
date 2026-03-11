package com.example.medmate;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class doctorbooking extends AppCompatActivity {
    EditText doname, doemail, uname, unumber, uemail, uage,reason, dat;
    Button book;
    String url = config.baseurl + "doctorbooking.php"; // Ensure this URL points to the correct PHP endpoint
    Spinner sp; // Time slot spinner
    String[] timeSlots = {"Select your time slot", "10am-10.30am", "10.30am-11am", "11am-11.30am", "11.30am-12pm", "12pm-12.30pm", "12.30pm-1pm"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorbooking);

        // Initialize views
        doname = findViewById(R.id.docname1);
        doemail = findViewById(R.id.docmobile1);
        uname = findViewById(R.id.patname1);
        unumber = findViewById(R.id.patmobile1);
        uemail = findViewById(R.id.patemail1);
        uage = findViewById(R.id.patage1);
        reason = findViewById(R.id.description);
        dat = findViewById(R.id.dn1);
        book = findViewById(R.id.book1);
        sp = findViewById(R.id.spi11); // Time slot spinner

        // Set up the time slot spinner
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, timeSlots);
        sp.setAdapter(timeAdapter);

        // Set onClick listener for the date input
        dat.setOnClickListener(v -> showDatePicker());

        // Load user data
        loadUserData();

        // Set button click listener for booking
        book.setOnClickListener(v -> processBooking());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create the DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                doctorbooking.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Format the selected date and set it to the TextView (or other UI element)
                    dat.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                },
                year, month, day
        );

        // Disable past dates (set the minimum date to today's date)
        calendar.add(Calendar.DATE, 0); // Set to today's date
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    private void loadUserData() {
        // Assuming Sessionmanager stores the user details
        HashMap<String, String> data = new Sessionmanager(doctorbooking.this).getUserDetails();
        uname.setText(data.get("name"));
        unumber.setText(data.get("mobilenumber"));
        uemail.setText(data.get("email"));

        // Receiving doctor info via Intent
        Intent intent = getIntent();
        doname.setText(intent.getStringExtra("name"));
        doemail.setText(intent.getStringExtra("email"));
    }

    private void processBooking() {
        String p1 = uname.getText().toString().trim();
        String d11 = doname.getText().toString().trim();
        String d22 = doemail.getText().toString().trim();
        String d33 = unumber.getText().toString().trim();
        String d44 = uemail.getText().toString().trim();
        String d99 = uage.getText().toString().trim();
        String d12 = reason.getText().toString().trim();
        String d55 = dat.getText().toString().trim();
        String sp1 = sp.getSelectedItem().toString();

        // Validate input fields
        if (TextUtils.isEmpty(d11) || TextUtils.isEmpty(d22) || TextUtils.isEmpty(d33) ||
                TextUtils.isEmpty(d44) || TextUtils.isEmpty(d99)|| TextUtils.isEmpty(d12) || TextUtils.isEmpty(d55) ||
                "Select your time slot".equals(sp1)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Send booking request to the server
        sendBookingRequest(p1, d11, d22, d33, d44, d99,d12, d55, sp1);
    }

    private void sendBookingRequest(String p1, String d11, String d22, String d33, String d44, String d99,String d12,String d55,  String sp1) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        // Handle server response
                        JSONObject c = new JSONObject(response);
                        String status = c.getString("status");
                        String message = c.getString("message");
                        checkBookingStatus(status, message);
                    } catch (JSONException e) {
                        Toast.makeText(this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(doctorbooking.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show()) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("docname", d11);       // Doctor name
                params.put("docemail", d22);      // Doctor email
                params.put("username", p1);       // Patient name
                params.put("usernumber", d33);    // Patient phone number
                params.put("useremail", d44);     // Patient email
                params.put("userage", d99);       // Patient age
                params.put("reason", d12);
                params.put("date", d55);          // Appointment date
                params.put("time", sp1);          // Selected time slot
                return params;
            }
        };

        // Execute request
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void checkBookingStatus(String status, String message) {
        if ("0".equals(status)) {
            Toast.makeText(this, "Time slot is already taken: " + message, Toast.LENGTH_SHORT).show();
        } else {
            // Booking successful
            Toast.makeText(this, " successfull", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(doctorbooking.this, Home.class);
            startActivity(i);
            finish();
        }
    }
}
