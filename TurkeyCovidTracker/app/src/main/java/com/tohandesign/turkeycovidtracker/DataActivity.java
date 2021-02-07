package com.tohandesign.turkeycovidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    CovidTracker covidTracker;

    public static CovidInfoItem covidItem;
    public static List<CovidInfoItem> itemList = new ArrayList<CovidInfoItem>();;

    TextView dateText;
    TextView newTestText;
    TextView newCaseText;
    TextView newPatientText;
    TextView newDeathText;
    TextView newRecoText;
    TextView zaturreText;
    TextView bedText;
    TextView careText;
    TextView ventilatorText;
    TextView detectionText;
    TextView filiationText;
    TextView totalTestText;
    TextView totalCaseText;
    TextView totalDeathText;
    TextView totalPatientText;
    TextView totalRecoText;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


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
        covidItem = itemList.get(0);


        dateText = (TextView) findViewById(R.id.dateText);
        newTestText = (TextView) findViewById(R.id.newTestText);
        newCaseText = (TextView) findViewById(R.id.newCaseText);
        newPatientText = (TextView) findViewById(R.id.newPatientText);
        newDeathText = (TextView) findViewById(R.id.newDeathText);
        newRecoText = (TextView) findViewById(R.id.newRecoText);
        zaturreText = (TextView) findViewById(R.id.ZaturreRateText);
        bedText = (TextView) findViewById(R.id.bedFillingText);
        careText = (TextView) findViewById(R.id.IntensiveOccupancyText);
        ventilatorText = (TextView) findViewById(R.id.VentilatorText);
        detectionText = (TextView) findViewById(R.id.DetectionText);
        filiationText = (TextView) findViewById(R.id.FilationRateText);
        totalTestText = (TextView) findViewById(R.id.totalTestText);
        totalCaseText = (TextView) findViewById(R.id.totalCaseTest);
        totalDeathText = (TextView) findViewById(R.id.totalDeathText);
        totalPatientText = (TextView) findViewById(R.id.totalHighPatientText);
        totalRecoText = (TextView) findViewById(R.id.totalRecoveredText);

        dateText.setText(covidItem.getDate());
        newTestText.setText(covidItem.getDailyTest());
        newCaseText.setText(covidItem.getDailyCase());
        newPatientText.setText(covidItem.getDailyPatient());
        newDeathText.setText(covidItem.getDailyDeath());
        newRecoText.setText(covidItem.getDailyRecovered());
        zaturreText.setText(covidItem.getZaturreRate()+"%");
        bedText.setText(covidItem.getBedOccupancy()+"%");
        careText.setText(covidItem.getIntensiveOccupancyRate()+"%");
        ventilatorText.setText(covidItem.getVentilatorRate()+"%");
        detectionText.setText(covidItem.getDetectionTime()+" Hours");
        filiationText.setText(covidItem.getFilationRate()+"%");
        totalTestText.setText(covidItem.getTotalTest());
        totalCaseText.setText(covidItem.getTotalCase());
        totalDeathText.setText(covidItem.getTotalDeath());
        totalPatientText.setText(covidItem.getHeavyPatients());
        totalRecoText.setText(covidItem.getTotalRecovered());



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


    public void itemsClicked(View v) {
        int id=v.getId();
        Intent intent;
        switch (id){

            case R.id.totalTestCount:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 9);
                startActivity(intent);
                break;

            case R.id.totalCaseCount:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 1);
                startActivity(intent);
                break;

            case R.id.totalDeathCount:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 2);
                startActivity(intent);
                break;


            case R.id.totalHighPatientCount:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 10);
                startActivity(intent);
                break;

            case R.id.totalRecoveredCount:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 3);
                startActivity(intent);
                break;


            case R.id.newTestCount:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 5);
                startActivity(intent);
                break;
            case R.id.newCaseCount:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 4);
                startActivity(intent);
                break;
            case R.id.newPatientCount:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 6);
                startActivity(intent);
                break;
            case R.id.newDeathCount:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 7);
                startActivity(intent);
                break;
            case R.id.newRecoCount:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 8);
                startActivity(intent);
                break;


            case R.id.ZaturreRateCount:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 11);
                startActivity(intent);
                break;

            case R.id.bedFillingRatio:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 12);
                startActivity(intent);
                break;

            case R.id.IntensiveOccupancyRate:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 13);
                startActivity(intent);
                break;

            case R.id.VentilatorRate:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 14);
                startActivity(intent);
                break;


            case R.id.DetectionTime:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 15);
                startActivity(intent);
                break;

            case R.id.FilationRate:
                intent = new Intent(this, Details.class);
                intent.putExtra("Clicked", 16);
                startActivity(intent);
                break;







        }

    }

}