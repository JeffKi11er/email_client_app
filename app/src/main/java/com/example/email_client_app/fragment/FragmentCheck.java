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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.email_client_app.R;
import com.example.email_client_app.activity.DetailActivity;
import com.example.email_client_app.adapter.AdapterItem;
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemEmail;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

public class FragmentCheck extends Fragment implements ItemListener{
    private RecyclerView rclEmails;
    private String userEmail;
    private String userPasswords;
    private ArrayList<ItemEmail> emails;
    private String title;
    private TextView tvTitle;
    private String address_to_string;
    private String received_date;
    private String subject;
    private String content;
    private LinearLayout lnPromotion;
    private LinearLayout lnSocial;
    private ArrayList<String>messages = new ArrayList<>();
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
        lnPromotion = getActivity().findViewById(R.id.ln_promotions);
        lnSocial = getActivity().findViewById(R.id.ln_social);
        if (title!=null && title.equals("All Inboxes")){
            lnPromotion.setVisibility(View.GONE);
            lnSocial.setVisibility(View.GONE);
        }else{
            lnPromotion.setVisibility(View.VISIBLE);
            lnSocial.setVisibility(View.VISIBLE);
        }
        tvTitle.setText(title);
        AdapterItem adapterItem = new AdapterItem(getContext(), emails);
        rclEmails.setAdapter(adapterItem);
        adapterItem.setListener(this);
        new MyAsynk().execute();
    }

    @Override
    public void onClick(int position) {

        Intent intent = new Intent(getActivity(), DetailActivity.class);
//        intent.putStringArrayListExtra("message",messages);
        startActivity(intent);
    }

    @Override
    public void onLongClick(int position) {

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
                javax.mail.Message msg = inbox.getMessage(inbox.getMessageCount());
                javax.mail.Address[] in = msg.getFrom();
                for (javax.mail.Address address : in) {
                    Log.i(getClass().getName(),"FROM:" + address.toString());
                    address_to_string =  address.toString();
                    messages.add(address_to_string);
                }
                Multipart mp = (Multipart) msg.getContent();
                BodyPart bp = mp.getBodyPart(0);
                Log.i(getClass().getName(),"SENT DATE:" + msg.getSentDate());
                received_date = msg.getSentDate().toString();
                messages.add(received_date);
                Log.i(getClass().getName(),"SUBJECT:" + msg.getSubject().toString());
                subject = msg.getSubject();
                messages.add(subject);
                Log.i(getClass().getName(),"CONTENT:" + bp.getContent());
                content = bp.getContent().toString();
                messages.add(content);
            } catch (Exception mex) {
                mex.printStackTrace();
            }
            return null;
        }
    }
}

