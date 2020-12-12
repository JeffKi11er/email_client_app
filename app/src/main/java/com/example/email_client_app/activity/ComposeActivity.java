package com.example.email_client_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.email_client_app.MainActivity;
import com.example.email_client_app.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ComposeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgBack;
    private ImageView imgSend;
    private EditText edtMailTo;
    private EditText edtMailSubject;
    private EditText edtMailDetail;
    private TextView tvFrom;
    private ImageView arrowDownFrom;
    private ImageView arrowDownSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        init();
    }

    private void init() {
        imgBack = findViewById(R.id.img_return);
        imgSend = findViewById(R.id.img_send);
        edtMailTo = findViewById(R.id.edt_email_to);
        edtMailSubject = findViewById(R.id.edt_email_subject);
        edtMailDetail = findViewById(R.id.edt_detail);
        tvFrom = findViewById(R.id.tv_email_from);
        arrowDownFrom = findViewById(R.id.img_arrow_from_down_1);
        arrowDownSend = findViewById(R.id.img_arrow_from_down_2);
        imgBack.setOnClickListener(this);
        imgSend.setOnClickListener(this);
        arrowDownFrom.setOnClickListener(this);
        arrowDownSend.setOnClickListener(this);
        tvFrom.setText(R.string.email);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_return:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.img_arrow_from_down_1:
                break;
            case R.id.img_arrow_from_down_2:
                break;
            case R.id.img_send:
                String user = tvFrom.getText().toString();
                String passwords = "nguyenanphu@";
                String recipient = edtMailTo.getText().toString().trim();
                //String []recipients = recipient.split(",");
                String subject = edtMailSubject.getText().toString();
                String message = edtMailDetail.getText().toString();
                Properties properties = new Properties();
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");
                Log.e(getClass().getName(),"initialize properties");
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,passwords);
                    }
                });
                Log.e(getClass().getName(),"initialize sessions");
                Message mimeMessage = new MimeMessage(session);
                Log.e(getClass().getName(),"initialize message content");
                try {
                    mimeMessage.setFrom(new InternetAddress(user));
                    Log.e(getClass().getName(),"sending email..");
                    mimeMessage.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipient));
                    Log.e(getClass().getName(),"recipient email");
                    mimeMessage.setSubject(subject);
                    Log.e(getClass().getName(),"email subject");
                    mimeMessage.setText(message);
                    Log.e(getClass().getName(),"email message");
                    new SendMail().execute(mimeMessage);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private class SendMail extends AsyncTask<Message,String,String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ComposeActivity.this,"Wait a sec..",
                    "sending..",true,false);
            Log.e(getClass().getName(),"create & show progress dialog");
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                Log.e(getClass().getName(),"success");
                return "C'est bon !";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "échouer ! :(";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equals("C'est bon !")){
                AlertDialog.Builder builder = new AlertDialog.Builder(ComposeActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>C'est bon !</font>"));
                builder.setMessage("Sent !");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        edtMailTo.setText("");
                        edtMailSubject.setText("");
                        edtMailDetail.setText("");
                    }
                });
                builder.show();
            }else {
                Toast.makeText(ComposeActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}