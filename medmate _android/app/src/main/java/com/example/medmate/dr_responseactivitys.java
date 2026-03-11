package com.example.medmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class dr_responseactivitys extends AppCompatActivity {

    EditText search1;
    String email;

    private String url = config.baseurl + "dr_response.php";
    private ArrayList<dr_responsemodel> dataModelArrayList;
    private dr_responseadapter rvvAdapter;  // Corrected this line
    private RecyclerView recyclerView1;
    private ProgressBar p1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_responseactivitys);

        recyclerView1 = findViewById(R.id.res1);
        p1 = findViewById(R.id.responsee);

        HashMap<String,String> data = new Doctor_sessionmanager(dr_responseactivitys.this).getUserDetails();
        email =data.get("email");

//        search1 = findViewById(R.id.se);
//        search1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
//
//            @Override
//            public void afterTextChanged(Editable text) {
//                //new array list that will hold the filtered data
//                ArrayList<doctormodel> filteredSongs = new ArrayList<>();
//
//                if (dataModelArrayList != null && !dataModelArrayList.isEmpty()) {
//                    //looping through existing elements
//                    for (doctormodel s1 : dataModelArrayList) {
//                        //if the existing elements contain the search input
//                        if (s1.getSpeciality().toLowerCase().contains(text.toString().toLowerCase())) {
//                            //adding the element to filtered list
//                            filteredSongs.add(s1);
//                        }
//                    }
//                }
//
//                if (rvvAdapter != null) {
//                    //calling a method of the adapter class and passing the filtered list
//                    rvvAdapter.filterList(filteredSongs);
//                }
//            }
//        });

        fetchingJSON();
    }

    private void fetchingJSON() {
        p1.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            p1.setVisibility(View.GONE);

                            dataModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject dataobj = array.getJSONObject(i);

                                dataModelArrayList.add(new dr_responsemodel(
                                        dataobj.getString("id"),
                                        dataobj.getString("docname"),
                                        dataobj.getString("docemail"),
                                        dataobj.getString("username"),
                                        dataobj.getString("usernumber"),
                                        dataobj.getString("useremail"),
                                        dataobj.getString("userage"),
                                        dataobj.getString("reason"),
                                        dataobj.getString("date"),
                                        dataobj.getString("time"),
                                        dataobj.getString("status")



                                ));
                            }
                            setupRecycler();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        p1.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("docemail", email );

                return params;
            }
        };

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 20000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 20000;
            }

            @Override
            public void retry(VolleyError error) {
                p1.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setupRecycler() {
        rvvAdapter = new dr_responseadapter(this, dataModelArrayList); // Fixed constructor call
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(rvvAdapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
}



