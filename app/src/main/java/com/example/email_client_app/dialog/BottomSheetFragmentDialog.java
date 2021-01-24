package com.example.email_client_app.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.email_client_app.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragmentDialog extends BottomSheetDialogFragment {
    private BottomSheetListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_dialog_ads,container,false);
        TextView tvNavi = v.findViewById(R.id.tv_time_navi);
        tvNavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOpenAddOn("Your email did purchase");
                dismiss();
            }
        });
        return v;
    }
    public interface BottomSheetListener{
        void onOpenAddOn(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BottomSheetListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement BottomSheetListener");
        }
    }
}
