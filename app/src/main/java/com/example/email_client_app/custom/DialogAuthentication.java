package com.example.email_client_app.custom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.email_client_app.R;
import com.example.email_client_app.helper.LoginDialogListener;

public class DialogAuthentication extends AppCompatDialogFragment {
    private LoginDialogListener listener;
    private EditText edtFieldName;
    private EditText edtFieldPass;
    private TextView tvBack;
    private Button btnLogin;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login,null);
        dialog.setView(view);
        edtFieldName = view.findViewById(R.id.img_field_user);
        edtFieldPass = view.findViewById(R.id.img_field_passwords);
        tvBack = view.findViewById(R.id.tv_back);
        btnLogin = view.findViewById(R.id.btn_authentication);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailComing = edtFieldName.getText().toString();
                String passwordsComing = edtFieldPass.getText().toString();
                listener.applyText(emailComing,passwordsComing);
                dismiss();
            }
        });
        return dialog.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (LoginDialogListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement LoginDialogListener");
        }
    }
}
