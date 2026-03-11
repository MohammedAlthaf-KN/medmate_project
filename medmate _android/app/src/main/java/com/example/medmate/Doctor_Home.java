package com.example.medmate;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Doctor_Home extends AppCompatActivity {
    RelativeLayout profile,booking,approvedbk,plist;
    private ViewFlipper myViewFlipper;
    private float initialXPoint;
    int[] image = { R.drawable.ptl, R.drawable.appbk,
            R.drawable.userlogo, R.drawable.feedback, R.drawable.approvedbooking };
    private ViewFlipper myflipper;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_home);
        myflipper=findViewById(R.id.myflipper);
        profile= findViewById(R.id.drprofile);
        booking= findViewById(R.id.bookingid);
        approvedbk= findViewById(R.id.apbk);
        plist= findViewById(R.id.patientlist);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myViewFlipper = Doctor_Home.this.myflipper;
        myViewFlipper.setAutoStart(true);
        myViewFlipper.setFlipInterval(3000);
        myViewFlipper.startFlipping();

        for (int i = 0; i < image.length; i++) {
            ImageView imageView = new ImageView(Doctor_Home.this);
            imageView.setImageResource(image[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            myViewFlipper.addView(imageView);
        }


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Doctor_Home.this,Doctor_Profile.class);
                startActivity(in);
            }
        });
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Doctor_Home.this,bookingviewactivity.class);
                startActivity(in);
            }
        });

        approvedbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Doctor_Home.this,dr_responseactivitys.class);
                startActivity(in);
            }
        });
        plist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Doctor_Home.this,gpay_listactivity.class);
                startActivity(in);
            }
        });
    }
}