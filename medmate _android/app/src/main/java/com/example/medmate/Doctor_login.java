package com.example.medmate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Doctor_login extends AppCompatActivity {
    EditText email,password;
    Button b1,b2;
    TextView t1;
    String n1,p1,id,name,dob,ge,speciality,experience,mobilenumber,sh,status,message,url=config.baseurl+"doctorlogin.php";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_login);

        email=findViewById(R.id.drloginemail);
        password=findViewById(R.id.drloginpassword);
        b1=findViewById(R.id.drlb);
        b2=findViewById(R.id.drzlogincreatebutton);
        t1=findViewById(R.id.drloginforgotbutton);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(Doctor_login.this,Doctor_registerpage.class);
                startActivity(in);

            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(Doctor_login.this,Forgotpassword1.class);
                startActivity(in);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login1();
            }
        });




    }
    private void login1() {
        n1=email.getText().toString();
        p1=password.getText().toString();

        if (TextUtils.isEmpty(n1)){
            email.requestFocus();
            email.setError("Enter your email");
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(n1).matches()) {
            email.setError("Enter a valid email");
            return;
        }

        if ((TextUtils.isEmpty(p1))){
            password.requestFocus();
            password.setError("character must eight digit");
            return;
        }
        StringRequest StringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                         Toast.makeText(Doctor_login.this, response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject c = new JSONObject(response);
                            status = c.getString("status");
                            message = c.getString("message");

                            id=c.getString("id");
                            name=c.getString("name");
                            speciality=c.getString("speciality");
                            dob=c.getString("dob");
                            ge=c.getString("gender");
                            experience=c.getString("experience");
                            n1=c.getString("email");
                            sh=c.getString("hospital");
                            mobilenumber=c.getString("mobilenumber");
                            p1=c.getString("password");
                            checklogin();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //run cheyikkumbo error indo ennu nokkan
                        Toast.makeText(Doctor_login.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }

                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", n1);
                params.put("password", p1);
                return params;
            }


        };

        //string reqt ne execute cheyan aanu requestqueue
        Volley volley =  null;
        RequestQueue requestQueue = volley.newRequestQueue(this);
        requestQueue.add(StringRequest);
    }


    private void checklogin() {
        if (status.equals("0")){
            Toast.makeText(this, "Invalied", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
            new  Doctor_sessionmanager(Doctor_login.this).createLoginSession(id,name,speciality,dob,ge,experience,n1,sh,mobilenumber,p1);
            Intent i =new Intent(Doctor_login.this,Doctor_Home.class);
            startActivity(i);
            finish();
        }

    }
}
