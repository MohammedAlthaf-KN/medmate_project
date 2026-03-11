package com.example.medmate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class healthcareadapter extends RecyclerView.Adapter<healthcareadapter.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<healthcaremodel> dataModelArrayList;
    private Context c;

    public healthcareadapter(Context ctx, ArrayList<healthcaremodel> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(c);
        this.dataModelArrayList = dataModelArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.healthcaretips, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final healthcaremodel omodel = dataModelArrayList.get(position);
        // Picasso.get().load(config.imgurl3 + omodel.getPrescription()).into(holder.image);


        holder.docname.setText( dataModelArrayList.get(position).getTitle());
//        holder.userphn.setText( dataModelArrayList.get(position).getUserphone());
//        holder.petname.setText("Reason:"+ dataModelArrayList.get(position).getReason());
//        holder.date.setText( dataModelArrayList.get(position).getShopname());





        holder.producta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, healthcare_fullactivity.class);
                intent.putExtra("id",dataModelArrayList.get(position).getId());
                intent.putExtra("title",dataModelArrayList.get(position).getTitle() );
                intent.putExtra("tips", dataModelArrayList.get(position).getTips());
                intent.putExtra("description", dataModelArrayList.get(position).getDescription());
//                intent.putExtra("userphone",dataModelArrayList.get(position).getUserphone());
//                intent.putExtra("reason",dataModelArrayList.get(position).getReason());
//                intent.putExtra("prescription",dataModelArrayList.get(position).getPrescription());


                c.startActivity(intent);

                //                  if (!dataModelArrayList.get(position).getPrescription().equals("")) {
                //     Picasso.get.load(config.imgurl3+dataModelArrayList.get(position).getPrescription()).into(holder.image);
            }

        });

    }


    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }


    public void filterList(ArrayList<healthcaremodel> filteredSongs) {
        this.dataModelArrayList = filteredSongs;
        notifyDataSetChanged();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        public
        CardView producta;
        TextView docname,userphn,petname,date,time;
        ImageView image,delete;

        String id,cname;


        public MyViewHolder(View itemView) {
            super(itemView);
            producta =itemView.findViewById(R.id.bookcard8);
            docname = itemView.findViewById(R.id.bookdoc8);
//            userphn = itemView.findViewById(R.id.bookdoc2);
//            petname= itemView.findViewById(R.id.bookpet6);
//            date= itemView.findViewById(R.id.bookdate6);
//            time= itemView.findViewById(R.id.booktime2);
//            image=itemView.findViewById(R.id.bookimage);

        }

    }
}