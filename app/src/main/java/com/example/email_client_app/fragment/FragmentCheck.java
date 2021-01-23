package com.example.email_client_app.fragment;

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

import com.example.email_client_app.R;
import com.example.email_client_app.activity.DetailActivity;
import com.example.email_client_app.adapter.AdapterItem;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemEmail;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FoldingCube;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class FragmentCheck extends Fragment implements ItemListener{
    private RecyclerView rclEmails;
    private String userEmail;
    private String userPasswords;
    private ArrayList<ItemEmail> emails = new ArrayList<>();
    private String title;
    private TextView tvTitle;
    private String address_to_string;
    private String received_date;
    private String subject;
    private String content;
    private LinearLayout lnPromotion;
    private LinearLayout lnSocial;
    private ArrayList<String>messages = new ArrayList<>();
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check, container, false);
        if (getArguments()!=null){
            title = getArguments().getString("title");
        }else {
            title = "Primary";
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
        emails = BrainResource.getEmails();
        rclEmails = getActivity().findViewById(R.id.rcl_emails);
        tvTitle = getActivity().findViewById(R.id.tv_status);
        lnPromotion = getActivity().findViewById(R.id.ln_promotions);
        lnSocial = getActivity().findViewById(R.id.ln_social);
        progressBar = (ProgressBar)getActivity().findViewById(R.id.spin_kit);
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
        AdapterItem adapterItem = new AdapterItem(getContext(), emails);
        rclEmails.setAdapter(adapterItem);
        adapterItem.setListener(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rclEmails);
        new MyAsynk().execute();
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

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        ItemEmail email = emails.get(position);
        intent.putExtra("name",email.getName());
        intent.putExtra("date",email.getDate());
        intent.putExtra("imgProfile",email.getImgProfile());
        intent.putExtra("starred",email.isStarred());
        intent.putExtra("subject",email.getSubject());
        intent.putExtra("description",email.getDescription());


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

