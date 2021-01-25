package com.example.email_client_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.email_client_app.MainActivity;
import com.example.email_client_app.R;
import com.example.email_client_app.custom.DialogAuthentication;
import com.example.email_client_app.custom.LoginActivity;
import com.example.email_client_app.helper.LoginDialogListener;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, LoginDialogListener, PopupMenu.OnMenuItemClickListener {
    private TextView tvAccountWebsite;
    private TextView tvAddAccount;
    private ImageView imgReturn;
    private TextView tvEmailUser;
    private ImageView imgDot;
    private TextView tvPasswordsUser;
    private SharedPreferences preferencesEmail;
    private SharedPreferences preferencesPasswords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Log.i(getClass().getName(),"start SettingActivity");
        init();
    }

    private void init() {
        tvAddAccount = findViewById(R.id.tv_add_account);
        tvAccountWebsite = findViewById(R.id.tv_add_account_website);
        imgReturn = findViewById(R.id.img_return);
        imgDot = findViewById(R.id.img_dot);
        tvEmailUser = findViewById(R.id.tv_email_login);
        tvPasswordsUser = findViewById(R.id.tv_passwords_login);
        preferencesEmail = this.getSharedPreferences("user_email", Context.MODE_PRIVATE);
        tvEmailUser.setText(preferencesEmail.getString("user_email",""));
        preferencesPasswords = this.getSharedPreferences("user_passwords",Context.MODE_PRIVATE);
        tvPasswordsUser.setText(preferencesPasswords.getString("user_passwords",""));
        tvAccountWebsite.setOnClickListener(this);
        imgReturn.setOnClickListener(this);
        tvAddAccount.setOnClickListener(this);
        imgDot.setOnClickListener(this);
    }
    private void showPopUp(View v) {
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_manage_account);
        popupMenu.show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_log_out:
//                DialogAuthentication dialogAuthentication = new DialogAuthentication();
//                dialogAuthentication.show(getSupportFragmentManager(),"logout fragment dialog");
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
            case R.id.img_dot:
                showPopUp(v);
                break;
            case R.id.tv_add_account:
                DialogAuthentication dialogAuthentication = new DialogAuthentication();
                dialogAuthentication.show(getSupportFragmentManager(),"logout fragment dialog");
                break;
            case R.id.img_return:
                SharedPreferences.Editor editorM = preferencesEmail.edit();
                editorM.remove("user_email");
                editorM.putString("user_email",tvEmailUser.getText().toString());
                editorM.commit();
                SharedPreferences.Editor editorP = preferencesPasswords.edit();
                editorP.remove("user_passwords");
                editorP.putString("user_passwords",tvPasswordsUser.getText().toString());
                editorP.commit();
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_add_account_website:
                Intent intent1 = new Intent(this,AddacountActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void applyText(String email, String passwords) {
        tvEmailUser.setText(email);
        tvPasswordsUser.setText(passwords);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            default:
                return false;
        }
    }
}
