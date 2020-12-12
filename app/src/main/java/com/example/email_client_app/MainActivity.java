package com.example.email_client_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.email_client_app.activity.ComposeActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageViewBar;
    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private TextView tvCompose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        imageViewBar = findViewById(R.id.img_bar);
        nav = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawable);
        tvCompose = findViewById(R.id.tv_mes);
        tvCompose.setOnClickListener(this);
        imageViewBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(nav);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_mes:
                Intent intent = new Intent(this, ComposeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
