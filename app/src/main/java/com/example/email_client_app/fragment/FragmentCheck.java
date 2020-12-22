package com.example.email_client_app.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.email_client_app.R;
import com.example.email_client_app.adapter.AdapterItem;
import com.example.email_client_app.item.ItemEmail;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import androidx.recyclerview.widget.ItemTouchHelper;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class FragmentCheck extends Fragment {
    private RecyclerView rclEmails;
    private String userEmail;
    private String userPasswords;
    private ArrayList<ItemEmail> emails;
    private String title;
    private TextView tvTitle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check, container, false);
        if (getArguments()!=null){
            title = getArguments().getString("title");
        }
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences sharedPreferencesEmail = getActivity().getSharedPreferences("user_email", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferencesPasswords = getActivity().getSharedPreferences("user_passwords", Context.MODE_PRIVATE);
        userEmail = sharedPreferencesEmail.getString("user_email", "");
        userPasswords = sharedPreferencesPasswords.getString("user_passwords", "");
        emails = new ArrayList<>();
        emails.add(new ItemEmail("Nguyen Cong Thanh", "15/12/2020", R.drawable.streamer, true, "Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Nguyen An Thiet", "16/12/2020", R.drawable.streamer, true, "Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Vinh", "15/12/2020", R.drawable.streamer, true, "Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Hieu", "15/12/2020", R.drawable.streamer, true, "Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Hieu", "15/12/2020", R.drawable.streamer, true, "Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Nguyen Cong Thanh", "15/12/2020", R.drawable.streamer, true, "Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        emails.add(new ItemEmail("Nguyen Cong Thanh", "15/12/2020", R.drawable.streamer, true, "Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        rclEmails = getActivity().findViewById(R.id.rcl_emails);
        tvTitle = getActivity().findViewById(R.id.tv_status);
        tvTitle.setText(title);
        rclEmails.setAdapter(new AdapterItem(getContext(), emails));
               new MyAsynk().execute();
    }

    public class MyAsynk extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            try {
                Session session = Session.getInstance(props, null);
                Store store = session.getStore();
                store.connect("imap.gmail.com", userEmail, userPasswords);
                Folder inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_ONLY);
//                javax.mail.Message []msg= inbox.getMessages();
//                for (Message message:msg) {
////                    Log.e(getClass().getName(),message.getContent().toString());
//                    Address[] address = message.getFrom();
//                    for (Address address1:address) {
//                        Log.e(getClass().getName(),address1.toString() + message.getReceivedDate());
//                    }
//                }
                javax.mail.Message msg = inbox.getMessage(inbox.getMessageCount());
                javax.mail.Address[] in = msg.getFrom();
                for (javax.mail.Address address : in) {
                    Log.e(getClass().getName(), address.toString());
                }
                Multipart mp = (Multipart) msg.getContent();
                BodyPart bp = mp.getBodyPart(0);
//                System.out.println("SENT DATE:" + msg.getSentDate());
//                System.out.println("SUBJECT:" + msg.getSubject());
//                System.out.println("CONTENT:" + bp.getContent());
                String date = msg.getSentDate().toString();
                String subject = msg.getSubject().toString();
                String content = bp.getContent().toString();
                Log.e(getClass().getName(), subject + content + date);
//                emails.add(new ItemEmail("",date,R.drawable.cat,true,subject,content));
//                rclEmails.setAdapter(new AdapterItem(getContext(),emails));
            } catch (Exception mex) {
                mex.printStackTrace();
            }
            return null;
        }
    }
}

