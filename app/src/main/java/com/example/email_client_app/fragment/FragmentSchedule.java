package com.example.email_client_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.email_client_app.R;
import com.example.email_client_app.adapter.AdapterSchedule;
import com.example.email_client_app.adapter.AdapterSchedule;
import com.example.email_client_app.adapter.AdapterSchedule;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.item.ItemEmail;
import com.example.email_client_app.item.ItemSchedule;

import java.util.ArrayList;

public class FragmentSchedule extends Fragment {
    private RecyclerView schedule_recycler;
    private ArrayList<ItemSchedule> schedule = new ArrayList<>();
    private RecyclerView rclSchedule;
    private SwipeRefreshLayout swipeRefreshSchedule;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View schedule_view = inflater.inflate(R.layout.fragment_schedule, container, false);
        return schedule_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        schedule = BrainResource.getScheduleEmails();
        schedule_recycler = getActivity().findViewById(R.id.schedule_recycler);
        schedule_recycler.setAdapter(new AdapterSchedule(getContext(), schedule));
        swipeRefreshSchedule = getActivity().findViewById(R.id.swipe_to_schedule);
        rclSchedule.setAdapter(new AdapterSchedule(getContext(),schedule));
        swipeRefreshSchedule.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(),"Nothing to show",Toast.LENGTH_LONG).show();
                swipeRefreshSchedule.setRefreshing(false);
            }
        });
    }
}
