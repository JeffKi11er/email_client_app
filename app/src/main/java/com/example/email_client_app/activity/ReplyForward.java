package com.example.email_client_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.email_client_app.R;

public class ReplyForward extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private TextView tvForward;
    private ImageView imgDotMenu;
    private ImageView imgDotRFA;
    private ImageView imgReturn;
    private String title ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_forward);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        title = title+intent.getStringExtra("title");
    }

    private void init() {
        tvForward = findViewById(R.id.tv_reply_main);
        imgDotMenu = findViewById(R.id.img_dot_reply);
        imgDotRFA = findViewById(R.id.img_menu_reply_forward);
        imgReturn = findViewById(R.id.img_return_reply);
        tvForward.setText(title);
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReplyForward.this,DetailActivity.class));
                finish();
            }
        });
        imgDotRFA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpRFA(v);
            }
        });
        imgDotMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpDot(v);
            }
        });

    }
    private void showPopUpDot(View v) {
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_reqly);
        popupMenu.show();
    }
    private void showPopUpRFA(View v) {
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_small_reply);
        popupMenu.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}