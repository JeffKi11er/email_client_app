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
import com.example.email_client_app.item.ItemEmail;
import com.example.email_client_app.item.ItemSchedule;

import java.util.ArrayList;

public class FragmentSchedule extends Fragment {
    private RecyclerView schedule_recycler;
    private ArrayList<ItemSchedule> schedules;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View schedule_view = inflater.inflate(R.layout.fragment_schedule, container, false);
        return schedule_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        schedules = new ArrayList<>();

        schedules.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));

        schedule_recycler = getActivity().findViewById(R.id.schedule_recycler);
        schedule_recycler.setAdapter(new AdapterSchedule(getContext(), schedules));
    }
}
