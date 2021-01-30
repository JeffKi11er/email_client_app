package com.example.email_client_app.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.email_client_app.R;
import com.example.email_client_app.activity.DetailActivity;
import com.example.email_client_app.adapter.AdapterItem;
import com.example.email_client_app.adapter.AllMailAdapter;
import com.example.email_client_app.helper.BrainResource;
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemEmail;

import java.util.ArrayList;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static com.example.email_client_app.helper.AppConstants.REQUEST_CODE;

public class FragmentAllMail extends Fragment implements ItemListener {
    private ArrayList<ItemEmail>allMails = new ArrayList<>();
    private RecyclerView rclAllMails;
    private SwipeRefreshLayout swipeRefreshAll;
    private ItemEmail emailTransfer;
public class FragmentAllMail extends Fragment {
    private ArrayList<ItemEmail>allMails;
    private RecyclerView rclAllMails;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_mail,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        allMails  = new ArrayList<>();

        allMails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        allMails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        allMails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        allMails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        allMails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        allMails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        allMails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,true,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));

        rclAllMails = getActivity().findViewById(R.id.all_mail_recycler_view);
        swipeRefreshAll = getActivity().findViewById(R.id.swipe_to_all);
        AllMailAdapter allMailAdapter = new AllMailAdapter(getContext(),allMails);
        rclAllMails.setAdapter(allMailAdapter);
        allMailAdapter.setListener(this);
        swipeRefreshAll.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(),"Nothing to show",Toast.LENGTH_LONG).show();
                swipeRefreshAll.setRefreshing(false);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rclAllMails);
    }
    ItemEmail itemEmail = null;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
//                    progressBar.setVisibility(View.VISIBLE);
                    itemEmail = allMails.get(position);
                    allMails.remove(position);
                    rclAllMails.setAdapter(new AllMailAdapter(getContext(),allMails));
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
// ...Irrelevant code for customizing the buttons and title
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.dialog_progress, null);
                    dialogBuilder.setView(dialogView);
                    AlertDialog alertDialog = dialogBuilder.create();
                    new Thread() {
                        public void run() {
                            try{
                                Thread.sleep(2000);
                            }
                            catch (Exception e) {
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
            new RecyclerViewSwipeDecorator.Builder(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(),R.color.green))
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
        emailTransfer = allMails.get(position);
        intent.putExtra("name",emailTransfer.getName());
        intent.putExtra("date",emailTransfer.getDate());
        intent.putExtra("imgProfile",emailTransfer.getImgProfile());
        intent.putExtra("starred",emailTransfer.isStarred());
        intent.putExtra("subject",emailTransfer.getSubject());
        intent.putExtra("description",emailTransfer.getDescription());
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onLongClick(int position) {
        rclAllMails.setAdapter(new AllMailAdapter(getContext(),allMails));
    }
}
