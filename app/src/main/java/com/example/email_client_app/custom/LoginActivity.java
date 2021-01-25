package com.example.email_client_app.custom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.email_client_app.MainActivity;
import com.example.email_client_app.R;
import com.example.email_client_app.helper.LoginDialogListener;

public class LoginActivity extends AppCompatActivity {
    private ImageView imgMail;

    private LoginDialogListener listener;
    private EditText edtFieldName;
    private EditText edtFieldPass;
    private Button btnLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
    }

    private void init() {

        edtFieldName = findViewById(R.id.img_field_user);
        edtFieldPass = findViewById(R.id.img_field_passwords);
        btnLogin = findViewById(R.id.btn_authentication);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailComing = edtFieldName.getText().toString();
                String passwordsComing = edtFieldPass.getText().toString();
                listener.applyText(emailComing,passwordsComing);
//                dismiss();
            }
        });
    }
}