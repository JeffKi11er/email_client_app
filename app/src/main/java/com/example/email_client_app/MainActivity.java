package com.example.email_client_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.email_client_app.activity.ComposeActivity;
import com.example.email_client_app.activity.SettingActivity;
import com.example.email_client_app.custom.DialogAuthentication;
import com.example.email_client_app.fragment.FragmentCheck;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private ImageView imageViewBar;
    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private TextView tvCompose;
    private ImageView imgHeader;
    private TextView tvHeaderEmail;
    private TextView tvHeaderUser;
    private String emailAuthenticate;
    private String passAuthenticate;
    private EditText edtFieldName;
    private EditText edtFieldPass;
    private TextView tvBack;
//    private FragmentCheck fragmentCheck = new FragmentCheck();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        imageViewBar = findViewById(R.id.img_bar);
        nav = findViewById(R.id.navigationView);
        nav.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawable);
        tvCompose = findViewById(R.id.tv_mes);
        View viewHeader = nav.getHeaderView(0);
        imgHeader = viewHeader.findViewById(R.id.profile_image);
        tvHeaderEmail = viewHeader.findViewById(R.id.tv_mail_header);
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
                Intent intent = new Intent(MainActivity.this, ComposeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_setting:
                openLoginDialog();
                Log.e(getClass().getName(),"start Setting");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void openLoginDialog() {
        Intent intent = new Intent(MainActivity.this,SettingActivity.class);
        startActivity(intent);
        finish();
    }
}
