package com.example.email_client_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.email_client_app.MainActivity;
import com.example.email_client_app.R;

public class MeetingActivity extends AppCompatActivity {
    private ImageView imgMail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        init();
    }

    private void init() {
        imgMail = findViewById(R.id.img_message_met);
        imgMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeetingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
