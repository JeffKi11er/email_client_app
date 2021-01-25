package com.example.email_client_app.helper;

import android.util.Log;
import android.widget.Toast;

import com.example.email_client_app.R;
import com.example.email_client_app.item.ItemEmail;
import com.example.email_client_app.item.ItemSchedule;
import com.example.email_client_app.item.ItemSentEmail;
import com.example.email_client_app.item.ItemSocial;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BrainResource {
    public static ArrayList<ItemEmail>getEmails(){
        ArrayList<ItemEmail>itemEmails = new ArrayList<>();
        itemEmails.add(new ItemEmail("Nguyen Cong Thanh",timeValueDate(Calendar.getInstance().getTime()), R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",false,"","inbox",false));
        itemEmails.add(new ItemEmail("Nguyen An Thiet",timeValueDate(Calendar.getInstance().getTime()), R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",false,"","inbox",false));
        itemEmails.add(new ItemEmail("Tran Minh Hieu",timeValueDate(Calendar.getInstance().getTime()), R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",false,"","inbox",false));
        itemEmails.add(new ItemEmail("Nguyen Nhu Hieu",timeValueDate(Calendar.getInstance().getTime()), R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",false,"","inbox",false));
        itemEmails.add(new ItemEmail("Nguyen Thanh Vinh",timeValueDate(Calendar.getInstance().getTime()), R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",false,"","inbox",false));
        itemEmails.add(new ItemEmail("Nguyen Quynh Nhu",timeValueDate(Calendar.getInstance().getTime()), R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",false,"","inbox",false));
        itemEmails.add(new ItemEmail("Hoang Phuong Ly",timeValueDate(Calendar.getInstance().getTime()), R.drawable.streamer,false,"Không tiêu đề",
                "đã bảo là không có tiêu đề",false,"","inbox",false));
        return itemEmails;
    }
    public static String timeValueTime(Date time){
        String date ="";
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        try{
            date = date + format.format(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }
    public static String timeValueDate(Date time){
        String date ="";
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try{
            date = date + format.format(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }
    public static Date getTime(String time){
        Date date = new Date();
        try {
            date = new SimpleDateFormat("HH:mm:ss").parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
    public static Date getDate(String time){
        Date date = new Date();
        try {
            date = new SimpleDateFormat("MM/dd/yyyy").parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
    public static ArrayList<ItemSchedule>getScheduleEmails(){
        ArrayList<ItemSchedule>scheduleItems = new ArrayList<>();
        for (ItemEmail email:getEmails()) {
            scheduleItems.add(new ItemSchedule("TH "+email.getName(),
                    timeValueDate(getDate(email.getDate())),
                    R.drawable.avatar,
                    false,
                    "Điều kỳ diệu : "+email.getSubject(),
                    "Description"+ email.getDescription(),
                    R.drawable.ic_pdf,
                    timeValueTime(Calendar.getInstance().getTime()),
                    "[123 USTH]",
                    R.drawable.ic_star));
        }
        return scheduleItems;
    }
//    public static ArrayList<ItemSchedule>getScheduleEmails(){
//        ArrayList<ItemSchedule>scheduleItems = new ArrayList<>();
//        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
//        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
//        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
//        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
//        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
//        scheduleItems.add(new ItemSchedule("TH", "21/12/2020", R.drawable.avatar, false, "Điều kỳ diệu", "Description", R.drawable.ic_pdf, "09:00AM", "[123 USTH]", R.drawable.ic_star));
//        return scheduleItems;
//    }
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
        for (ItemEmail itemEmail:getEmails()) {
            socialItems.add(new ItemSocial(itemEmail.getName(),
                    itemEmail.getDate(),
                    R.drawable.streamer,
                    false,
                    itemEmail.getSubject(),
                    itemEmail.getDescription(),
                    false,
                    "",
                    "social",
                    true,
                    R.drawable.camera,
                    false
                    ));
        }
//        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
//                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
//        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
//                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, true));
//        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
//                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, true));
//        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
//                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
//        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
//                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
//        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
//                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, true));
//        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
//                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
//        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
//                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
//        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
//                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
//        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
//                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, true));
//        socialItems.add(new ItemSocial("FaceBook", "23/12/2012", R.drawable.streamer, false, "You received " +
//                "a message", "Get 1000+ messages from your scocial media", R.drawable.camera, false));
        return socialItems;
    }
}
