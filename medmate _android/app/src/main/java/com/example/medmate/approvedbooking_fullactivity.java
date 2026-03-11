package com.example.medmate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class approvedbooking_fullactivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7;
    String us1,us2,us3,us4,us5,us6,us7;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_approvedbooking_fullactivity);
        t1=findViewById(R.id.apus);
        t2=findViewById(R.id.apusnmr);
        t3=findViewById(R.id.ape);
        t4=findViewById(R.id.apage);
        t5=findViewById(R.id.apresn);
        t6=findViewById(R.id.apdate);
        t7=findViewById(R.id.aptime);

        Intent intent = getIntent();
        us1 = intent.getStringExtra("username");
        t1.setText("Username"+us1);

        us2 = intent.getStringExtra("usernumber");
        t2.setText("usernumber"+us2);

        us3 = intent.getStringExtra("useremail");
        t3.setText("useremail"+ us3);

        us4 = intent.getStringExtra("userage");
        t4.setText("userage"+ us4);

        us5 = intent.getStringExtra("reason");
        t5.setText( "userreason"+us5);

        us6 = intent.getStringExtra("date");
        t6.setText("date"+ us6);

        us7 = intent.getStringExtra("time");
        t7.setText( "time"+us7);





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}