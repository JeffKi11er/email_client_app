package com.example.email_client_app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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

public class AdapterDraft extends RecyclerView.Adapter<AdapterDraft.DraftHolder> {
    private Context context;
    private ArrayList<ItemEmail> emails;
    private ItemListener listener;
    public AdapterDraft(Context context, ArrayList<ItemEmail> emails) {
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
    public AdapterDraft.DraftHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_draft,parent,false);
        return new AdapterDraft.DraftHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DraftHolder holder, int position) {
        holder.tvNameSent.setText(emails.get(position).getName());
        if (emails.get(position).isStarred()){
            holder.tvNameSent.setTypeface(holder.tvNameSent.getTypeface(), Typeface.BOLD);
            holder.tvNameSent.setTextColor(Color.parseColor("#212121"));
        }
        holder.tvSubject.setText(emails.get(position).getSubject());
        holder.tvDescription.setText(emails.get(position).getDescription());
        holder.tvTags.setText("Tags");
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
    public int getItemCount() {
        return emails.size();
    }

    public void setListener(ItemTouchHelper.SimpleCallback simpleCallback) {
    }

    public class DraftHolder extends RecyclerView.ViewHolder {
        private ImageView imgProfile;
        private TextView tvNameSent;
        private TextView tvDateSent;
        private TextView tvSubject;
        private TextView tvDescription;
        private ToggleButton imgStarDraft;
        private TextView tvTags;

        public DraftHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.img_draft);
            tvNameSent = itemView.findViewById(R.id.tv_person_draft_name);
            tvDateSent = itemView.findViewById(R.id.tv_time_draft);
            tvSubject = itemView.findViewById(R.id.tv_subject_draft);
            tvDescription = itemView.findViewById(R.id.tv_draft_description);
            imgStarDraft = itemView.findViewById(R.id.img_star_draft);
            tvTags = itemView.findViewById(R.id.tv_draft_tags);

        }
    }
}

