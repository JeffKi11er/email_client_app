package com.example.email_client_app.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.email_client_app.R;
import com.example.email_client_app.adapter.AdapterItem;
import com.example.email_client_app.item.ItemEmail;
import com.example.email_client_app.item.ItemSchedule;
import com.example.email_client_app.item.ItemSentEmail;
import com.example.email_client_app.item.ItemSocial;
import com.sun.mail.pop3.POP3Store;

import java.io.IOException;
import java.security.NoSuchProviderException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;

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
    public static ArrayList<ItemSchedule>getScheduleEmails(ArrayList<ItemEmail>emails){
        ArrayList<ItemSchedule>scheduleItems = new ArrayList<>();
        for (ItemEmail email:emails) {
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
    public static void readAllMail(AdapterItem adapterItem,ArrayList<ItemEmail>itemEmails,String username,String password){
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                try {
                    //1) get the session object
                    Properties properties = new Properties();
                    properties.put("mail.pop3.host",  "mail.javatpoint.com");
                    Session emailSession = Session.getDefaultInstance(properties);

                    //2) create the POP3 store object and connect with the pop server
                    POP3Store emailStore = (POP3Store) emailSession.getStore("pop3");
                    emailStore.connect(username, password);

                    //3) create the folder object and open it
                    Folder emailFolder = emailStore.getFolder("INBOX");
                    emailFolder.open(Folder.READ_ONLY);

                    //4) retrieve the messages from the folder in an array and print it
                    Message[] messages = emailFolder.getMessages();
                    for (int i = 0; i < messages.length; i++) {
                        Message message = messages[i];
                        Log.e("BrainEmail","---------------------------------");
                        Log.e("BrainEmail","Email Number " + (i + 1));
                        Log.e("BrainEmail","Subject: " + message.getSubject());
                        Log.e("BrainEmail","From: " + message.getFrom()[0]);
                        Log.e("BrainEmail","Text: " + message.getContent().toString());
                        itemEmails.add(new ItemEmail(message.getFrom()[i].toString(),message.getSentDate().toString(),R.drawable.streamer,false,
                                message.getSubject(),message.getDescription(),false,"","inbox",false));
                    }
                    //5) close the store and folder objects
                    emailFolder.close(false);
                    emailStore.close();

                }
                catch (MessagingException e) {e.printStackTrace();}
                catch (Exception e) {e.printStackTrace();}
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                adapterItem.setItems(itemEmails);
                adapterItem.notifyDataSetChanged();
            }
        }.execute();
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
    public static ArrayList<ItemSocial>getSocialItems(ArrayList<ItemEmail>emails) {
        ArrayList<ItemSocial> socialItems = new ArrayList<>();
        for (ItemEmail itemEmail:emails) {
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
