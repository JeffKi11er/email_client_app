package com.example.email_client_app.helper;

import android.util.Log;
import android.widget.Toast;

import com.example.email_client_app.R;
import com.example.email_client_app.item.ItemEmail;
import com.example.email_client_app.item.ItemSchedule;
import com.example.email_client_app.item.ItemSentEmail;
import com.example.email_client_app.item.ItemSocial;

import java.util.ArrayList;
import java.util.List;

public class BrainResource {
    public static ArrayList<ItemEmail>getEmails(){
        ArrayList<ItemEmail>itemEmails = new ArrayList<>();
        itemEmails.add(new ItemEmail("Nguyen Cong Thanh","15/12/2020", R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        itemEmails.add(new ItemEmail("Nguyen An Thiet","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        itemEmails.add(new ItemEmail("Tran Minh Hieu","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        itemEmails.add(new ItemEmail("Nguyen Thanh Vinh","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        itemEmails.add(new ItemEmail("Nguyen Nhu Hieu","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        itemEmails.add(new ItemEmail("Dang Nhu Quynh","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        itemEmails.add(new ItemEmail("Nguyen Bich Mai","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề"));
        return itemEmails;
    }
    public static ArrayList<ItemSchedule>getScheduleEmails(){
        ArrayList<ItemSchedule>scheduleItems = new ArrayList<>();
        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
        return scheduleItems;
    }
    public static ArrayList<ItemSentEmail>getSentEmails(){
        ArrayList<ItemSentEmail>sentItems = new ArrayList<>();
        sentItems.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",6));
        sentItems.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",4));
        sentItems.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",3));
        sentItems.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",2));
        sentItems.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",4));
        sentItems.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",2));
        sentItems.add(new ItemSentEmail("Nguyen Cong Thanh","15/12/2020",R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",3));
        return sentItems;
    }
    public static ArrayList<ItemSocial>getSocialItems() {
        ArrayList<ItemSocial> socialItems = new ArrayList<>();
        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, true));
        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, true));
        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, true));
        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, true));
        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
        return socialItems;
    }
}
