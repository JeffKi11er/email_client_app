package com.example.email_client_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.email_client_app.MainActivity;
import com.example.email_client_app.R;
import com.example.email_client_app.custom.DialogAuthentication;
import com.example.email_client_app.helper.LoginDialogListener;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, LoginDialogListener {
    private TextView tvLogOut;
    private TextView tvAddAccount;
    private ImageView imgReturn;
    private TextView tvEmailUser;
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
        tvLogOut = findViewById(R.id.tv_log_out);
        imgReturn = findViewById(R.id.img_return);
        tvEmailUser = findViewById(R.id.tv_email_login);
        tvPasswordsUser = findViewById(R.id.tv_passwords_login);
        preferencesEmail = this.getSharedPreferences("user_email", Context.MODE_PRIVATE);
        tvEmailUser.setText(preferencesEmail.getString("user_email",""));
        preferencesPasswords = this.getSharedPreferences("user_passwords",Context.MODE_PRIVATE);
        tvPasswordsUser.setText(preferencesPasswords.getString("user_passwords",""));
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
            case R.id.tv_add_account:
                AddacountActivity addacountActivity = new AddacountActivity();

                break;
        }
    }

    @Override
    public void applyText(String email, String passwords) {
        tvEmailUser.setText(email);
        tvPasswordsUser.setText(passwords);
    }
}
