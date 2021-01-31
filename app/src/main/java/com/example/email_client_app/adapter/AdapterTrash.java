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
import com.example.email_client_app.item.ItemEmail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterTrash extends RecyclerView.Adapter<AdapterTrash.TrashHolder> {
    private Context context;
    private ArrayList<ItemEmail> emails;
    private ItemListener listener;
    public AdapterTrash(Context context, ArrayList<ItemEmail> emails) {
        this.context = context;
        this.emails = emails;
    }
    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    public void setEmails(ArrayList<ItemEmail> emails) {
        this.emails = emails;
    }

    @NonNull
    @Override
    public TrashHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trash,parent,false);
        return new AdapterTrash.TrashHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrashHolder holder, int position){
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
    }

    @Override
    public int getItemCount(){
        return emails.size();
    }

    public class TrashHolder extends RecyclerView.ViewHolder{
        private ImageView imgProfile;
        private TextView tvNameSent;
        private TextView tvDateSent;
        private TextView tvSubject;
        private TextView tvDescription;

        public TrashHolder(@NonNull View itemView){
            super(itemView);
            imgProfile = itemView.findViewById(R.id.img_trash);
            tvNameSent = itemView.findViewById(R.id.tv_person_trash_name);
            tvDateSent = itemView.findViewById(R.id.tv_time_trash);
            tvSubject = itemView.findViewById(R.id.tv_subject_trash);
            tvDescription = itemView.findViewById(R.id.tv_trash_description);
        }
    }
}
