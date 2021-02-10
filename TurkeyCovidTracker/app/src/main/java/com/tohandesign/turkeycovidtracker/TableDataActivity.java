package com.tohandesign.turkeycovidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.levitnudi.legacytableview.LegacyTableView;

import java.util.ArrayList;
import java.util.List;



public class TableDataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    public static List<CovidInfoItem> itemList = new ArrayList<CovidInfoItem>();;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        //set table title labels
        LegacyTableView.insertLegacyTitle("Date", "Daily Test", "Daily Case", "Daily Patient", "Daily Death", "Daily Recovered", "Total Test", "Total Case", "Total Death", "Total Recovered", "Heavy Patients","Rate of Pneumonia", "Bed Filling Ratio","Intensive Occupancy Rate", "Ventilator Rate", "Detection Time", "Filation Rate");
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



}