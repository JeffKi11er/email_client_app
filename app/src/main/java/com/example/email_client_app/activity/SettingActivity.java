package com.example.email_client_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.email_client_app.MainActivity;
import com.example.email_client_app.R;
import com.example.email_client_app.custom.DialogAuthentication;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvLogOut;
    private ImageView imgReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Log.i(getClass().getName(),"start SettingActivity");
        init();
    }

    private void init() {
        tvLogOut = findViewById(R.id.tv_log_out);
        imgReturn = findViewById(R.id.img_return);
        tvLogOut.setOnClickListener(this);
        imgReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_log_out:
                DialogAuthentication dialogAuthentication = new DialogAuthentication();
                dialogAuthentication.show(getSupportFragmentManager(),"logout fragment dialog");
                break;
            case R.id.img_return:
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}