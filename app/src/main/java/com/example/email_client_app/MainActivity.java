package com.example.email_client_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.email_client_app.activity.ComposeActivity;
import com.example.email_client_app.activity.MeetingActivity;
import com.example.email_client_app.activity.SettingActivity;
import com.example.email_client_app.adapter.AdapterItem;
import com.example.email_client_app.fragment.FragmentAllMail;
import com.example.email_client_app.fragment.FragmentCheck;
import com.example.email_client_app.fragment.FragmentDraft;
import com.example.email_client_app.fragment.FragmentImportant;
import com.example.email_client_app.fragment.FragmentSent;
import com.example.email_client_app.fragment.FragmentSnoozed;
import com.example.email_client_app.fragment.FragmentSocialPromotion;
import com.example.email_client_app.fragment.FragmentStarred;
import com.example.email_client_app.fragment.FragmentSchedule;
import com.example.email_client_app.fragment.FragmentTrash;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.helper.QueryTextChange;
import com.example.email_client_app.item.ItemEmail;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private ImageView imageViewBar;
    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private TextView tvMessage;
    private TextView tvMeeting;
    private boolean shown = false;
    private ImageView imgMessage;
    private ImageView imgMeeting;
    private FloatingActionButton floatingNew, floatingCamera, floatingCompose, floatingActtachment;
    private ImageView imgHeader;
    private TextView tvHeaderEmail;
    private TextView tvHeaderUser;
    private String emailAuthenticate;
    private String passAuthenticate;
    private EditText edtFieldName;
    private EditText edtFieldPass;
    private TextView tvBack;
    private ImageView imgStatus;
    private String userEmail;
    private AdapterItem adapterItem;
    private RecyclerView rclSearch;
    private String userPasswords;
    private RelativeLayout rlvSearch;
    private EditText edtSearch;
    private ArrayList<ItemEmail>emailItems = new ArrayList<>();
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
    private ColorStateList setTintForNavigation( int color1,int color2){
        int [][]states = {new int[]{android.R.attr.state_pressed},new int[]{-android.R.attr.state_pressed}};
        int colors[]={color1,color2};
        nav.setItemIconTintList(new ColorStateList(states,colors));
        return new ColorStateList(states,colors);
    }
    private void init() {
        imageViewBar = findViewById(R.id.img_bar);
        nav = findViewById(R.id.navigationView);
//        nav.setItemIconTintList(new ColorStateList(states,colors));
//        nav.setItemTextColor(new ColorStateList(states,colors));
//        nav.setBackgroundTintList(new ColorStateList(states,colors));
        nav.setItemIconTintList(setTintForNavigation(R.color.red_hard,Color.BLACK));
        nav.setItemTextColor(setTintForNavigation(R.color.red_hard,Color.BLACK));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            nav.setBackgroundTintList(setTintForNavigation(Color.GREEN,Color.WHITE));
//        }
        nav.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawable);
