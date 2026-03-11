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

public class healthcare_fullactivity extends AppCompatActivity {
    TextView t1,t2,t3;
    String titl,tps,dsp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_healthcare_fullactivity);
        t1 = findViewById(R.id.health);
        t2 = findViewById(R.id.tips);
        t3 = findViewById(R.id.des);


        Intent intent = getIntent();
        titl = intent.getStringExtra("title");
        t1.setText(titl);

        tps = intent.getStringExtra("tips");
        t2.setText( tps);

        dsp = intent.getStringExtra("description");
        t3.setText( dsp);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}