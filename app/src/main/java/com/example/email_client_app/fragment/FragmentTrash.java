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
import com.example.email_client_app.adapter.AdapterItem;
import com.example.email_client_app.adapter.AdapterTrash;
import com.example.email_client_app.adapter.AdapterTrash;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.item.ItemEmail;

import java.util.ArrayList;

public class FragmentTrash extends Fragment {
    private ArrayList<ItemEmail> emails = new ArrayList<>();
    private RecyclerView rcltrash;
    private SwipeRefreshLayout swipeRefreshtrash;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trash,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        emails  = BrainResource.getEmails();
        rcltrash = getActivity().findViewById(R.id.rcl_trash);
        rcltrash.setAdapter(new AdapterTrash(getContext(),emails));
        swipeRefreshtrash = getActivity().findViewById(R.id.swipe_to_trash);
        rcltrash.setAdapter(new AdapterTrash(getContext(),emails));
        swipeRefreshtrash.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(),"Nothing to show",Toast.LENGTH_LONG).show();
                swipeRefreshtrash.setRefreshing(false);
            }
        });
    }
}