//        tvCompose = findViewById(R.id.tv_mes);
        tvMessage = findViewById(R.id.tv_message);
        tvMeeting = findViewById(R.id.tv_meeting);
        imgMessage = findViewById(R.id.img_message);
        imgMeeting = findViewById(R.id.img_meeting);
        floatingNew = findViewById(R.id.floating_new);
        floatingCompose = findViewById(R.id.fab_compose);
        floatingActtachment = findViewById(R.id.fab_attachment);
        floatingCamera = findViewById(R.id.fab_camera);
        edtSearch = findViewById(R.id.edt_search);
        imgStatus = findViewById(R.id.img_status);
        rclSearch = findViewById(R.id.rcl_search);
        rlvSearch = findViewById(R.id.rlv_search_list);
        adapterItem = new AdapterItem(this,emailItems);
        rclSearch.setAdapter(adapterItem);
        View viewHeader = nav.getHeaderView(0);
        imgHeader = viewHeader.findViewById(R.id.profile_image);
        tvHeaderEmail = viewHeader.findViewById(R.id.tv_mail_header);
        rlvSearch.setVisibility(View.GONE);
        SharedPreferences sharedPreferencesEmail = this.getSharedPreferences("user_email", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferencesPasswords = this.getSharedPreferences("user_passwords", Context.MODE_PRIVATE);
        userEmail = sharedPreferencesEmail.getString("user_email", "");
        userPasswords = sharedPreferencesPasswords.getString("user_passwords", "");
        tvHeaderEmail.setText(userEmail);
        floatingNew.setOnClickListener(this);
        floatingActtachment.setOnClickListener(this);
        floatingCompose.setOnClickListener(this);
        floatingCamera.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentCheck).commit();
        imageViewBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(nav);
            }
        });
        imgMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MeetingActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rlvSearch.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                imgStatus.setImageResource(R.drawable.ic_close);
                imgStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rlvSearch.setVisibility(View.GONE);
                        imgStatus.setImageResource(R.drawable.ic_search);
                    }
                });
            }
        });
    }
    private void filter(String toString) {
        ArrayList<ItemEmail>emails = new ArrayList<>();
        for (ItemEmail email: BrainResource.getEmails()){
            if (email.getSubject().toLowerCase().contains(toString.toLowerCase())){
                emails.add(email);
            }
        }
        adapterItem.filterList(emails);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_new:
                if (!shown){
                    floatingCamera.show();
                    floatingActtachment.show();
                    floatingCompose.show();
                    shown =true;
                }else {
                    floatingCamera.hide();
                    floatingActtachment.hide();
                    floatingCompose.hide();
                    shown = false;
                }
                break;
            case R.id.fab_compose:
                Intent intent = new Intent(MainActivity.this, ComposeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                openLoginDialog();
                Log.e(getClass().getName(), "start Setting");
                break;
            case R.id.menu_all:
                FragmentCheck fragmentCheck = new FragmentCheck();
                Bundle args = new Bundle();
                args.putString("title", "All Inboxes");
                fragmentCheck.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentCheck).commit();
                break;
            case R.id.menu_inb:
                FragmentCheck fragmentCheck1 = new FragmentCheck();
                Bundle args1 = new Bundle();
                args1.putString("title", "Primary");
                fragmentCheck1.setArguments(args1);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentCheck1).commit();
                break;
            case R.id.menu_star:
                openStarred();
                break;
            case R.id.menu_hiden:
                openSnoozed();
                break;
            case R.id.menu_plan:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentSchedule).commit();
                break;
            case R.id.menu_bin:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_, new FragmentDraft()).commit();
                break;
            case R.id.menu_important:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentImportant).commit();
                break;
            case R.id.menu_all_:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentAllMail).commit();
                break;
            case R.id.menu_sent:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentSent).commit();
                break;
            case R.id.menu_spam:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_,new FragmentTrash()).commit();
                break;
            case R.id.menu_promotion:
                FragmentSocialPromotion fragmentSocialPromotion = new FragmentSocialPromotion();
                Bundle argSocial = new Bundle();
                argSocial.putString("title_p", "Promotions");
                fragmentSocialPromotion.setArguments(argSocial);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentSocialPromotion).commit();
                break;
            case R.id.menu_social:
                FragmentSocialPromotion fragmentSocial = new FragmentSocialPromotion();
                Bundle argRealSocial = new Bundle();
                argRealSocial.putString("title_p", "Social");
                fragmentSocial.setArguments(argRealSocial);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentSocial).commit();
                break;
        }
        return false;
    }

    private void openLoginDialog() {
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        startActivity(intent);
        finish();
    }

    private void openStarred() {
        FragmentStarred fragmentStar = new FragmentStarred();
        Bundle argsstarred = new Bundle();
        argsstarred.putString("title", "Starred");
        fragmentCheck.setArguments(argsstarred);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentStar).commit();
    }

    private void openSnoozed() {
        FragmentSnoozed fragmentSnoozed = new FragmentSnoozed();
        Bundle argssnoozed = new Bundle();
        argssnoozed.putString("title", "Snoozed");
        fragmentCheck.setArguments(argssnoozed);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_, fragmentSnoozed).commit();
    }
}
