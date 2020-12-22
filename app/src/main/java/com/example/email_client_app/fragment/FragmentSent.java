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
import com.example.email_client_app.item.ItemEmail;
import com.example.email_client_app.item.ItemSentEmail;

import java.util.ArrayList;

public class FragmentSent extends Fragment {
    private RecyclerView sent_recycler;
    private ArrayList<ItemSentEmail> sent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Sent = inflater.inflate(R.layout.fragment_sent, container, false);
        return Sent;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sent = new ArrayList<>();
        sent.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.cat,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề",6));
        sent.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.cat,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề",4));
        sent.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.cat,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề",3));
        sent.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.cat,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề",2));
        sent.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.cat,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề",4));
        sent.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.cat,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề",2));
        sent.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.cat,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề",3));

        sent_recycler = getActivity().findViewById(R.id.rcl_sent);
        sent_recycler.setAdapter(new SentAdapter(getContext(), sent));
    }
}
