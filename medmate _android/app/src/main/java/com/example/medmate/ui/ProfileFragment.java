package com.example.medmate.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.example.job.Home;
//import com.example.job.R;
//import com.example.job.SessionManager;
//import com.example.job.config;
import com.example.medmate.Home;
import com.example.medmate.Login;
import com.example.medmate.R;
//import com.example.bloodbank.SessionManager;
import com.example.medmate.Sessionmanager;
import com.example.medmate.config;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ProfileFragment extends Fragment {

    EditText pname,pemail,pphone,ppassword;
    Button pupdate,logout;
   String pn,pe,pp,ppass,ppid;
   String status,message;
   String  url = config.baseurl + "profileupdation.php";

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile, container, false);
        pname= v.findViewById(R.id.profilename);
        pemail = v.findViewById(R.id.profileemail);
        pphone=v.findViewById(R.id.profilemobilenumber);
        ppassword = v.findViewById(R.id.profilecreatepassword);
        pupdate = v.findViewById(R.id.profilesubmitbutton);
        logout = v.findViewById(R.id.log);

logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        log1();
    }


});

        HashMap<String,String> data=new Sessionmanager(getActivity()).getUserDetails();

        ppid=data.get("id");
        pn=data.get("name");
        pe=data.get("email");
        pp=data.get("mobilenumber");
        ppass=data.get("password");

        pname.setText(pn);
        pemail.setText(pe);
        pphone.setText(pp);
        ppassword.setText(ppass);



        pupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit();
            }
        });
        return v;
    }
    private void submit() {
        pn=pname.getText().toString();
        pe=pemail.getText().toString();
        pp=pphone.getText().toString();
        ppass=ppassword.getText().toString();


        if (TextUtils.isEmpty(pn)){
            pname.requestFocus();
            pname.setError("required field");
            return;
        }


        // Validate email
        if (pe.isEmpty()) {
            pemail.setError("Email is required");
            pemail.requestFocus();
            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(pe).matches()) {
            pemail.setError("Valid email is required");
            pemail.requestFocus();
            return;
        }

        // Validate mobile number
        if (pp.isEmpty()) {
            pphone.setError("Mobile number is required");
            pphone.requestFocus();
            return;
        } else if (pp.length() != 10 || !pp.matches("[0-9]+")) {
            pphone.setError("Valid 10-digit mobile number is required");
            pphone.requestFocus();
            return;
        }

        // Validate password
        if (ppass.isEmpty()) {
            ppassword.setError("Password is required");
            ppassword.requestFocus();
            return;
        } else if (ppass.length() < 6) {
            ppassword.setError("Password must be at least 6 characters");
            ppassword.requestFocus();
            return;
        }


        StringRequest str = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();


                try {
                    JSONObject json = new JSONObject(response);
                    status = json.getString("status");
                    message = json.getString("message");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if ("0".equals(status)) {
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "updation successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), Login.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id",ppid);
                params.put("name", pn);
                params.put("email", pe);
                params.put("mobilenumber", pp);
                params.put("password", ppass);

                return params;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(str);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Object binding = null;
    }

    private void log1() {
        new AlertDialog.Builder(requireContext()) // Use requireContext() for Fragment
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout from your account?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Use requireActivity() for launching an Intent from a Fragment
                        startActivity(new Intent(requireActivity(), Login.class));
                        requireActivity().finish(); // Finish the activity hosting the fragment
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Dismiss the dialog
                    }
                })
                .show();
    }


}
