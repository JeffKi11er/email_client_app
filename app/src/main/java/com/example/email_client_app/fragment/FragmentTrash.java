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
import com.example.email_client_app.adapter.AdapterItem;
import com.example.email_client_app.adapter.AdapterTrash;
import com.example.email_client_app.item.ItemEmail;

import java.util.ArrayList;

public class FragmentTrash extends Fragment {
    private ArrayList<ItemEmail> emails;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trash,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        emails  = new ArrayList<>();
        emails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.ic_spam,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.ic_spam,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.ic_spam,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.ic_spam,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.ic_spam,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.ic_spam,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.ic_spam,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));

        recyclerView = getActivity().findViewById(R.id.rcl_trash);
        recyclerView.setAdapter(new AdapterTrash(getContext(),emails));
    }
}

