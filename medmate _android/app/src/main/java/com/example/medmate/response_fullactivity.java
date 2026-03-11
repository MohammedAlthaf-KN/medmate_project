package com.example.medmate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class response_fullactivity extends AppCompatActivity {

    TextView t1, t2, t3, t4, t5, t6, t7, t8;

    String st1, st2, st3, st4, st5, st6, st7, st8;

    CheckBox confirmBookingCheckBox;
    Button payNowButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_response_fullactivity);

        t1 = findViewById(R.id.refdocname);
        t2 = findViewById(R.id.refdocemail);
        t3 = findViewById(R.id.refusername);
        t4 = findViewById(R.id.refusrnbr);
        t5 = findViewById(R.id.refusremail);
        t6 = findViewById(R.id.refreasn);
        t7 = findViewById(R.id.redate);
        t8 = findViewById(R.id.reftime);

//        t11 = findViewById(R.id.ctimey);
//        img = findViewById(R.id.cdocimagey);

        // Initialize the CheckBox and Button
        confirmBookingCheckBox = findViewById(R.id.refconfirm);
        payNowButton = findViewById(R.id.refpay);

        // Initially disable the Pay Now button
        payNowButton.setEnabled(false);

        // Enable the Pay Now button if the CheckBox is checked, disable if unchecked
        confirmBookingCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            payNowButton.setEnabled(isChecked);  // Enable if checked, disable if unchecked
        });

        // Set click listener for the Pay Now button
        payNowButton.setOnClickListener(view -> {
            if (confirmBookingCheckBox.isChecked()) {
                // Proceed with payment
                Toast.makeText(response_fullactivity.this, "Proceeding to payment...", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(response_fullactivity.this, Gpayactivity2.class);

                // Doctor details
                i.putExtra("docname", st1);
                i.putExtra("usernumber", st4);

                startActivity(i);
                finish();
                // Add your payment processing code here
            } else {
                // Show message if the checkbox is not checked
                Toast.makeText(response_fullactivity.this, "Please confirm your booking first.", Toast.LENGTH_SHORT).show();
            }
        });

        // Receiving data from intent
        Intent intent = getIntent();


        st1 = intent.getStringExtra("docname");
        t1.setText(st1);

        st2 = intent.getStringExtra("docemail");
        t2.setText(st2);

        st3 = intent.getStringExtra("username");
        t3.setText(st3);

        st4 = intent.getStringExtra("usernumber");
        t4.setText(st4);

        st5 = intent.getStringExtra("useremail");
        t5.setText(st5);

        st6 = intent.getStringExtra("reason");
        t6.setText(st6);

        st7 = intent.getStringExtra("date");
        t7.setText(st7);

        st8 = intent.getStringExtra("time");
        t8.setText(st8);

//        st9 = intent.getStringExtra("description");
//        t9.setText(st9);
//
//        st10 = intent.getStringExtra("objective");
//        t10.setText(st10);



        // Handling window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
