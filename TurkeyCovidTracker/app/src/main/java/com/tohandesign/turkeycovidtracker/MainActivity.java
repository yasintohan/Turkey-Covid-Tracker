package com.tohandesign.turkeycovidtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
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

    CovidTracker covidTracker;
    public static TextView totalDeathText;
    public static TextView totalCaseText;
    public static TextView totalRecoveredText;
    public static TextView dailyCaseText;

    public static TextView dateText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalDeathText = (TextView) findViewById(R.id.totalDeath);
        totalCaseText = (TextView) findViewById(R.id.totalCase);
        totalRecoveredText = (TextView) findViewById(R.id.totalRecovered);
        dailyCaseText = (TextView) findViewById(R.id.dailyCase);
        dateText = (TextView) findViewById(R.id.dateText);


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
}



