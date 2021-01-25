package com.example.email_client_app.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.email_client_app.R;
import com.example.email_client_app.activity.DetailActivity;
import com.example.email_client_app.adapter.AdapterItem;
import com.example.email_client_app.adapter.AdapterSchedule;
import com.example.email_client_app.adapter.AdapterSchedule;
import com.example.email_client_app.adapter.AdapterSchedule;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemEmail;
import com.example.email_client_app.item.ItemSchedule;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static com.example.email_client_app.helper.AppConstants.REQUEST_CODE;

public class FragmentSchedule extends Fragment implements ItemListener {
    private ArrayList<ItemSchedule> schedule = new ArrayList<>();
    private RecyclerView rclSchedule;
    private SwipeRefreshLayout swipeRefreshSchedule;
    private ItemSchedule emailTransfer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View schedule_view = inflater.inflate(R.layout.fragment_schedule, container, false);
        return schedule_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        schedule = BrainResource.getScheduleEmails();
        rclSchedule = getActivity().findViewById(R.id.schedule_recycler);

        swipeRefreshSchedule = getActivity().findViewById(R.id.swipe_to_schedule);


        rclSchedule.setAdapter(new AdapterSchedule(getContext(),schedule));
        swipeRefreshSchedule.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(),"Nothing to show",Toast.LENGTH_LONG).show();
                swipeRefreshSchedule.setRefreshing(false);
            }
        });
        AdapterSchedule adapterSchedule = new AdapterSchedule(getContext(), schedule);
        rclSchedule.setAdapter(adapterSchedule);
        adapterSchedule.setListener(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rclSchedule);
    }
    ItemSchedule itemEmail = null;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
//                    progressBar.setVisibility(View.VISIBLE);
                    itemEmail = schedule.get(position);
                    schedule.remove(position);
                    AdapterSchedule adapterSchedule = new AdapterSchedule(getContext(), schedule);
                    rclSchedule.setAdapter(adapterSchedule);

                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
// ...Irrelevant code for customizing the buttons and title
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.dialog_progress, null);
                    dialogBuilder.setView(dialogView);
                    AlertDialog alertDialog = dialogBuilder.create();
                    new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (Exception e) {
                                Log.e("tag", e.getMessage());
                            }
                            // dismiss the progress dialog
                            alertDialog.dismiss();
                        }
                    }.start();
                    alertDialog.show();
                    break;

            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.green))
                    .addSwipeLeftActionIcon(R.drawable.ic_all_inb_white)
                    .create()
                    .decorate();
            View itemView = viewHolder.itemView;
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        emailTransfer = schedule.get(position);
        intent.putExtra("emailReceived",emailTransfer.getEmailReceived());
        intent.putExtra("imgAvatar",emailTransfer.getImgAvatar());
        intent.putExtra("starred",emailTransfer.isStarred());
        intent.putExtra("subjectSent",emailTransfer.getSubjectSent());
        intent.putExtra("descriptionSchedule",emailTransfer.getDescriptionSchedule());
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onLongClick(int position) {

    }
}
