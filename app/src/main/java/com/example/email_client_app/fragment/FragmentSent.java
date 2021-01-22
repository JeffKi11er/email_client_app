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
import com.example.email_client_app.adapter.ImportantAdapter;
import com.example.email_client_app.adapter.SentAdapter;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.item.ItemEmail;
import com.example.email_client_app.item.ItemSentEmail;

import java.util.ArrayList;

public class FragmentSent extends Fragment {
    private RecyclerView sent_recycler;
    private ArrayList<ItemSentEmail> sent = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Sent = inflater.inflate(R.layout.fragment_sent, container, false);
        return Sent;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sent = BrainResource.getSentEmails();
        sent_recycler = getActivity().findViewById(R.id.rcl_sent);
        sent_recycler.setAdapter(new SentAdapter(getContext(), sent));
    }
}
