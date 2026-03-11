package com.example.medmate;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Doctor_registerpage extends AppCompatActivity {

    EditText name, dateofbirth, experience, email,hos, mobilenumber, createpassword, confirmpassword;
    RadioGroup g1;
    Button s1;
    TextView r1;
    Spinner speciality;
    String sname, sspeciality, sdateofbirth, sexperience, semail,shos, smobilenumber, screatepassword, sconfirmpassword, sgender, status, message;
    String url = config.baseurl + "doctorregister.php";

    private RequestQueue rQueue;
    private static ProgressDialog mProgressDialog;

    String[] departments = {"Select your department", "Cardiologist", "Dermatologist", "Pediatrician", "Orthopedic", "Gynecologist", "Neurologist", "Dentist", "Psychiatrist", "Endocrinologist", "Ophthalmologist", "ENT Specialist"};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registerpage);

        // Initialize views
        name = findViewById(R.id.doctorregistername);
        speciality = findViewById(R.id.doctorregisterspeciality);
        dateofbirth = findViewById(R.id.doctorregisterdob);
        experience = findViewById(R.id.Experience);
        email = findViewById(R.id.doctorregisteremail);
        hos = findViewById(R.id.hs);
        mobilenumber = findViewById(R.id.doctorregistermobilenumber);
        createpassword = findViewById(R.id.doctorregistercreatepassword);
        confirmpassword = findViewById(R.id.doctorregisterconfirmpassword);
        s1 = findViewById(R.id.doctorregistersubmitbutton);
        r1 = findViewById(R.id.doctorregisteralreadyaccount);
        g1 = findViewById(R.id.gen);


        // Set up district spinner
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        speciality.setAdapter(districtAdapter);



        // Disable keyboard for date of birth field
        dateofbirth.setInputType(InputType.TYPE_NULL);

        // Set date picker on the dateofbirth field
        dateofbirth.setOnClickListener(v -> showDatePicker());

        r1.setOnClickListener(v -> {
            Intent in = new Intent(Doctor_registerpage.this, Doctor_login.class);
            startActivity(in);
        });

        s1.setOnClickListener(v -> doctorreg());
    }

    private void showDatePicker() {
        // Get current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Set selected date in the EditText
                    String formattedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    dateofbirth.setText(formattedDate);
                },
                year, month, day
        );

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    private void doctorreg() {
        sname = name.getText().toString();
        sspeciality = speciality.getSelectedItem().toString();
        sdateofbirth = dateofbirth.getText().toString();
        sexperience = experience.getText().toString();
        semail = email.getText().toString();
        shos = hos.getText().toString();
        smobilenumber = mobilenumber.getText().toString();
        screatepassword = createpassword.getText().toString();
        sconfirmpassword = confirmpassword.getText().toString();

        // Validation
        if (TextUtils.isEmpty(sname)) {
            name.setError("Enter your name");
            return;
        }

        if (sspeciality.equals("Select your department")) {
            Toast.makeText(this, "Select your department", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(sdateofbirth)) {
            dateofbirth.setError("Enter date of birth");
            return;
        }

        int id = g1.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Select gender", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton radioButton = g1.findViewById(id);
        sgender = radioButton.getText().toString();

        if (TextUtils.isEmpty(sexperience)) {
            experience.setError("Enter your years of experience");
            return;
        }

        if (TextUtils.isEmpty(semail)) {
            email.setError("Enter your Email");
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(semail).matches()) {
            email.setError("Enter a valid email");
            return;
        }
        if (TextUtils.isEmpty(shos)) {
            hos.setError("Enter your Hospital name");
            return;
        }
        if (TextUtils.isEmpty(smobilenumber)) {
            mobilenumber.setError("Enter your Mobilenumber");
            return;
        }
        if (smobilenumber.length() != 10) {
            mobilenumber.requestFocus();
            mobilenumber.setError("Enter 10 digits");
            return;
        }


        if (screatepassword.length() < 6) {
            createpassword.setError("Password must be at least 6 characters");
            return;
        }
        if (!screatepassword.equals(sconfirmpassword)) {
            confirmpassword.setError("Passwords do not match");
            return;
        }
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }
    @SuppressLint("Range")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // Get the Uri of the selected file
            Uri uri = data.getData();
            String uriString = uri.toString();
            File myFile = new File(uriString);
            String path = myFile.getAbsolutePath();
            String displayName = null;
            if (uriString.startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor =getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex( OpenableColumns.DISPLAY_NAME));
                        Log.d("nam  ",displayName);

                        uploadPDF(displayName,uri);
                    }
                } finally {
                    cursor.close();
                }
            } else if (uriString.startsWith("file://")) {
                displayName = myFile.getName();
                Log.d("nameeeee>>>>  ",displayName);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void uploadPDF(final String pdfname, Uri pdffile) {
        InputStream iStream = null;
        try {

            iStream = getContentResolver().openInputStream(pdffile);
            final byte[] inputData = getBytes(iStream);

            showSimpleProgressDialog(Doctor_registerpage.this, null, "Uploading image", false);
            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest( Request.Method.POST, url,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {
                            removeSimpleProgressDialog();
                            Log.d("res",new String(response.data));
                            rQueue.getCache().clear();
                            try {

                                JSONObject jsonObject = new JSONObject(new String(response.data));

                                jsonObject.toString().replace("\\\\","");

                                status = jsonObject.getString("status");
                                message = jsonObject.getString("message");

                                if (status.equals("1")) {
                                    //    Toast.makeText(this, " successfully", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(Doctor_registerpage.this, "successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(Doctor_registerpage.this, Doctor_login.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Doctor_registerpage.this, message, Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            removeSimpleProgressDialog();
                            Toast.makeText(Doctor_registerpage.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", sname);
                    params.put("speciality", sspeciality);
                    params.put("dob", sdateofbirth);
                    params.put("gender", sgender);
                    params.put("experience", sexperience);
                    params.put("email", semail);
                    params.put("hospital", shos);
                    params.put("mobilenumber", smobilenumber);
                    params.put("password", screatepassword);
                    return params;




                }

                /*
                 *pass files using below method
                 * */
                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    params.put("filename", new DataPart(pdfname ,inputData));
                    return params;
                }
            };


            volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            rQueue = Volley.newRequestQueue(this);
            rQueue.add(volleyMultipartRequest);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


    public  void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            Log.e("Log", "inside catch IllegalArgumentException");
            ie.printStackTrace();
        } catch (RuntimeException re) {
            Log.e("Log", "inside catch RuntimeException");
            re.printStackTrace();
        } catch (Exception e) {
            Log.e("Log", "Inside catch Exception");
            e.printStackTrace();
        }

    }

    public void showSimpleProgressDialog(Context context, String title,
                                         String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show( context, title, msg );
                mProgressDialog.setCancelable( isCancelable );
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

