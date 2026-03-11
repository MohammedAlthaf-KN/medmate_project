package com.example.medmate.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.medmate.FeedbackActivity;
import com.example.medmate.Health;
import com.example.medmate.R;
import com.example.medmate.Selectdoctors;
import com.example.medmate.databinding.FragmentHomeBinding;
import com.example.medmate.doctorlistactivity;
import com.example.medmate.healthcareactivity;
import com.example.medmate.responseactivity;
import com.example.medmate.userdoctor_activitylist;
import com.example.medmate.userprescription_activitylist;

public class HomeFragment extends Fragment {
    CardView dbook,docbook1,payment,healthcaretips,consultedrcd,feedback,predict;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        predict=root.findViewById(R.id.docbook133);

        docbook1=root.findViewById(R.id.bres);
        dbook=root.findViewById(R.id.docbook);
        payment=root.findViewById(R.id.payment);
        healthcaretips=root.findViewById(R.id.healthtips);
        consultedrcd=root.findViewById(R.id.consultedrcd);
        feedback=root.findViewById(R.id.feedback1);

        predict.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), Health.class);
                startActivity(in);
            }
        });

        dbook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), Selectdoctors.class);
                startActivity(in);
            }
        });

        docbook1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), responseactivity.class);
                startActivity(in);
            }
        });
        payment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), userdoctor_activitylist.class);
                startActivity(in);
            }
        });
        healthcaretips.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), healthcareactivity.class);
                startActivity(in);
            }
        });
        consultedrcd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), userprescription_activitylist.class);
                startActivity(in);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), FeedbackActivity.class);
                startActivity(in);
            }
        });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}