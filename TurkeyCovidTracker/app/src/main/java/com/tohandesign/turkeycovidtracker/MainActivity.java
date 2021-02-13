package com.tohandesign.turkeycovidtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;


    VaccineTracker vaccineTracker;
    CovidTracker covidTracker;
    public static TextView totalDeathText;
    public static TextView totalCaseText;
    public static TextView totalRecoveredText;
    public static TextView dailyCaseText;

    public static TextView dateText;

    SharedPreferences sharedPref;
    SharedPreferences langSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        langSharedPref = this.getSharedPreferences("language",Context.MODE_PRIVATE);
        String lang = langSharedPref.getString("Language", "en");
        setLocale(this,lang);

        setContentView(R.layout.activity_main);

        totalDeathText = (TextView) findViewById(R.id.totalDeath);
        totalCaseText = (TextView) findViewById(R.id.totalCase);
        totalRecoveredText = (TextView) findViewById(R.id.totalRecovered);
        dailyCaseText = (TextView) findViewById(R.id.dailyCase);
        dateText = (TextView) findViewById(R.id.dateText);

        sharedPref = this.getSharedPreferences("sharedPref",Context.MODE_PRIVATE);

        getThemeMode();



        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navmenu);
        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,null,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();






        covidTracker = new CovidTracker(this);
        covidTracker.execute();

        vaccineTracker = new VaccineTracker(this);
        vaccineTracker.execute();





    }





    public void getThemeMode(){
        boolean darkModeValue = sharedPref.getBoolean("darkModeValue",false);

        if (darkModeValue) {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }

    }

    public void TotalCaseClicked(View v)
    {
        Intent intent = new Intent(this, Details.class);
        intent.putExtra("Clicked", 1);
        startActivity(intent);
    }

    public void TotalDeathClicked(View v)
    {
        Intent intent = new Intent(this, Details.class);
        intent.putExtra("Clicked", 2);
        startActivity(intent);
    }

    public void TotalRecoveredClicked(View v)
    {
        Intent intent = new Intent(this, Details.class);
        intent.putExtra("Clicked", 3);
        startActivity(intent);
    }

    public void NewCaseClicked(View v)
    {
        Intent intent = new Intent(this, Details.class);
        intent.putExtra("Clicked", 4);
        startActivity(intent);
    }

    public void navDraverClicked(View v)
    {
        drawerLayout.openDrawer(Gravity.LEFT);
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

            case R.id.nav_vaccine:
                Intent v= new Intent(this, VaccineActivity.class);
                startActivity(v);
                break;
            case R.id.nav_github:
                Uri uriGithub = Uri.parse("https://github.com/yasintohan/Turkey-Covid-Tracker");
                Intent intentGit = new Intent(Intent.ACTION_VIEW, uriGithub);
                startActivity(intentGit);
                break;


        }



        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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



