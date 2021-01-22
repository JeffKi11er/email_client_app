package com.example.email_client_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.email_client_app.R;
import com.example.email_client_app.adapter.AdapterSchedule;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.item.ItemEmail;
import com.example.email_client_app.item.ItemSchedule;

import java.util.ArrayList;

public class FragmentSchedule extends Fragment {
    private RecyclerView schedule_recycler;
    private ArrayList<ItemSchedule> schedules = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View schedule_view = inflater.inflate(R.layout.fragment_schedule, container, false);
        return schedule_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        schedules = BrainResource.getScheduleEmails();
        schedule_recycler = getActivity().findViewById(R.id.schedule_recycler);
        schedule_recycler.setAdapter(new AdapterSchedule(getContext(), schedules));
    }
}
