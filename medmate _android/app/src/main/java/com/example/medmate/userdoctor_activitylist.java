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

public class userdoctor_activitylist extends AppCompatActivity {

    EditText search1;
    String semail;

    private String url = config.baseurl + "userdoctor_activitylist.php";
    private ArrayList<gpay_list> dataModelArrayList;
    private userdoctor_adapterlist rvvAdapter;  // Corrected this line
    private RecyclerView recyclerView1;
    private ProgressBar p1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usedoctor_listactivity);

        recyclerView1 = findViewById(R.id.recu);
        p1 = findViewById(R.id.pru);
        HashMap<String,String> data=new Sessionmanager(userdoctor_activitylist.this).getUserDetails();

        semail = data.get("email");

        search1 = findViewById(R.id.seu);
        search1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable text) {
                //new array list that will hold the filtered data
                ArrayList<gpay_list> filteredSongs = new ArrayList<>();

                if (dataModelArrayList != null && !dataModelArrayList.isEmpty()) {
                    //looping through existing elements
                    for (gpay_list s1 : dataModelArrayList) {
                        //if the existing elements contain the search input
                        if (s1.getUsername().toLowerCase().contains(text.toString().toLowerCase())) {
                            //adding the element to filtered list
                            filteredSongs.add(s1);
                        }
                    }
                }

                if (rvvAdapter != null) {
                    //calling a method of the adapter class and passing the filtered list
                    rvvAdapter.filterList(filteredSongs);
                }
            }
        });

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

                                // Extracting fee and status from the JSON object
                                String fee = dataobj.getString("fee");
                                String status = dataobj.getString("status");

                                // Only add to the list if the fee is 200 and the status is "Approve"
                                if (fee.equals("200") && status.equals("Approve")) {
                                    dataModelArrayList.add(new gpay_list(
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
                                            status,  // Status is already "Approve" here
                                            fee,
                                            dataobj.getString("feedate")
                                    ));
                                }
                            }

                            // Set up the RecyclerView with filtered data
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
                params.put("useremail", semail );
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
        rvvAdapter = new userdoctor_adapterlist(this, dataModelArrayList); // Fixed constructor call
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(rvvAdapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
}
