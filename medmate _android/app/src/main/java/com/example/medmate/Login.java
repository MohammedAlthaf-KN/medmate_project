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

public class Login extends AppCompatActivity {
    EditText name,password;
    Button b1,b2;
    TextView t1;
    String lid,lna,n11,lm,p11,status,message,url=config.baseurl+"login.php";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        name=findViewById(R.id.loginname);
        password=findViewById(R.id.loginpassword);
        b1=findViewById(R.id.lb);
        b2=findViewById(R.id.logincreatebutton);
        t1=findViewById(R.id.loginforgotbutton);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(Login.this,Register.class);
                startActivity(in);

            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(Login.this,Forgotpassword.class);
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
        n11=name.getText().toString();
        p11=password.getText().toString();

        if (TextUtils.isEmpty(n11)){
            name.requestFocus();
            name.setError("Enter your email");
            return;
        }
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(n11).matches()) {
                    name.setError("Enter a valid email");
                    return;
                }
        if ((TextUtils.isEmpty(p11))){
            password.requestFocus();
            password.setError("character must eight digit");
            return;
        }
                StringRequest StringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                                try {
                                    JSONObject c = new JSONObject(response);
                                    status = c.getString("status");
                                    message = c.getString("message");


                                    lid=c.getString("id");
                                    lna=c.getString("name");
                                    n11=c.getString("email");
                                    lm=c.getString("mobilenumber");
                                    p11=c.getString("password");

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
                                Toast.makeText(Login.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                            }

                        }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", n11);
                        params.put("password", p11);
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
            new  Sessionmanager(Login.this).createLoginSession(lid,lna,n11,lm,p11);
            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
            Intent i =new Intent(Login.this,Home.class);
            startActivity(i);
            finish();
        }

    }
}

