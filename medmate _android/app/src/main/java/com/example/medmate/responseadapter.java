package com.example.medmate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class responseadapter extends RecyclerView.Adapter<responseadapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<responsemodel> dataModelArrayList;
    private Context c;

    public responseadapter(Context ctx, ArrayList<responsemodel> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(c);
        this.dataModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.response, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final responsemodel omodel = dataModelArrayList.get(position);

        holder.docname.setText(omodel.getDocname());
        holder.username.setText(omodel.getUsername());
        holder.date.setText("Date: " + omodel.getDate());
        holder.time.setText("Time: " + omodel.getTime());
        holder.status.setText(omodel.getStatus());

        String statusText = omodel.getStatus();

        if ("Approve".equalsIgnoreCase(statusText)) {
            holder.status.setTextColor(ContextCompat.getColor(c, R.color.green1));  // Green color for "Approve"
            holder.pay.setVisibility(View.VISIBLE);  // Show pay button
        } else {
            holder.status.setTextColor(ContextCompat.getColor(c, R.color.red));  // Red color for "Disapprove"
            holder.pay.setVisibility(View.GONE);  // Hide pay button
        }

        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the pay button click
                Toast.makeText(c, "Proceeding to payment for " + omodel.getDocname(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(c, response_fullactivity.class);
                intent.putExtra("id", omodel.getId());
                intent.putExtra("docname", omodel.getDocname());
                intent.putExtra("docemail", omodel.getDocemail());
                intent.putExtra("username", omodel.getUsername());
                intent.putExtra("usernumber", omodel.getUsernumber());
                intent.putExtra("useremail", omodel.getUseremail());
                intent.putExtra("reason", omodel.getReason());
                intent.putExtra("date", omodel.getDate());
                intent.putExtra("time", omodel.getTime());

                c.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public void filterList(ArrayList<responsemodel> filteredSongs) {
        this.dataModelArrayList = filteredSongs;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView producta;
        TextView  docname,username, date, time, status;
        ImageView image, delete;
        Button pay;
        String id, cname;

        public MyViewHolder(View itemView) {
            super(itemView);
            docname = itemView.findViewById(R.id.docname2);
            username = itemView.findViewById(R.id.username);
            date = itemView.findViewById(R.id.date2);
            time = itemView.findViewById(R.id.booktime);
            image = itemView.findViewById(R.id.bookimage);
            status = itemView.findViewById(R.id.status);
            pay = itemView.findViewById(R.id.pay);
        }
    }
}