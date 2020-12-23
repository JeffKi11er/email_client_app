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
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemSocial;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSocial extends RecyclerView.Adapter<AdapterSocial.SocialHolder> {
    private ArrayList<ItemSocial>itemSocials;
    private Context context;
    private ItemListener listener;

    public AdapterSocial(ArrayList<ItemSocial> itemSocials, Context context) {
        this.itemSocials = itemSocials;
        this.context = context;
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SocialHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_promotions_social,parent,false);
        return new SocialHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialHolder holder, int position) {
        Picasso.with(context).load(itemSocials.get(position).getImgProfile()).into(holder.imgProfile);
        Picasso.with(context).load(itemSocials.get(position).getImgMedia()).into(holder.imgMedia);
        if (itemSocials.get(position).isErrorSpam()){
           holder.imgSpamError.setVisibility(View.VISIBLE);
        }else {
            holder.imgSpamError.setVisibility(View.GONE);
        }
        holder.tvSubject.setText(itemSocials.get(position).getSubject());
        holder.tvDescription.setText(itemSocials.get(position).getDescription());
        holder.tvAddress.setText(itemSocials.get(position).getName());
        holder.tvDate.setText(itemSocials.get(position).getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener!=null){
                    listener.onLongClick(position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemSocials.size();
    }

    public class SocialHolder extends RecyclerView.ViewHolder {
        private ImageView imgProfile;
        private TextView tvSubject;
        private TextView tvAddress;
        private ImageView imgMedia;
        private ImageView imgSpamError;
        private TextView tvDescription;
        private TextView tvDate;
        public SocialHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.img_profile_social);
            tvSubject = itemView.findViewById(R.id.tv_subject_social);
            tvAddress = itemView.findViewById(R.id.tv_social_sent);
            imgMedia = itemView.findViewById(R.id.img_sample);
            imgSpamError = itemView.findViewById(R.id.img_ads);
            tvDescription = itemView.findViewById(R.id.tv_description_social);
            tvDate = itemView.findViewById(R.id.tv_time_received_social);
        }
    }
}
