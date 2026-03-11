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

public class doctoradapter extends RecyclerView.Adapter<doctoradapter.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<doctormodel> dataModelArrayList;
    private Context c;


    public doctoradapter(Context ctx, ArrayList<doctormodel> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(c);
        this.dataModelArrayList = dataModelArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.doctorlist, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.adname.setText("Name:"+dataModelArrayList.get(position).getName());
        holder.adexp.setText("Specialization:"+dataModelArrayList.get(position).getSpeciality());




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





                Intent intent = new Intent(c, doctorfullactivity.class);
                intent.putExtra("name",dataModelArrayList.get(position).getName());
                intent.putExtra("speciality",dataModelArrayList.get(position).getSpeciality());
                intent.putExtra("experience", dataModelArrayList.get(position).getExperience());
                intent.putExtra("email", dataModelArrayList.get(position).getEmail());
                intent.putExtra("hospital",dataModelArrayList.get(position).getHospital());
                c.startActivity(intent);
            }

        });
//
//
//




    }


    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }


    public void filterList(ArrayList<doctormodel> filteredSongs) {
        this.dataModelArrayList = filteredSongs;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        CardView cardView1;
        TextView adname, adexp;




        @SuppressLint("WrongViewCast")
        public MyViewHolder(View itemView) {

            super(itemView);

            cardView1 = itemView.findViewById(R.id.advview);
            adname = itemView.findViewById(R.id.viewname);
            adexp = itemView.findViewById(R.id.viewctype);





        }

    }
}
