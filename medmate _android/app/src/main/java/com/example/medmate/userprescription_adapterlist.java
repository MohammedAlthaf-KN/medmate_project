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


import com.example.medmate.R;
import com.example.medmate.doctormodel;

import java.util.ArrayList;

public class userprescription_adapterlist extends RecyclerView.Adapter<userprescription_adapterlist.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<userprescription_modellist> dataModelArrayList;
    private Context c;


    public userprescription_adapterlist(Context ctx, ArrayList<userprescription_modellist> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(c);
        this.dataModelArrayList = dataModelArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.userprescription_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.doctorname.setText(dataModelArrayList.get(position).getDoctorname());
        holder.username.setText("Patient Name: "+dataModelArrayList.get(position).getUsername());
//        );holder.prescription.setText("Prescription:"+dataModelArrayList.get(position).getPrescription()
        holder.date.setText("Added on:"+dataModelArrayList.get(position).getDate());




//        holder.book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(c, docbooking.class);
//                intent.putExtra("name",dataModelArrayList.get(position).getName());
//                intent.putExtra("email", dataModelArrayList.get(position).getEmail());
//                c.startActivity(intent);
//            }
//        });
//
        holder.cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                Intent intent = new Intent(c, userprecriptionfullactivity.class);
                intent.putExtra("id",dataModelArrayList.get(position).getId());
                intent.putExtra("doctorname",dataModelArrayList.get(position).getDoctorname());
                intent.putExtra("doctorphone", dataModelArrayList.get(position).getDoctorphone());
                intent.putExtra("username", dataModelArrayList.get(position).getUsername());
                intent.putExtra("userphone",dataModelArrayList.get(position).getUserphone());
                intent.putExtra("prescription",dataModelArrayList.get(position).getPrescription());
                intent.putExtra("date",dataModelArrayList.get(position).getDate());


                c.startActivity(intent);
            }

        });
////
//
//




    }


    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }


    public void filterList(ArrayList<userprescription_modellist> filteredSongs) {
        this.dataModelArrayList = filteredSongs;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        CardView cardView1;
        TextView doctorname,username,date;




        @SuppressLint("WrongViewCast")
        public MyViewHolder(View itemView) {

            super(itemView);

            cardView1 = itemView.findViewById(R.id.usrp);
            doctorname = itemView.findViewById(R.id.uspdn);
            username = itemView.findViewById(R.id.usppn);
//            prescription = itemView.findViewById(R.id.viewctype);
            date = itemView.findViewById(R.id.uspdate);





        }

    }
}
