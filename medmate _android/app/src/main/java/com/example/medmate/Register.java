package com.example.medmate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

public class Register extends AppCompatActivity {
    EditText name, email, mobilenumber, createpassword, confirmpassword;
    Button b1;
    TextView t1;
    String n1, e1, mn1, cpwrd1, cp1, status, message, url = config.baseurl + "register.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Initialize views
        name = findViewById(R.id.registername);
        email = findViewById(R.id.registeremail);
        mobilenumber = findViewById(R.id.registermobilenumber);
        createpassword = findViewById(R.id.registercreatepassword);
        confirmpassword = findViewById(R.id.registerconfirmpassword);
        b1 = findViewById(R.id.registersubmitbutton);
        t1 = findViewById(R.id.registeralreadyaccount);

        // Adjust the system padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Add TextWatchers to fields to clear errors on typing
        addTextWatchers();

        // Handle "Already have an account" click
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Register.this, Login.class);
                startActivity(in);
            }
        });

        // Handle form submission
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register1();
            }
        });
    }

    // Add TextWatchers to each input field to clear errors
    private void addTextWatchers() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (name.getError() != null) {
                    name.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (email.getError() != null) {
                    email.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        mobilenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (mobilenumber.getError() != null) {
                    mobilenumber.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        createpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (createpassword.getError() != null) {
                    createpassword.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        confirmpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (confirmpassword.getError() != null) {
                    confirmpassword.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void register1() {
        // Get input values
        n1 = name.getText().toString();
        e1 = email.getText().toString();
        mn1 = mobilenumber.getText().toString();
        cpwrd1 = createpassword.getText().toString();
        cp1 = confirmpassword.getText().toString();

        // Validate name
        if (n1.isEmpty()) {
            name.setError("Name is required");
            name.requestFocus();
            return;
        }

        // Validate email
        if (e1.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(e1).matches()) {
            email.setError("Valid email is required");
            email.requestFocus();
            return;
        }

        // Validate mobile number
        if (mn1.isEmpty()) {
            mobilenumber.setError("Mobile number is required");
            mobilenumber.requestFocus();
            return;
        } else if (mn1.length() != 10 || !mn1.matches("[0-9]+")) {
            mobilenumber.setError("Valid 10-digit mobile number is required");
            mobilenumber.requestFocus();
            return;
        }

        // Validate password
        if (cpwrd1.isEmpty()) {
            createpassword.setError("Password is required");
            createpassword.requestFocus();
            return;
        } else if (cpwrd1.length() < 6) {
            createpassword.setError("Password must be at least 6 characters");
            createpassword.requestFocus();
            return;
        }

        // Validate confirm password
        if (cp1.isEmpty()) {
            confirmpassword.setError("Confirm password is required");
            confirmpassword.requestFocus();
            return;
        } else if (!cpwrd1.equals(cp1)) {
            confirmpassword.setError("Passwords do not match");
            confirmpassword.requestFocus();
            return;
        }

        // If all fields are valid, proceed to send the registration request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject c = new JSONObject(response);
                            status = c.getString("status");
                            message = c.getString("message");
                            checklogin();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", n1);
                params.put("email", e1);
                params.put("mobilenumber", mn1);
                params.put("password", cpwrd1);
                return params;
            }
        };

        // Execute the request
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void checklogin() {
        if (status.equals("0")) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Register.this, Login.class);
            startActivity(i);
            finish();
        }
    }
}
