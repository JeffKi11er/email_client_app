package com.example.email_client_app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.email_client_app.MainActivity;
import com.example.email_client_app.R;
import com.example.email_client_app.dialog.BottomSheetFragmentDialog;

import static com.example.email_client_app.helper.AppConstants.RESULT_DELETE;
import static com.example.email_client_app.helper.AppConstants.RESULT_STORED;
import static com.example.email_client_app.helper.AppConstants.RESULT_UNSEEN;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, BottomSheetFragmentDialog.BottomSheetListener, PopupMenu.OnMenuItemClickListener {
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
    private TextView tvAddOn;
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
        tvAddOn = findViewById(R.id.tv_add_on);
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
        tvAddOn.setOnClickListener(this);
        tvUser.setText(user);
        tvReceived.setText("Postman Team <news@postman.com>");
        tvTime.setText("Tue Dec 22 15:22:29 GMT+07:00 2020");
        tvSubject.setText("Postman Galaxy: Three Top Reasons to Attend");
        tvDescription.setText("Postman Galaxy is the global, virtual API conference happening February 2-4, 2021, and it’s rocket fuel for your API skills. Here are three exceptional reasons to get yourself registered ASAP:\n" +
                "    \n" +
                "    \n" +
                "    \t- Superstar guest speakers (see below)\n" +
                "    \t- Insightful technical sessions (also see below)\n" +
                "    \t- Postman-insider speakers (you guessed it, these too are below)");
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
                Intent intent2 = new Intent(this,MainActivity.class);
                setResult(RESULT_STORED,intent2);
                finish();
                break;
            case R.id.img_bin_detail:
                Intent intent = new Intent(this, MainActivity.class);
                setResult(RESULT_DELETE,intent);
                finish();
                break;
            case R.id.img_inb_detail:
                Intent intent1 = new Intent(this, MainActivity.class);
                setResult(RESULT_UNSEEN,intent1);
                finish();
                break;
            case R.id.img_more_detail:
                showPopUp(v);
                break;
            case R.id.tv_add_on:
                BottomSheetFragmentDialog bottomSheet = new BottomSheetFragmentDialog();
                bottomSheet.show(getSupportFragmentManager(),"bottomSheet");
                break;
        }
    }

    private void showPopUp(View v) {
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_setting);
        popupMenu.show();
    }

    @Override
    public void onOpenAddOn(String text) {
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.pop_add_tasks:
                return true;
            case R.id.pop_change_labels:
                return true;
            case R.id.pop_help:
                return true;
            case R.id.pop_mark_not_important:
                return true;
            case R.id.pop_move_to:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_move_to);
                TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            case R.id.pop_print:
                return true;
            case R.id.pop_mute:
                return true;
            case R.id.pop_snooze:
                return true;
            case R.id.pop_report:
                return true;
            default:
                return false;

        }

    }
}