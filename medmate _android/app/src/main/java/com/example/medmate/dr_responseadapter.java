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

public class dr_responseadapter extends RecyclerView.Adapter<dr_responseadapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<dr_responsemodel> dataModelArrayList;
    private Context c;

    public dr_responseadapter(Context ctx, ArrayList<dr_responsemodel> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(c);
        this.dataModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.dr_response, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final dr_responsemodel omodel = dataModelArrayList.get(position);


        holder.docname.setText(dataModelArrayList.get(position).getDocname());
        holder.username.setText( dataModelArrayList.get(position).getUsername());
        holder.date.setText("Date:"+dataModelArrayList.get(position).getDate());
        holder.time.setText("Time:"+dataModelArrayList.get(position).getTime());
        holder.status.setText(dataModelArrayList.get(position).getStatus());




        String statusText = omodel.getStatus();
        if ("Approve".equalsIgnoreCase(statusText)) {
            holder.status.setTextColor(ContextCompat.getColor(c, R.color.green1));  // Green color for "Approve"
        } else if ("Disapprove".equalsIgnoreCase(statusText)) {
            holder.status.setTextColor(ContextCompat.getColor(c, R.color.red));  // Red color for "Disapprove"
        } else {
            holder.status.setTextColor(ContextCompat.getColor(c, R.color.black));  // Default color if status is neither "Approve" nor "Disapprove"
        }


        holder.docview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, approvedbooking_fullactivity.class);
                intent.putExtra("docname",dataModelArrayList.get(position).getDocname());
                intent.putExtra("docemail", dataModelArrayList.get(position).getDocemail());
                intent.putExtra("username",dataModelArrayList.get(position).getUsername());
                intent.putExtra("usernumber",dataModelArrayList.get(position).getUsernumber());
                intent.putExtra("useremail",dataModelArrayList.get(position).getUseremail());
                intent.putExtra("userage",dataModelArrayList.get(position).getUserage());
                intent.putExtra("reason",dataModelArrayList.get(position).getReason());
                intent.putExtra("date",dataModelArrayList.get(position).getDate());
                intent.putExtra("time",dataModelArrayList.get(position).getTime());
                c.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public void filterList(ArrayList<dr_responsemodel> filteredSongs) {
        this.dataModelArrayList = filteredSongs;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView docview;
        TextView  docname,username, date, time, status;
        ImageView image, delete;
        Button pay;
        String id, cname;

        public MyViewHolder(View itemView) {
            super(itemView);
            docview = itemView.findViewById(R.id.dd);
            docname = itemView.findViewById(R.id.docname2);
            username = itemView.findViewById(R.id.username);
            date = itemView.findViewById(R.id.date2);
            time = itemView.findViewById(R.id.booktime);
            image = itemView.findViewById(R.id.bookimage);
            status = itemView.findViewById(R.id.status);

        }
    }
}