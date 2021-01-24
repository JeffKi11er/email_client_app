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
import com.example.email_client_app.adapter.AdapterSnoozed;
import com.example.email_client_app.adapter.AdapterStarred;
import com.example.email_client_app.adapter.AdapterSnoozed;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.item.ItemEmail;

import java.util.ArrayList;

public class FragmentSnoozed extends Fragment {
    private ArrayList<ItemEmail>emails = new ArrayList<>();
    private RecyclerView rclsnoozed;
    private SwipeRefreshLayout swipeRefreshsnoozed;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_snoozed,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        emails  = BrainResource.getEmails();
        rclsnoozed = getActivity().findViewById(R.id.rcl_snoozed);
        rclsnoozed.setAdapter(new AdapterSnoozed(getContext(),emails));
        swipeRefreshsnoozed = getActivity().findViewById(R.id.swipe_to_snoozed);
        swipeRefreshsnoozed.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(),"Nothing to show",Toast.LENGTH_LONG).show();
                swipeRefreshsnoozed.setRefreshing(false);
            }
        });
    }
}
