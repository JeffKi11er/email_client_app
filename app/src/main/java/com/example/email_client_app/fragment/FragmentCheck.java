package com.example.email_client_app.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.email_client_app.R;
import com.example.email_client_app.activity.DetailActivity;
import com.example.email_client_app.adapter.AdapterItem;
import com.example.email_client_app.helper.AppConstants;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemEmail;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.pop3.POP3Store;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.app.Activity.RESULT_OK;
import static com.example.email_client_app.helper.AppConstants.REQUEST_CODE;
import static com.example.email_client_app.helper.AppConstants.RESULT_DELETE;
import static com.example.email_client_app.helper.AppConstants.RESULT_STORED;
import static com.example.email_client_app.helper.AppConstants.RESULT_UNSEEN;

public class FragmentCheck extends Fragment implements ItemListener{
    private RecyclerView rclEmails;
    private String userEmail;
    private String userPasswords;
    private ArrayList<ItemEmail> emails = new ArrayList<>();
    private String title;
    private TextView tvTitle;
    private String address_to_string="";
    private String received_date="";
    private String subject="";
    private String content="";
    private LinearLayout lnPromotion;
    private LinearLayout lnSocial;
    private ArrayList<String>messages = new ArrayList<>();
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefresh;
    private ItemEmail emailTransfer;
    private AdapterItem adapterItem;
    String protocol = "imap";
    String host = "imap.gmail.com";
    String port = "993";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check, container, false);
        if (getArguments()!=null){
            title = getArguments().getString("title");
        }else {
            title = "Primary";
        }
//        dataGet2();
        dataFinish();
//        dataReadAll();
        return view;
    }
    public Folder getAlternativeFolder(Folder[] folders, String folder_name) throws MessagingException {
        for (Folder folder : folders) {
            IMAPFolder imapFolder = (IMAPFolder) folder;
            for(String attribute : imapFolder.getAttributes()) {
                if (attribute.equals("\\"+folder_name)) {
                    return folder;
                }
            }
        }
        return null;
    }
    private void dataFinish() {
        new AsyncTask<String, Void, Void>() {
            ///doInBackground
            AlertDialog alertDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_progress, null);
                dialogBuilder.setView(dialogView);
                alertDialog = dialogBuilder.create();
                alertDialog.show();
            }

            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(String... values) {
//                Properties props = new Properties();
//                props.setProperty("mail.store.protocol", "imaps");
                Properties properties = getServerProperties(protocol, host, port);
                Session session = Session.getDefaultInstance(properties,null);
                try {
//                    Session session = Session.getInstance(props, null);
//                    Store store = session.getStore();
//                    store.connect("imap.gmail.com", userEmail, userPasswords);
                    Store store = session.getStore(protocol);
                    store.connect(userEmail, userPasswords);
//                    Folder inbox = store.getFolder("INBOX");
                    Folder[] f = store.getDefaultFolder().list("*");
                    for (Folder fd:f) {
                        Log.i(getClass().getName(),fd.getName());
                    }
//                    IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");
//                    Folder inbox = getAlternativeFolder(f,"INBOX");
                    Folder inbox = f[7];
                    inbox.open(Folder.READ_ONLY);
//                    Message msg = inbox.getMessage(inbox.getMessageCount());
                    Message[] msg = inbox.getMessages();
                    for (int i = 0; i < msg.length ; i++) {
                        String from="";
                        if (msg[i].getReplyTo().length >= 1) {
                            from = msg[i].getReplyTo()[0].toString();
                        }
                        else if (msg[i].getFrom().length >= 1) {
                            from = msg[i].getFrom()[0].toString();
                        }
                        Multipart mp = (Multipart) msg[i].getContent();
                        BodyPart bp = mp.getBodyPart(i);
//                        Address[]in = msg[i].getFrom();
//                        javax.mail.Address address = in[0];
                        Log.i(getClass().getName(),"SENT DATE:" + msg[i].getSentDate());
                        received_date = msg[i].getSentDate().toString();
                        messages.add(received_date);
                        Log.i(getClass().getName(),"SUBJECT:" + msg[i].getSubject());
                        subject = msg[i].getSubject();
                        messages.add(subject);
                        Log.i(getClass().getName(),"CONTENT:" + bp.getContent());
                        content = bp.getContent().toString();
                        messages.add(content);
                        Log.i(getClass().getName(),"FROM:" + from);
                        emails.add(new ItemEmail(from,received_date,R.drawable.streamer,false,subject,content,false,
                                "","inbox",false));
                    }
//                    Address[] in = msg.getFrom();
//                    for (javax.mail.Address address : in) {
//                        Log.i(getClass().getName(),"FROM:" + address.toString());
//                        address_to_string =  address.toString();
//                        messages.add(address_to_string);
                    inbox.close(false);
                    store.close();
//                    }
                } catch (Exception mex) {
                    mex.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                adapterItem.setItems(emails);
                adapterItem.notifyDataSetChanged();
                alertDialog.dismiss();
            }
        }.execute();
    }
    private Properties getServerProperties(String protocol, String host,
                                           String port) {
        Properties properties = new Properties();

        // server setting
        properties.put(String.format("mail.%s.host", protocol), host);
        properties.put(String.format("mail.%s.port", protocol), port);

        // SSL setting
        properties.setProperty(
                String.format("mail.%s.socketFactory.class", protocol),
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty(
                String.format("mail.%s.socketFactory.fallback", protocol),
                "false");
        properties.setProperty(
                String.format("mail.%s.socketFactory.port", protocol),
                String.valueOf(port));

        return properties;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences sharedPreferencesEmail = getActivity().getSharedPreferences("user_email", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferencesPasswords = getActivity().getSharedPreferences("user_passwords", Context.MODE_PRIVATE);
        userEmail = sharedPreferencesEmail.getString("user_email", "");
        userPasswords = sharedPreferencesPasswords.getString("user_passwords", "");
        //emails = BrainResource.getEmails();
        rclEmails = getActivity().findViewById(R.id.rcl_emails);
        tvTitle = getActivity().findViewById(R.id.tv_status);
        lnPromotion = getActivity().findViewById(R.id.ln_promotions);
        lnSocial = getActivity().findViewById(R.id.ln_social);
        progressBar = (ProgressBar)getActivity().findViewById(R.id.spin_kit);
        swipeRefresh = getActivity().findViewById(R.id.swipe_to_refresh);
        Sprite doubleBounce = new FoldingCube();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.GONE);
        lnPromotion.setVisibility(View.GONE);
        lnSocial.setVisibility(View.GONE);
        if (title!=null && title.equals("All Inboxes")){
            lnPromotion.setVisibility(View.GONE);
            lnSocial.setVisibility(View.GONE);
        }else{
            lnPromotion.setVisibility(View.VISIBLE);
            lnSocial.setVisibility(View.VISIBLE);
        }
        tvTitle.setText(title);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(),"Nothing to show",Toast.LENGTH_LONG).show();
                dataFinish();
                swipeRefresh.setRefreshing(false);
            }
        });
        adapterItem = new AdapterItem(getContext(), emails);
        rclEmails.setAdapter(adapterItem);
        adapterItem.setListener(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rclEmails);
    }
    ItemEmail itemEmail = null;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
