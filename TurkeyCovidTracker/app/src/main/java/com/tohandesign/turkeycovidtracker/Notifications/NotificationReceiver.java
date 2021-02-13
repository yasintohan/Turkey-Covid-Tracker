package com.tohandesign.turkeycovidtracker.Notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.tohandesign.turkeycovidtracker.CovidInfoItem;
import com.tohandesign.turkeycovidtracker.CovidTracker;
import com.tohandesign.turkeycovidtracker.DataActivity;
import com.tohandesign.turkeycovidtracker.Details;
import com.tohandesign.turkeycovidtracker.MainActivity;
import com.tohandesign.turkeycovidtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NotificationReceiver extends BroadcastReceiver {

    private static String currentCheckDate;
    public static String currentDate;

    @Override
    public void onReceive(Context context, Intent intent) {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        currentDate = df.format(c);


        CovidInfoItem item= getCheck();
        currentCheckDate = item.getDate();


        if (currentCheckDate==null)
            currentCheckDate = "";

        SharedPreferences sharedPref = context.getSharedPreferences("notification",context.MODE_PRIVATE);;

        if (currentCheckDate.equals(currentDate) && !sharedPref.getBoolean(currentDate, false)) {

            runNotification(context, item);

            SharedPreferences.Editor editor = context.getSharedPreferences("notification", context.MODE_PRIVATE).edit();
            editor.putBoolean(currentDate, true);
            editor.apply();


        }



    }


    private void runNotification(Context context , CovidInfoItem item){

        NotificationManager mNotificationManager;

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context.getApplicationContext(), "notify_001");
        Intent ii = new Intent(context.getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, ii, 0);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(context.getResources().getString(R.string.dailyTest)+": " + item.getDailyTest() + "\n" +
                context.getResources().getString(R.string.dailyCase)+": " + item.getDailyCase() +"\n"+
                context.getResources().getString(R.string.dailyPatient)+": " + item.getDailyPatient() + "\n"+
                context.getResources().getString(R.string.dailyDeaths)+": " + item.getDailyDeath()+ "\n"+
                context.getResources().getString(R.string.dailyRecovered)+": " + item.getDailyRecovered());
        bigText.setBigContentTitle(context.getResources().getString(R.string.notiTitle));
        bigText.setSummaryText(item.getDate());

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_coronavirus_24);
        mBuilder.setContentTitle(context.getResources().getString(R.string.notiTitle));
        mBuilder.setContentText(context.getResources().getString(R.string.notisubTitle));
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(bigText);

        mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "notify_001";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Turkey Covid Tracker",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(0, mBuilder.build());



    }


    private static CovidInfoItem getCheck() {
        CovidInfoItem item = new CovidInfoItem();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        Document doc;
        String url = "https://covid19.saglik.gov.tr/";
        String date = "00.00.0000";
        try {

            doc = Jsoup.connect(url).ignoreContentType(true).get();

            Element elements=doc.select("script").last();

            String veri=elements.html();
            String aciklama=Jsoup.parse(veri).text();
            aciklama = aciklama.replace("// var sondurumjson = ", "{ variables: ");
            aciklama = aciklama.replace(";//", "}");

            JSONObject jsnobject = new JSONObject(aciklama);

            JSONArray jsonArray = jsnobject.getJSONArray("variables");

            JSONObject explrObject = jsonArray.getJSONObject(0);

            item.setDate(explrObject.getString("tarih"));
            item.setDailyTest(explrObject.getString("gunluk_test"));
            item.setDailyCase(explrObject.getString("gunluk_vaka"));
            item.setDailyPatient(explrObject.getString("gunluk_hasta"));
            item.setDailyDeath(explrObject.getString("gunluk_vefat"));
            item.setDailyRecovered(explrObject.getString("gunluk_iyilesen"));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.i("JsonLog", "olmadı");
        }
        return item;
    }



}
