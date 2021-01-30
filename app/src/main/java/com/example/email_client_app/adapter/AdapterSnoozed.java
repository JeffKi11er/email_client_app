package com.example.email_client_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.email_client_app.R;
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemEmail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSnoozed extends RecyclerView.Adapter<AdapterSnoozed.MyHolder> {
    private Context context;
    private ArrayList<ItemEmail>emails;
    private ItemListener listener;

    public AdapterSnoozed(Context context, ArrayList<ItemEmail> emails) {
        this.context = context;
        this.emails = emails;
    }
    public void setListener(ItemListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_snoozed,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tvNameSent.setText(emails.get(position).getName());
        holder.tvSubject.setText(emails.get(position).getSubject());
        holder.tvDescription.setText(emails.get(position).getDescription());
        holder.tvDateSent.setText(emails.get(position).getDate());
        Picasso.with(context).load(emails.get(position).getImgProfile()).into(holder.imgProfile);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(position);
                }
            }
        });
        if (emails.get(position).getSnoozed().equals("")){
            holder.tvSnoozed.setText("No snoozed in this email");
        }else {
            holder.tvSnoozed.setText(emails.get(position).getSnoozed());
        }
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder {
        private ImageView imgProfile;
        private TextView tvNameSent;
        private TextView tvDateSent;
        private TextView tvSubject;
        private TextView tvDescription;
        private ToggleButton imgStar;
        private TextView tvSnoozed;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.img_person_sent2);
            tvNameSent = itemView.findViewById(R.id.tv_person_received_starred2);
            tvDateSent = itemView.findViewById(R.id.tv_time_received_starred2);
            tvSubject = itemView.findViewById(R.id.tv_subject_sent_starred2);
            tvDescription = itemView.findViewById(R.id.tv_description_starred2);
            imgStar = itemView.findViewById(R.id.star_toggle_snoozed);
            tvSnoozed = itemView.findViewById(R.id.tv_snoozed);
        }
    }
}
