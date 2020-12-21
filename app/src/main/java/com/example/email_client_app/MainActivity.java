package com.example.email_client_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.email_client_app.fragment.FragmentSnoozed;
import com.example.email_client_app.fragment.FragmentStarred;
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
    private FragmentCheck fragmentCheck = new FragmentCheck();
    private FragmentStarred fragmentStar = new FragmentStarred();
    private FragmentSnoozed fragmentSnoozed = new FragmentSnoozed();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_,fragmentCheck).commit();
            nav.setCheckedItem(R.id.menu_all);
        }
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
        SharedPreferences sharedPreferencesEmail = this.getSharedPreferences("user_email", Context.MODE_PRIVATE);
        tvHeaderEmail.setText(sharedPreferencesEmail.getString("user_email",""));
        tvCompose.setOnClickListener(this);
//

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
            case R.id.menu_star:
                openStarred();
                break;
            case R.id.menu_hiden:
                openSnoozed();
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
    private void openStarred() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentStar).commit();
    }
    private void openSnoozed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentSnoozed).commit();
    }
}
