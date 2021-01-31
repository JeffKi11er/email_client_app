package com.example.email_client_app.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import com.example.email_client_app.adapter.AdapterStarred;
import com.example.email_client_app.adapter.AdapterTrash;
import com.example.email_client_app.adapter.AdapterTrash;
import com.example.email_client_app.helper.AppConstants;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemEmail;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.pop3.POP3Store;

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

import static com.example.email_client_app.helper.AppConstants.REQUEST_CODE;

public class FragmentTrash extends Fragment implements ItemListener {
    private ArrayList<ItemEmail> emails = new ArrayList<>();
    private RecyclerView rcltrash;
    private SwipeRefreshLayout swipeRefreshtrash;
    private ItemEmail emailTransfer;
    private String userEmail;
    private String userPasswords;
    private String received_date="";
    private String subject="";
    private String content="";
    AdapterTrash adapterTrash;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trash,container,false);
        dataFinish();
        return view;
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
                Properties properties = getServerProperties(AppConstants.protocol, AppConstants.host, AppConstants.port);
                Session session = Session.getDefaultInstance(properties,null);
                try {
//                    Session session = Session.getInstance(props, null);
//                    Store store = session.getStore();
//                    store.connect("imap.gmail.com", userEmail, userPasswords);
                    Store store = session.getStore(AppConstants.protocol);
                    store.connect(userEmail, userPasswords);
//                    Folder inbox = store.getFolder("INBOX");
                    Folder[] f = store.getDefaultFolder().list("*");
                    for (Folder fd:f) {
                        Log.i(getClass().getName(),fd.getName());
                    }
//                    IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");
//                    Folder inbox = getAlternativeFolder(f,"INBOX");
                    Folder inbox = f[6];
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
                        Log.i(getClass().getName(),"SUBJECT:" + msg[i].getSubject());
                        subject = msg[i].getSubject();
                        Log.i(getClass().getName(),"CONTENT:" + bp.getContent());
                        content = bp.getContent().toString();
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
                adapterTrash.setEmails(emails);
                adapterTrash.notifyDataSetChanged();
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
        rcltrash = getActivity().findViewById(R.id.rcl_trash);
        rcltrash.setAdapter(new AdapterTrash(getContext(),emails));
        swipeRefreshtrash = getActivity().findViewById(R.id.swipe_to_trash);
        adapterTrash = new AdapterTrash(getContext(),emails);
        rcltrash.setAdapter(adapterTrash);
        adapterTrash.setListener(this);
        swipeRefreshtrash.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataFinish();
                swipeRefreshtrash.setRefreshing(false);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rcltrash);
    }
    ItemEmail itemEmail = null;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
//                    progressBar.setVisibility(View.VISIBLE);
                    itemEmail = emails.get(position);
                    emails.remove(position);
                    rcltrash.setAdapter(new AdapterStarred(getContext(),emails));
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
// ...Irrelevant code for customizing the buttons and title
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.dialog_progress, null);
                    dialogBuilder.setView(dialogView);
                    AlertDialog alertDialog = dialogBuilder.create();
                    new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (Exception e) {
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
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.green))
                    .addSwipeLeftActionIcon(R.drawable.ic_all_inb_white)
                    .create()
                    .decorate();
            View itemView = viewHolder.itemView;
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
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
}

