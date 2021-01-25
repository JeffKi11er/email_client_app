package com.example.email_client_app.custom;

import android.app.Activity;
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

import static com.example.email_client_app.helper.AppConstants.RESULT_UNSEEN;

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
        edtFieldName = findViewById(R.id.img_field_Hlogin);
        edtFieldPass = findViewById(R.id.img_field_Hpasswords);
        btnLogin = findViewById(R.id.btn_Hauthentication);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailComing = edtFieldName.getText().toString();
                String passwordsComing = edtFieldPass.getText().toString();
                Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                intent1.putExtra("email authen",emailComing);
                intent1.putExtra("password authen",passwordsComing);
                setResult(Activity.RESULT_OK,intent1);
                finish();
            }
        });
    }
}