//                    progressBar.setVisibility(View.VISIBLE);
                    itemEmail = emails.get(position);
                    emails.remove(position);
                    rclEmails.setAdapter(new AdapterItem(getContext(),emails));
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
// ...Irrelevant code for customizing the buttons and title
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.dialog_progress, null);
                    dialogBuilder.setView(dialogView);
                    AlertDialog alertDialog = dialogBuilder.create();
                    new Thread() {
                        public void run() {
                            try{
                                Thread.sleep(2000);
                            }
                            catch (Exception e) {
                                Log.e("tag", e.getMessage());
                            }
                            // dismiss the progress dialog
                            alertDialog.dismiss();
                        }
                    }.start();
                    alertDialog.show();
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(),R.color.green))
                    .addSwipeLeftActionIcon(R.drawable.ic_all_inb_white)
                    .create()
                    .decorate();
            View itemView = viewHolder.itemView;
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        emailTransfer = emails.get(position);
        intent.putExtra("name",emailTransfer.getName());
        intent.putExtra("date",emailTransfer.getDate());
        intent.putExtra("imgProfile",emailTransfer.getImgProfile());
        intent.putExtra("starred",emailTransfer.isStarred());
        intent.putExtra("subject",emailTransfer.getSubject());
        intent.putExtra("description",emailTransfer.getDescription());
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onLongClick(int position) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE){
            if (resultCode == RESULT_DELETE){
                emails.remove(emailTransfer);
                AdapterItem adapter = new AdapterItem(getContext(),emails);
                rclEmails.setAdapter(adapter);
                adapter.setListener(this);
            }
            if (resultCode == RESULT_UNSEEN){
                emailTransfer.setStarred(true);
                AdapterItem adapter = new AdapterItem(getContext(),emails);
                rclEmails.setAdapter(adapter);
                adapter.setListener(this);
            }
            if (resultCode == RESULT_STORED){
                emails.remove(emailTransfer);
                AdapterItem adapter = new AdapterItem(getContext(),emails);
                rclEmails.setAdapter(adapter);
                adapter.setListener(this);
                Toast.makeText(getContext(),"1 archived",Toast.LENGTH_LONG).show();
            }
        }
    }
}

