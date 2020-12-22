package com.example.email_client_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.email_client_app.R;
import com.example.email_client_app.item.ItemEmail;
import com.example.email_client_app.item.ItemSentEmail;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SentAdapter extends RecyclerView.Adapter<SentAdapter.MyHolder> {
    private Context context;
    private ArrayList<ItemSentEmail>emails;

    public SentAdapter(Context context, ArrayList<ItemSentEmail> emails) {
        this.context = context;
        this.emails = emails;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sent_item_email,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tvNameSent.setText(emails.get(position).getName());
        holder.tvSubject.setText(emails.get(position).getSubject());
        holder.tvDescription.setText(emails.get(position).getDescription());
        holder.tvTags.setText("Tags");
        holder.tvDateSent.setText(emails.get(position).getDate());
        Picasso.with(context).load(emails.get(position).getImgProfile()).into(holder.imgProfile);
//        holder.numofmail.setText(emails.get(position).getNumofmail());
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
        private ImageView imgStar;
        private TextView tvTags;
        private TextView numofmail;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.img_person_sent_origin);
            tvNameSent = itemView.findViewById(R.id.tv_person_received_origin);
            tvDateSent = itemView.findViewById(R.id.tv_time_received_origin);
            tvSubject = itemView.findViewById(R.id.tv_subject_sent_origin);
            tvDescription = itemView.findViewById(R.id.tv_description_origin);
            imgStar = itemView.findViewById(R.id.img_star_origin);
            tvTags = itemView.findViewById(R.id.tv_tags_origin);
            numofmail = itemView.findViewById(R.id.num_mail_sent);
        }
    }
}