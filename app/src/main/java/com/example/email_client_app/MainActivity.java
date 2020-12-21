package com.example.email_client_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.email_client_app.R;
import com.example.email_client_app.activity.ComposeActivity;
import com.example.email_client_app.activity.SettingActivity;
import com.example.email_client_app.fragment.FragmentAllMail;
import com.example.email_client_app.fragment.FragmentCheck;
import com.example.email_client_app.fragment.FragmentDraft;
import com.example.email_client_app.fragment.FragmentSent;
import com.example.email_client_app.fragment.FragmentSnoozed;
import com.example.email_client_app.fragment.FragmentStarred;
import com.example.email_client_app.fragment.FragmentSchedule;
import com.example.email_client_app.fragment.FragmentImportant;
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
    private String userEmail;
    private String userPasswords;
    private FragmentCheck fragmentCheck = new FragmentCheck();
    private FragmentSnoozed fragmentSnoozed = new FragmentSnoozed();
    private FragmentSchedule fragmentSchedule = new FragmentSchedule();
    private FragmentImportant fragmentImportant = new FragmentImportant();
    private FragmentAllMail fragmentAllMail = new FragmentAllMail();
    private FragmentSent fragmentSent = new FragmentSent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (savedInstanceState == null) {
            nav.setCheckedItem(R.id.menu_all);
            FragmentCheck fragmentCheck = new FragmentCheck();
            Bundle args = new Bundle();
            args.putString("title", "All Inboxes");
            fragmentCheck.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentCheck).commit();
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
        SharedPreferences sharedPreferencesPasswords = this.getSharedPreferences("user_passwords",Context.MODE_PRIVATE);
        userEmail = sharedPreferencesEmail.getString("user_email","");
        userPasswords =  sharedPreferencesPasswords.getString("user_passwords","");
        tvHeaderEmail.setText(userEmail);
        tvCompose.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_,fragmentCheck).commit();
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
            case R.id.menu_all:
                FragmentCheck fragmentCheck = new FragmentCheck();
                Bundle args = new Bundle();
                args.putString("title","All Inboxes");
                fragmentCheck.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_,fragmentCheck).commit();
                break;
            case R.id.menu_inb:
                FragmentCheck fragmentCheck1 = new FragmentCheck();
                Bundle args1 = new Bundle();
                args1.putString("title","Inbox");
                fragmentCheck1.setArguments(args1);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_,fragmentCheck1).commit();
                break;
            case R.id.menu_star:
                openStarred();
                break;
            case R.id.menu_hiden:
                openSnoozed();
                break;
            case R.id.menu_plan:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_,fragmentSchedule).commit();
                break;
            case R.id.menu_bin:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_,new FragmentDraft()).commit();
                break;
            case R.id.menu_important:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_,fragmentImportant).commit();
		break;
            case R.id.menu_all_:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_,fragmentAllMail).commit();
                break;
	    case R.id.menu_sent:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_,fragmentSent).commit();
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
        FragmentStarred fragmentStar = new FragmentStarred();
        Bundle argsstarred = new Bundle();
        argsstarred.putString("title","Starred");
        fragmentCheck.setArguments(argsstarred);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentStar).commit();
    }
    private void openSnoozed() {
        FragmentSnoozed fragmentSnoozed = new FragmentSnoozed();
        Bundle argssnoozed = new Bundle();
        argssnoozed.putString("title","Snoozed");
        fragmentCheck.setArguments(argssnoozed);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentSnoozed).commit();
    }
}
