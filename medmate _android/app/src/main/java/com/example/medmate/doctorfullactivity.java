package com.example.medmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.Manifest;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medmate.R;


public class doctorfullactivity extends AppCompatActivity {
    EditText drn,dre,drex,drh,drs;
    String aa, bb,cc,dd,ee;
Button book;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorfullactivity);
        drn = findViewById(R.id.doctor_name);
        dre = findViewById(R.id.doctor_email);
        drex=findViewById(R.id.doctor_experience);
        drh=findViewById(R.id.doctor_hospitalname);
        drs=findViewById(R.id.doctor_speciality);
        book=findViewById(R.id.btn);




        Intent intent = getIntent();
        aa = intent.getStringExtra("name");
        drn.setText(aa);

        bb = intent.getStringExtra("email");
        dre.setText(bb);

        cc = intent.getStringExtra("experience");
        drex.setText(cc);

        dd = intent.getStringExtra("hospital");
        drh.setText(dd);

        ee = intent.getStringExtra("speciality");
        drs.setText(ee);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(doctorfullactivity.this, doctorbooking.class);
                n.putExtra("name", aa);
                n.putExtra("email", bb);
                startActivity(n);
            }
        });

//        d = intent.getStringExtra("gender");
//        gender.setText(" gender  :" + d);
//
//        e = intent.getStringExtra("place");
//        place.setText("place :" + e);
//
//        f = intent.getStringExtra("dob");
//        dob.setText("dob  :" + f);
//
//
//        g = intent.getStringExtra("ldd");
//        ldd.setText("ldd  :" + g);

//        h = intent.getStringExtra("culpristaddress");
//        culpritaddress.setText("Culprist address :" + h);

//        i = intent.getStringExtra("complaintaddress");
//        complaintaddress.setText("Complaint address :" + i);

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                check();
//            }
//        });
//    }
//
//    private void check() {
//        if (ContextCompat.checkSelfPermission(Policeuserfulldetails.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
//
//            // Requesting the permission
//            ActivityCompat.requestPermissions(Policeuserfulldetails.this, new String[] { Manifest.permission.SEND_SMS }, 1);
//        }
//        else {
//            sendOTP();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == 1) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                sendOTP();
//                Toast.makeText(Policeuserfulldetails.this, "Message send successfully", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(Policeuserfulldetails.this, "SMS Permission Denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void sendOTP() {
//        String msg = "Please contact your police station to get updated information about your case " ;
//
//        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(g, null, msg, null, null);
//    }
    }
}