package com.example.email_client_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.email_client_app.MainActivity;
import com.example.email_client_app.R;

public class AdsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgBackAdvertise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        init();
    }

    private void init() {
        imgBackAdvertise = findViewById(R.id.img_arrow_back_ads);
        imgBackAdvertise.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_arrow_back_ads:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}
