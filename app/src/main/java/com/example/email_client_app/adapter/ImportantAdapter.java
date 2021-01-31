package com.example.email_client_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.email_client_app.R;
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemEmail;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ImportantAdapter extends RecyclerView.Adapter<ImportantAdapter.MyHolder> {
    private Context context;
    private ArrayList<ItemEmail>emails;
    private ItemListener listener;
    public ImportantAdapter(Context context, ArrayList<ItemEmail> emails) {
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
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.important_item_email,parent,false);
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

    public class MyHolder extends RecyclerView.ViewHolder {
        private ImageView imgProfile;
        private TextView tvNameSent;
        private TextView tvDateSent;
        private TextView tvSubject;
        private TextView tvDescription;
        private ImageView imgStar;
        private TextView tvTags;
        private ToggleButton starToggleImportant;
        
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.img_person_important);
            tvNameSent = itemView.findViewById(R.id.tv_person_important);
            tvDateSent = itemView.findViewById(R.id.tv_time_received_important);
            tvSubject = itemView.findViewById(R.id.tv_subject_important);
            tvDescription = itemView.findViewById(R.id.tv_description_important);
            tvTags = itemView.findViewById(R.id.tv_tags_important);
            starToggleImportant = itemView.findViewById(R.id.star_toggle_important);
        }
    }
}
