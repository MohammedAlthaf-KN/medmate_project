package com.example.medmate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Selectdoctors extends AppCompatActivity {


    CardView d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_selectdoctors);

        d1=findViewById(R.id.d1);
        d2=findViewById(R.id.d2);
        d3=findViewById(R.id.d3);
        d4=findViewById(R.id.d4);
        d5=findViewById(R.id.d5);
        d6=findViewById(R.id.d6);
        d7=findViewById(R.id.d7);
        d8=findViewById(R.id.d8);
        d9=findViewById(R.id.d9);
        d10=findViewById(R.id.d10);
        d11=findViewById(R.id.d11);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        d1.setOnClickListener(view -> {
            String menu = "Cardiologist";
            Intent i = new Intent(Selectdoctors.this, doctorlistactivity.class);
            i.putExtra("speciality", menu);
            Toast.makeText(Selectdoctors.this, menu, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });



        d2.setOnClickListener(view -> {
            String menu = "Dermatologist";
            Intent i = new Intent(Selectdoctors.this, doctorlistactivity.class);
            i.putExtra("speciality", menu);
            Toast.makeText(Selectdoctors.this, menu, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });


        d3.setOnClickListener(view -> {
            String menu = "Pediatrician";
            Intent i = new Intent(Selectdoctors.this, doctorlistactivity.class);
            i.putExtra("speciality", menu);
            Toast.makeText(Selectdoctors.this, menu, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });


        d4.setOnClickListener(view -> {
            String menu = "Orthopedic";
            Intent i = new Intent(Selectdoctors.this, doctorlistactivity.class);
            i.putExtra("speciality", menu);
            Toast.makeText(Selectdoctors.this, menu, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });


        d5.setOnClickListener(view -> {
            String menu = "Gynecologist";
            Intent i = new Intent(Selectdoctors.this, doctorlistactivity.class);
            i.putExtra("speciality", menu);
            Toast.makeText(Selectdoctors.this, menu, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });


        d6.setOnClickListener(view -> {
            String menu = "Neurologist";
            Intent i = new Intent(Selectdoctors.this, doctorlistactivity.class);
            i.putExtra("speciality", menu);
            Toast.makeText(Selectdoctors.this, menu, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });


        d7.setOnClickListener(view -> {
            String menu = "Dentist";
            Intent i = new Intent(Selectdoctors.this, doctorlistactivity.class);
            i.putExtra("speciality", menu);
            Toast.makeText(Selectdoctors.this, menu, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });


        d8.setOnClickListener(view -> {
            String menu = "Psychiatrist";
            Intent i = new Intent(Selectdoctors.this, doctorlistactivity.class);
            i.putExtra("speciality", menu);
            Toast.makeText(Selectdoctors.this, menu, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });


        d9.setOnClickListener(view -> {
            String menu = "Endocrinologist";
            Intent i = new Intent(Selectdoctors.this, doctorlistactivity.class);
            i.putExtra("speciality", menu);
            Toast.makeText(Selectdoctors.this, menu, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });


        d10.setOnClickListener(view -> {
            String menu = "Ophthalmologist";
            Intent i = new Intent(Selectdoctors.this, doctorlistactivity.class);
            i.putExtra("speciality", menu);
            Toast.makeText(Selectdoctors.this, menu, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });


        d11.setOnClickListener(view -> {
            String menu = "ENT Specialist";
            Intent i = new Intent(Selectdoctors.this, doctorlistactivity.class);
            i.putExtra("speciality", menu);
            Toast.makeText(Selectdoctors.this, menu, Toast.LENGTH_SHORT).show();
            startActivity(i);
        });


}
}