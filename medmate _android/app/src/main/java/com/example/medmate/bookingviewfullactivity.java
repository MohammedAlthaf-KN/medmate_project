package com.example.medmate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class bookingviewfullactivity extends AppCompatActivity {

    EditText t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
    ImageView img;
    String st1,st2,st3,st4,st5,st6,st7,st8,st9,st10,st11,st12,simg,gender,status,message,url=config.baseurl+"bookingviewfull.php";
    RadioGroup regen;
    Button b1;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bookingviewfullactivity);

        t1=findViewById(R.id.usrname_bookingdetails);
        t2=findViewById(R.id.usremail_bkdetails);
        t3=findViewById(R.id.usrage_bkdetails);
        t4=findViewById(R.id.reason_bkdetails);
        t5=findViewById(R.id.date_bkdetails);
        t6=findViewById(R.id.time_bkdetails);

        regen = findViewById(R.id.gen1);
        b1 = findViewById(R.id.sub);


        Intent intent = getIntent();
        st1 = intent.getStringExtra("username");
        t1.setText(st1);

        st2 = intent.getStringExtra("useremail");
        t2.setText( st2);

        st3 = intent.getStringExtra("userage");
        t3.setText( st3);

        st4 = intent.getStringExtra("reason");
        t4.setText( st4);

        st5 = intent.getStringExtra("date");
        t5.setText( st5);

        st6 = intent.getStringExtra("time");
        t6.setText( st6);

        st7 = intent.getStringExtra("docname");



        st8 = intent.getStringExtra("docemail");

        st9 = intent.getStringExtra("usernumber");









        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approve();
            }
        });
    }

    private void approve() {
        int id = regen.getCheckedRadioButtonId();
        if (id == -1) {
            // No radio button is checked, show an error message
            Toast.makeText(this, "Please select a one option", Toast.LENGTH_SHORT).show();
            return; // Exit the method if no button is selected
        } else {
            RadioButton radioButton = regen.findViewById(id);
            gender = radioButton.getText().toString();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(bookingviewfullactivity.this, response, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(bookingviewfullactivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                params.put("username", st1);
                params.put("useremail",st2);
                params.put("userage", st3);
                params.put("reason",st4);
                params.put("date", st5);
                params.put("time",st6);
                params.put("docname",st7);
                params.put("docemail", st8);
                params.put("usernumber", st9);
                params.put("status", gender);
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
            Toast.makeText(this, "Submit successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(bookingviewfullactivity.this, Doctor_Home.class);
            startActivity(i);
            finish();
        }
    }
}