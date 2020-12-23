package com.example.email_client_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.email_client_app.R;
import com.example.email_client_app.activity.AdsActivity;
import com.example.email_client_app.adapter.AdapterSocial;
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemSocial;

import java.util.ArrayList;

public class FragmentSocialPromotion extends Fragment implements ItemListener {
    private RecyclerView rclListSocial;
    private ArrayList<ItemSocial>socials;
    private String title;
    private TextView tvTitle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ads,container,false);
        if (getArguments()!=null){
            title = getArguments().getString("title_p");
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        socials = new ArrayList<>();
        socials.add(new ItemSocial("FaceBook","23/12/2012",R.drawable.streamer,true,"You received " +
                "a message","Get 1000+ messages from your scocial media",R.drawable.camera,false));
        socials.add(new ItemSocial("FaceBook","23/12/2012",R.drawable.streamer,true,"You received " +
                "a message","Get 1000+ messages from your scocial media",R.drawable.camera,true));
        socials.add(new ItemSocial("FaceBook","23/12/2012",R.drawable.streamer,true,"You received " +
                "a message","Get 1000+ messages from your scocial media",R.drawable.camera,true));
        socials.add(new ItemSocial("FaceBook","23/12/2012",R.drawable.streamer,true,"You received " +
                "a message","Get 1000+ messages from your scocial media",R.drawable.camera,false));
        socials.add(new ItemSocial("FaceBook","23/12/2012",R.drawable.streamer,true,"You received " +
                "a message","Get 1000+ messages from your scocial media",R.drawable.camera,false));
        socials.add(new ItemSocial("FaceBook","23/12/2012",R.drawable.streamer,true,"You received " +
                "a message","Get 1000+ messages from your scocial media",R.drawable.camera,true));
        socials.add(new ItemSocial("FaceBook","23/12/2012",R.drawable.streamer,true,"You received " +
                "a message","Get 1000+ messages from your scocial media",R.drawable.camera,false));
        socials.add(new ItemSocial("FaceBook","23/12/2012",R.drawable.streamer,true,"You received " +
                "a message","Get 1000+ messages from your scocial media",R.drawable.camera,false));
        socials.add(new ItemSocial("FaceBook","23/12/2012",R.drawable.streamer,true,"You received " +
                "a message","Get 1000+ messages from your scocial media",R.drawable.camera,false));
        socials.add(new ItemSocial("FaceBook","23/12/2012",R.drawable.streamer,true,"You received " +
                "a message","Get 1000+ messages from your scocial media",R.drawable.camera,true));
        socials.add(new ItemSocial("FaceBook","23/12/2012",R.drawable.streamer,true,"You received " +
                "a message","Get 1000+ messages from your scocial media",R.drawable.camera,false));
        rclListSocial = getActivity().findViewById(R.id.rcl_promotion_social);
        tvTitle = getActivity().findViewById(R.id.tv_social_title);
        AdapterSocial adapterSocial = new AdapterSocial(socials,getContext());
        adapterSocial.setListener(this);
        rclListSocial.setAdapter(adapterSocial);
        tvTitle.setText(title);
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
