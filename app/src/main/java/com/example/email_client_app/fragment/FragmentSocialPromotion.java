package com.example.email_client_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.email_client_app.R;
import com.example.email_client_app.activity.AdsActivity;
import com.example.email_client_app.adapter.AdapterSocial;
import com.example.email_client_app.adapter.AdapterSocial;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemSocial;

import java.util.ArrayList;

public class FragmentSocialPromotion extends Fragment implements ItemListener {
    private ArrayList<ItemSocial>socials = new ArrayList<>();
    private String title;
    private TextView tvTitle;
    private RecyclerView rclsocial;
    private SwipeRefreshLayout swipeRefreshsocial;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ads,container,false);
        if (getArguments()!=null){
            title = getArguments().getString("title_p");
        }
        socials = BrainResource.getSocialItems();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rclsocial = getActivity().findViewById(R.id.rcl_promotion_social);
        tvTitle = getActivity().findViewById(R.id.tv_social_title);
        AdapterSocial adapterSocial = new AdapterSocial(socials,getContext());
        rclsocial.setAdapter(adapterSocial);
        adapterSocial.setListener(this);
        tvTitle.setText(title);
        swipeRefreshsocial = getActivity().findViewById(R.id.swipe_to_ads);
        rclsocial.setAdapter(new AdapterSocial(socials, getContext()));
        swipeRefreshsocial.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(),"Nothing to show",Toast.LENGTH_LONG).show();
                swipeRefreshsocial.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(int position) {
        startActivity(new Intent(getActivity(), AdsActivity.class));
        getActivity().finish();
    }

    @Override
    public void onLongClick(int position) {

    }
}
