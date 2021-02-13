package com.tohandesign.turkeycovidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.tohandesign.turkeycovidtracker.Notifications.NotificationReceiver;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Switch aSwitch;
    Switch notiSwitch;
    SharedPreferences sharedPref;
    TextView languageBtn;


    final static int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getViews();

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(aSwitch.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("darkModeValue" , true);

                    editor.commit();
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("darkModeValue" , false);
                    editor.commit();
                }
            }
        });

        notiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(notiSwitch.isChecked()){
                    Log.i("JsonLog", "Bildirimler Açıldı");
                    setAlarm(true);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("notificationValue" , true);
                    editor.commit();
                }else {
                    Log.i("JsonLog", "Bildirimler Kapandı");
                    setAlarm(false);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("notificationValue" , false);
                    editor.commit();
                }
            }
        });





    }

    public void getViews(){
        aSwitch=(Switch)findViewById(R.id.darkModeSwitch);
        sharedPref =  this.getSharedPreferences("sharedPref",Context.MODE_PRIVATE);
        getThemeMode();

        notiSwitch=(Switch)findViewById(R.id.switch2);
        getNotificationMode();

        languageBtn = (TextView) findViewById(R.id.textView7);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navmenu);
        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,null,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();

    }

    public void getThemeMode(){
        boolean darkModeValue = sharedPref.getBoolean("darkModeValue",false);
        if (darkModeValue) {
                aSwitch.setChecked(true);
        }

    }

    public void getNotificationMode(){
        boolean darkNotificationValue = sharedPref.getBoolean("notificationValue",false);
        if (darkNotificationValue) {
            notiSwitch.setChecked(true);
        }

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch (id){

            case R.id.nav_home:
                Intent h= new Intent(this, MainActivity.class);
                startActivity(h);
                break;
            case R.id.nav_daily:
                Intent i= new Intent(this, DataActivity.class);
                startActivity(i);
                break;
            case R.id.nav_settings:
                Intent s= new Intent(this, SettingsActivity.class);
                startActivity(s);
                break;
            case R.id.nav_table:
                Intent t= new Intent(this, TableDataActivity.class);
                startActivity(t);
                break;
            case R.id.nav_profile:
                Uri uriProfile = Uri.parse("https://github.com/yasintohan");
                Intent intentUri = new Intent(Intent.ACTION_VIEW, uriProfile);
                startActivity(intentUri);
                break;
            case R.id.nav_github:
                Uri uriGithub = Uri.parse("https://github.com/yasintohan/Turkey-Covid-Tracker");
                Intent intentGit = new Intent(Intent.ACTION_VIEW, uriGithub);
                startActivity(intentGit);
                break;
            case R.id.nav_vaccine:
                Intent v= new Intent(this, VaccineActivity.class);
                startActivity(v);
                break;


        }



        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void navDraverClicked(View v)
    {
        drawerLayout.openDrawer(Gravity.LEFT);
    }


    public void languageClick(View v){
        PopupMenu popup = new PopupMenu(SettingsActivity.this, languageBtn);

        popup.getMenuInflater().inflate(R.menu.language_menu, popup.getMenu());


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_tr:
                        setLocale("tr");
                        break;
                    case R.id.menu_en:
                        setLocale("en");
                        break;
                    case R.id.menu_de:
                        setLocale("de");
                        break;

                }
                return true;
            }
        });

        popup.show();

    }

    public void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("language", MODE_PRIVATE).edit();
        editor.putString("Language", lang);
        editor.apply();
        setContentView(R.layout.activity_settings);
        getViews();
    }

    private void setAlarm(Boolean active){

        Intent intent = new Intent(getBaseContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), REQUEST_CODE, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        if(active) {
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime(),
                    5*60*1000,
                    pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
        }

    }




}