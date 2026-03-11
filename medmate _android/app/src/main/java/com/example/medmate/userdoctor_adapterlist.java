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

public class userdoctor_adapterlist extends RecyclerView.Adapter<userdoctor_adapterlist.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<gpay_list> dataModelArrayList;
    private Context c;

    public userdoctor_adapterlist(Context ctx, ArrayList<gpay_list> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(c);
        this.dataModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.gpay_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final gpay_list omodel = dataModelArrayList.get(position);


        holder.docname.setText(dataModelArrayList.get(position).getDocname());
        holder.username.setText( dataModelArrayList.get(position).getUsername());
        holder.date.setText("Date:"+dataModelArrayList.get(position).getDate());
        holder.time.setText("Time:"+dataModelArrayList.get(position).getTime());
//        holder.status.setText(dataModelArrayList.get(position).getStatus());




//        String statusText = omodel.getStatus();
//        if ("Approve".equalsIgnoreCase(statusText)) {
//            holder.status.setTextColor(ContextCompat.getColor(c, R.color.green1));  // Green color for "Approve"
//        } else if ("Disapprove".equalsIgnoreCase(statusText)) {
//            holder.status.setTextColor(ContextCompat.getColor(c, R.color.red));  // Red color for "Disapprove"
//        } else {
//            holder.status.setTextColor(ContextCompat.getColor(c, R.color.black));  // Default color if status is neither "Approve" nor "Disapprove"
//        }
//        holder.pay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // You can handle payment logic here, like navigating to a payment screen or processing payment
//                Toast.makeText(c, "Proceeding to payment for " + omodel.getDocname(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(c, response_fullactivity.class);
//                intent.putExtra("id",dataModelArrayList.get(position).getId());
//                intent.putExtra("docname",dataModelArrayList.get(position).getDocname() );
//                intent.putExtra("docname", dataModelArrayList.get(position).getDocemail());
//                intent.putExtra("username", dataModelArrayList.get(position).getUsername());
//                intent.putExtra("usernumber",dataModelArrayList.get(position).getUsernumber());
//                intent.putExtra("useremail",dataModelArrayList.get(position).getUseremail());
//                intent.putExtra("reason",dataModelArrayList.get(position).getReason());
//                intent.putExtra("date",dataModelArrayList.get(position).getDate());
//                intent.putExtra("time",dataModelArrayList.get(position).getTime());
//
//
//
//                c.startActivity(intent);
//
//
//                // Intent intent = new Intent(c, PaymentActivity.class);
//                // intent.putExtra("docId", omodel.getId());
//                // c.startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public void filterList(ArrayList<gpay_list> filteredSongs) {
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
            docname = itemView.findViewById(R.id.gpaypname);
            username = itemView.findViewById(R.id.gpaypreason);
            date = itemView.findViewById(R.id.gpaypdate);
            time = itemView.findViewById(R.id.gpayptime);
//            image = itemView.findViewById(R.id.bookimage);
//            status = itemView.findViewById(R.id.status);
            pay = itemView.findViewById(R.id.pay);
        }
    }
}