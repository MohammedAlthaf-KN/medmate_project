package com.example.medmate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

//import com.mashood.kaudisorders.R;
//import com.mashood.kaudisorders.disorder.DisorderListActivity;
//import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class bookingviewadapter extends RecyclerView.Adapter<bookingviewadapter.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<bookingviewmodel> dataModelArrayList;
    private Context c;


    public bookingviewadapter(Context ctx, ArrayList<bookingviewmodel> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(c);
        this.dataModelArrayList = dataModelArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.bookingviewlist, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.un.setText("Name:"+dataModelArrayList.get(position).getUsername());
        holder.reas.setText("Reason:"+dataModelArrayList.get(position).getReason());
        holder.da.setText("Date:"+dataModelArrayList.get(position).getDate());
        holder.ti.setText("Time:"+dataModelArrayList.get(position).getTime());



        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, bookingviewfullactivity.class);
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


    public void filterList(ArrayList<bookingviewmodel> filteredSongs) {
        this.dataModelArrayList = filteredSongs;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        CardView card;
        TextView un, reas,da,ti;




        @SuppressLint("WrongViewCast")
        public MyViewHolder(View itemView) {

            super(itemView);

            card = itemView.findViewById(R.id.bookingview);
            un = itemView.findViewById(R.id.uname1);
            reas = itemView.findViewById(R.id.ureason1);
            da = itemView.findViewById(R.id.date1);
            ti = itemView.findViewById(R.id.time1);




        }

    }
}
