package com.example.email_client_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.email_client_app.MainActivity;
import com.example.email_client_app.R;

public class ComposeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        init();
    }

    private void init() {
        imgBack = findViewById(R.id.img_return);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_return:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
        }
    }
}