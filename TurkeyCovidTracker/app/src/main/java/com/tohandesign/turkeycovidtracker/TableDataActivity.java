package com.tohandesign.turkeycovidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.levitnudi.legacytableview.LegacyTableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class TableDataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    public static List<CovidInfoItem> itemList = new ArrayList<CovidInfoItem>();;

    SharedPreferences langSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        langSharedPref = this.getSharedPreferences("language",Context.MODE_PRIVATE);
        String lang = langSharedPref.getString("Language", "en");
        setLocale(this,lang);

        setContentView(R.layout.activity_table_data);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navmenu);
        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,null,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();



        itemList = DataActivity.itemList;

        String date = getResources().getString(R.string.date);
        String dailtTest = getResources().getString(R.string.dailyTest);
        String dailtCase = getResources().getString(R.string.dailyCase);
        String dailtPatient = getResources().getString(R.string.dailyPatient);
        String dailtDeath = getResources().getString(R.string.dailyDeaths);
        String dailtRecovered = getResources().getString(R.string.dailyRecovered);
        String totalTest = getResources().getString(R.string.totalTest);
        String totalCase = getResources().getString(R.string.totalCases);
        String totalDeath = getResources().getString(R.string.totalDeaths);
        String totalRecovered = getResources().getString(R.string.totalRecovered);
        String heavyPatients = getResources().getString(R.string.heavyPatients);
        String pneumoniaRate = getResources().getString(R.string.pneumoniaRate);
        String bedFilling = getResources().getString(R.string.bedFilling);
        String intensiveRate = getResources().getString(R.string.intensiveRate);
        String ventilatorRate = getResources().getString(R.string.ventilatorRate);
        String detectionTime = getResources().getString(R.string.detectionRate);
        String filationRate = getResources().getString(R.string.filationRate);


        //set table title labels
        LegacyTableView.insertLegacyTitle(date, dailtTest, dailtCase, dailtPatient, dailtDeath, dailtRecovered, totalTest, totalCase, totalDeath, totalRecovered, heavyPatients,pneumoniaRate, bedFilling,intensiveRate, ventilatorRate, detectionTime, filationRate);
        //set table contents as string arrays
        for (CovidInfoItem item : itemList) {
            LegacyTableView.insertLegacyContent(item.getDate(),item.getDailyTest(),item.getDailyCase(),item.getDailyPatient(),item.getDailyDeath(),item.getDailyRecovered(),
                    item.getTotalTest(),item.getTotalCase(),item.getTotalDeath(),item.getTotalRecovered(),item.getHeavyPatients(),
                    item.getZaturreRate()+"%",item.getBedOccupancy()+"%",item.getIntensiveOccupancyRate()+"%",item.getVentilatorRate()+"%",item.getDetectionTime()+"Hours",item.getFilationRate()+"%");

        }



        LegacyTableView legacyTableView = (LegacyTableView)findViewById(R.id.legacy_table_view);
        legacyTableView.setTitle(LegacyTableView.readLegacyTitle());
        legacyTableView.setContent(LegacyTableView.readLegacyContent());
        legacyTableView.setTheme(10);
        legacyTableView.setHeaderBackgroundLinearGradientBOTTOM("#0D8E53");
        legacyTableView.setHeaderBackgroundLinearGradientTOP("#0D8E53");

        legacyTableView.setTablePadding(7);

        legacyTableView.build();



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
            case R.id.nav_profile:
                Uri uriProfile = Uri.parse("https://github.com/yasintohan");
                Intent intentUri = new Intent(Intent.ACTION_VIEW, uriProfile);
                startActivity(intentUri);
                break;
            case R.id.nav_table:
                Intent t= new Intent(this, TableDataActivity.class);
                startActivity(t);
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


    public void setLocale(Activity activity, String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }


}