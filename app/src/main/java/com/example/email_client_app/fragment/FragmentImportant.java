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
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.item.ItemEmail;

import java.util.ArrayList;

public class FragmentImportant extends Fragment {
    private RecyclerView important_recycler;
    private ArrayList<ItemEmail> important = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Important = inflater.inflate(R.layout.fragment_importance, container, false);
        return Important;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        important = BrainResource.getEmails();
        important_recycler = getActivity().findViewById(R.id.rcl_important);
        important_recycler.setAdapter(new ImportantAdapter(getContext(), important));
    }
}
