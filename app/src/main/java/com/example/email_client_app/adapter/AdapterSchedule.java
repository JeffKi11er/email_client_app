package com.example.email_client_app.adapter;

import android.content.Context;
import android.media.Image;
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
import com.example.email_client_app.item.ItemSchedule;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSchedule extends RecyclerView.Adapter<AdapterSchedule.MyHolder> {
    private Context context;
    private ArrayList<ItemSchedule>schedules;
    private ItemListener listener;

    public AdapterSchedule(Context context, ArrayList<ItemSchedule> schedules) {
        this.context = context;
        this.schedules = schedules;
    }
    public void setListener(ItemListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public AdapterSchedule.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewSchedule = LayoutInflater.from(context).inflate(R.layout.schedule_item, parent, false);
        return new MyHolder(viewSchedule);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSchedule.MyHolder holder, int position) {
        Picasso.with(context).load(schedules.get(position).getImgAvatar()).into(holder.imgAvatar);
        holder.emailReceived.setText(schedules.get(position).getEmailReceived());
        holder.subjectSent.setText(schedules.get(position).getSubjectSent());
        holder.timeSend.setText(schedules.get(position).getTimeSend());
        holder.descriptionSchedule.setText(schedules.get(position).getDescriptionSchedule());
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
        return schedules.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView emailReceived;
        private TextView subjectSent;
        private TextView timeSend;
        private TextView descriptionSchedule;
        private ToggleButton starToggleSchedule;

        public MyHolder(View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.img_person_sent_schedule);
            emailReceived = itemView.findViewById(R.id.tv_person_received_schedule);
            subjectSent = itemView.findViewById(R.id.tv_subject_sent_schedule);
            timeSend = itemView.findViewById(R.id.tv_time_send_schedule);
            descriptionSchedule = itemView.findViewById(R.id.tv_description_schedule);
            starToggleSchedule = itemView.findViewById(R.id.star_toggle_schedule);
        }
    }
}
