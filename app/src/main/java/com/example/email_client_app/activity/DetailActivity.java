package com.example.email_client_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.email_client_app.MainActivity;
import com.example.email_client_app.R;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.item.ItemEmail;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvSubject;
    private ImageView imgStored;
    private ImageView imgMore;
    private ImageView imgBin;
    private ImageView imgInBox;
    private ImageView imgStar;
    private ImageView imgProfile;
    private ImageView imgArrowBack;
    private TextView tvUser;
    private TextView tvReceived;
    private TextView tvTime;
    private ImageView imgDown;
    private ImageView imgReturnLoop;
    private ImageView imgMoreChoose;
    private TextView tvDescription;
    private SharedPreferences preferencesEmail;

    private ImageView imgArrowdowndetail;
    private ImageView imgReturnloop;
    private ImageView imgToolpopup;
    private ImageView imgDetailstar;
    private ImageView imgStoreddetail;
    private ImageView imgBindetail;
    private ImageView imgInbdetail;
    private ImageView imgMoredetail;
    private TextView tvReply;
    private TextView tvForward;
    private TextView tvReplyall;

    private String name;
    private String date;
    private int imgProfilePicture;
    private boolean starred;
    private String subject;
    private String description;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");
        imgProfilePicture = intent.getIntExtra("imgProfile",0);
        starred = intent.getBooleanExtra("starred",false);
        subject = intent.getStringExtra("subject");
        description = intent.getStringExtra("description");
        Toast.makeText(this,name+date+imgProfilePicture+starred+subject+description,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
    }

    private void init() {
//        Intent intent = getIntent();
//        ArrayList<String>getMessages = intent.getStringArrayListExtra("message");
//        String address_to_string = intent.getStringExtra("address_to_string");
//        String received_date = intent.getStringExtra("received_date");
//        String subject = intent.getStringExtra("subject");
//        String content = intent.getStringExtra("content");

        preferencesEmail = this.getSharedPreferences("user_email", Context.MODE_PRIVATE);
        String user = preferencesEmail.getString("user_email","");
        tvSubject = findViewById(R.id.tv_detail_subject);
        imgStored = findViewById(R.id.img_stored_detail);
        imgMore = findViewById(R.id.img_more_detail);
        imgBin  = findViewById(R.id.img_bin_detail);
        imgInBox = findViewById(R.id.img_inb_detail);
        imgStar = findViewById(R.id.img_detail_star);
        imgProfile = findViewById(R.id.img_detail_profile);
        imgArrowBack = findViewById(R.id.img_arrow_back_detail);
        tvUser = findViewById(R.id.tv_detail_user);
        tvReceived = findViewById(R.id.tv_detail_received);
        tvTime = findViewById(R.id.tv_detail_time);
        imgDown = findViewById(R.id.img_arrow_down_detail);
        imgReturnLoop = findViewById(R.id.img_return_loop);
        imgMoreChoose = findViewById(R.id.img_tool_pop_up);
        tvDescription = findViewById(R.id.tv_detail_description);

        tvReply = findViewById(R.id.tv_reply);
        tvReplyall = findViewById(R.id.tv_reply_all);
        tvForward = findViewById(R.id.tv_forward);
        imgArrowdowndetail = findViewById(R.id.img_arrow_down_detail);
        imgReturnLoop = findViewById(R.id.img_return_loop);
        imgToolpopup = findViewById(R.id.img_tool_pop_up);
        imgDetailstar = findViewById(R.id.img_detail_star);
        imgStoreddetail= findViewById(R.id.img_stored_detail);
        imgBindetail = findViewById(R.id.img_bin_detail);
        imgInbdetail = findViewById(R.id.img_inb_detail);
        imgMoredetail = findViewById(R.id.img_more_detail);




        imgArrowBack.setOnClickListener(this);
        tvReply.setOnClickListener(this);
        tvReplyall.setOnClickListener(this);
        tvForward.setOnClickListener(this);
        imgArrowdowndetail.setOnClickListener(this);
        imgReturnLoop.setOnClickListener(this);
        imgDetailstar.setOnClickListener(this);
        imgDetailstar.setOnClickListener(this);
        imgStoreddetail.setOnClickListener(this);
        imgBindetail.setOnClickListener(this);
        imgInbdetail.setOnClickListener(this);
        imgMoredetail.setOnClickListener(this);

        tvUser.setText(user);
//        if (getMessages!=null && getMessages.size()>0){
//            tvReceived.setText(getMessages.get(0));
//            tvTime.setText(getMessages.get(1));
//            tvSubject.setText(getMessages.get(2));
//            tvDescription.setText(getMessages.get(3));
        tvReceived.setText("Postman Team <news@postman.com>");
        tvTime.setText("Tue Dec 22 15:22:29 GMT+07:00 2020");
        tvSubject.setText("Postman Galaxy: Three Top Reasons to Attend");
        tvDescription.setText("Postman Galaxy is the global, virtual API conference happening February 2-4, 2021, and it’s rocket fuel for your API skills. Here are three exceptional reasons to get yourself registered ASAP:\n" +
                "    \n" +
                "    \n" +
                "    \t- Superstar guest speakers (see below)\n" +
                "    \t- Insightful technical sessions (also see below)\n" +
                "    \t- Postman-insider speakers (you guessed it, these too are below)");
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_arrow_back_detail:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.tv_reply:

                break;
            case R.id.tv_reply_all:
                break;
            case R.id.tv_forward:
                break;
            case R.id.img_arrow_down_detail:
                break;
            case R.id.img_return_loop:
                break;
            case R.id.img_tool_pop_up:
                break;
            case R.id.img_detail_star:
                break;
            case R.id.img_stored_detail:
                break;
            case R.id.img_bin_detail:
                BrainResource.removeEmail(new ItemEmail(name,date,imgProfilePicture,starred,subject,description));
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.img_inb_detail:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                ///SharedPreference to bold the text style
                break;
            case R.id.img_more_detail:
                break;
        }
    }
}