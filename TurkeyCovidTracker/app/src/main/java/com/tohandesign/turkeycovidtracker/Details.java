package com.tohandesign.turkeycovidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.scales.DateTime;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Details extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    CovidTracker covidTracker;
    public static int clicked;
    public static TextView mainCountText;
    public static TextView mainText;
    public static TextView mainRateText;
    public static TextView weeklyRateText;
    public static TextView monthlyRateText;

    public static CovidInfoItem covidItem;
    public static List<CovidInfoItem> itemList = new ArrayList<CovidInfoItem>();;
    Calendar calendar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mainCountText = (TextView) findViewById(R.id.mainCount);
        mainText = (TextView) findViewById(R.id.textView18);
        mainRateText = (TextView) findViewById(R.id.textView25);
        weeklyRateText = (TextView) findViewById(R.id.textView21);
        monthlyRateText = (TextView) findViewById(R.id.textView23);


        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navmenu);
        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,null,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();


        Intent intent = getIntent();
        clicked = intent.getIntExtra("Clicked", 1);

        covidTracker = new CovidTracker(this);
        covidTracker.execute();
        covidItem = itemList.get(0);

        setCalenderDate(covidItem.getDate());


        AnyChartView anyChartView = findViewById(R.id.imageView17);

        Cartesian cartesian = AnyChart.column();
        List<DataEntry> data = new ArrayList<>();









        float mainRate = 0;
        float weeklyRate = 0;
        float monthlyRate = 0;
        weeklyRate = getWeeklyRate(clicked);
        monthlyRate = getMonthlyRate(clicked);

        switch (Details.clicked) {
            case 1:
                mainCountText.setText(covidItem.getTotalCase());
                mainText.setText("Total Cases");

                mainRate = getRate(itemList.get(0).getTotalCase(),itemList.get(1).getTotalCase());


                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getTotalCase();
                    veri = veri.replace(".", "");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Integer.parseInt(veri)));
                }


                break;

            case 2:
                mainCountText.setText(covidItem.getTotalDeath());
                mainText.setText("Total Deaths");


                mainRate = getRate(itemList.get(0).getTotalDeath(),itemList.get(1).getTotalDeath());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getTotalDeath();
                    veri = veri.replace(".", "");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Integer.parseInt(veri)));
                }
                break;
            case 3:
                mainCountText.setText(covidItem.getTotalRecovered());
                mainText.setText("Total Recovered");


                mainRate = getRate(itemList.get(0).getTotalRecovered(),itemList.get(1).getTotalRecovered());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getTotalRecovered();
                    veri = veri.replace(".", "");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Integer.parseInt(veri)));
                }
                break;

            case 4:
                mainCountText.setText(covidItem.getDailyCase());
                mainText.setText("Daily Cases");


                mainRate = getRate(itemList.get(0).getDailyCase(),itemList.get(1).getDailyCase());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getDailyCase();
                    veri = veri.replace(".", "");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Integer.parseInt(veri)));
                }
                break;

            case 5:
                mainCountText.setText(covidItem.getDailyTest());
                mainText.setText("Daily Test");



                mainRate = getRate(itemList.get(0).getDailyTest(),itemList.get(1).getDailyTest());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getDailyTest();
                    veri = veri.replace(".", "");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Integer.parseInt(veri)));
                }
                break;

            case 6:
                mainCountText.setText(covidItem.getDailyPatient());
                mainText.setText("Daily Patient");


                mainRate = getRate(itemList.get(0).getDailyPatient(),itemList.get(1).getDailyPatient());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getDailyPatient();
                    veri = veri.replace(".", "");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Integer.parseInt(veri)));
                }
                break;

            case 7:
                mainCountText.setText(covidItem.getDailyDeath());
                mainText.setText("Daily Deaths");



                mainRate = getRate(itemList.get(0).getDailyDeath(),itemList.get(1).getDailyDeath());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getDailyDeath();
                    veri = veri.replace(".", "");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Integer.parseInt(veri)));
                }
                break;

            case 8:
                mainCountText.setText(covidItem.getDailyRecovered());
                mainText.setText("Daily Recovered");



                mainRate = getRate(itemList.get(0).getDailyRecovered(),itemList.get(1).getDailyRecovered());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getDailyRecovered();
                    veri = veri.replace(".", "");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Integer.parseInt(veri)));
                }
                break;

            case 9:
                mainCountText.setText(covidItem.getTotalTest());
                mainText.setText("Total Tests");


                mainRate = getRate(itemList.get(0).getTotalTest(),itemList.get(1).getTotalTest());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getTotalTest();
                    veri = veri.replace(".", "");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Integer.parseInt(veri)));
                }
                break;

            case 10:
                mainCountText.setText(covidItem.getHeavyPatients());
                mainText.setText("Heavy Patients");



                mainRate = getRate(itemList.get(0).getHeavyPatients(),itemList.get(1).getHeavyPatients());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getHeavyPatients();
                    veri = veri.replace(".", "");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Integer.parseInt(veri)));
                }
                break;



            case 11:
                mainCountText.setText(covidItem.getZaturreRate());
                mainText.setText("Rate of Pneumonia");


                mainRate = getRate(itemList.get(0).getZaturreRate(),itemList.get(1).getZaturreRate());


                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getZaturreRate();
                    veri = veri.replace(",", ".");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Float.parseFloat(veri)));
                }
                break;

            case 12:
                mainCountText.setText(covidItem.getBedOccupancy());
                mainText.setText("Bed Filling Ratio");

                mainRate = getRate(itemList.get(0).getBedOccupancy(),itemList.get(1).getBedOccupancy());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getBedOccupancy();
                    veri = veri.replace(",", ".");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Float.parseFloat(veri)));
                }
                break;


            case 13:
                mainCountText.setText(covidItem.getIntensiveOccupancyRate());
                mainText.setText("Intensive Care Occupancy Rate");


                mainRate = getRate(itemList.get(0).getIntensiveOccupancyRate(),itemList.get(1).getIntensiveOccupancyRate());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getIntensiveOccupancyRate();
                    veri = veri.replace(",", ".");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Float.parseFloat(veri)));
                }
                break;

            case 14:
                mainCountText.setText(covidItem.getVentilatorRate());
                mainText.setText("Ventilator Rate");


                mainRate = getRate(itemList.get(0).getVentilatorRate(),itemList.get(1).getVentilatorRate());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getVentilatorRate();
                    veri = veri.replace(",", ".");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Float.parseFloat(veri)));
                }
                break;


            case 15:
                mainCountText.setText(covidItem.getDetectionTime() + " Hours");
                mainText.setText("Detection Time");


                mainRate = getRate(itemList.get(0).getDetectionTime(),itemList.get(1).getDetectionTime());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getDetectionTime();
                    veri = veri.replace(".", "");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Integer.parseInt(veri)));
                }
                break;


            case 16:
                mainCountText.setText(covidItem.getFilationRate());
                mainText.setText("Filation Rate");


                mainRate = getRate(itemList.get(0).getFilationRate(),itemList.get(1).getFilationRate());

                data.clear();
                for(int i = 6; i >= 0; i--) {
                    String veri = itemList.get(i).getFilationRate();
                    veri = veri.replace(",", ".");
                    data.add(new ValueDataEntry(itemList.get(i).getDate(), Float.parseFloat(veri)));
                }
                break;

            default:
                mainCountText.setText(covidItem.getTotalCase());
                break;
        }


        weeklyRateText.setText(String.format("%.2f", weeklyRate) + "%");
        weeklyRateText.setTextColor((weeklyRate < 0) ? Color.parseColor("#8F0000") : Color.parseColor("#0D8E53"));


        monthlyRateText.setText(String.format("%.2f", monthlyRate)+"%");
        monthlyRateText.setTextColor((monthlyRate < 0) ? Color.parseColor("#8F0000") : Color.parseColor("#0D8E53"));

        mainRateText.setText(String.format("%.2f", mainRate)+"%");
        mainRateText.setTextColor((mainRate < 0) ? Color.parseColor("#8F0000") : Color.parseColor("#0D8E53"));




        Column column = cartesian.column(data);

        column.tooltip()
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .format("{%Value}");

        cartesian.animation(true);




        anyChartView.setChart(cartesian);

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


    public void navDraverClicked(View v)
    {
        drawerLayout.openDrawer(Gravity.LEFT);
    }


    public float getRate(String lastCase, String recentlyCase){

            lastCase = lastCase.replace(".", "");
            recentlyCase = recentlyCase.replace(".", "");
            if(recentlyCase.contains(","))
                recentlyCase = recentlyCase.replace(",", ".");
        if(lastCase.contains(","))
            lastCase = lastCase.replace(",", ".");
        float mainRate = (float)((float)(Float.parseFloat(lastCase) - Float.parseFloat(recentlyCase)) / Float.parseFloat(recentlyCase)) * 100;

        return mainRate;
    }



    public float getWeeklyRate(int dataId) {
        float weeklyRate = 0;


        int dayCount = 0;
            dayCount = calendar.get(Calendar.DAY_OF_WEEK);
            dayCount -= 1;



        if(dayCount>0) {
            float thisWeekTotal = 0;
            for (int i = 0; i < dayCount; i++) {
                String itemData;
                switch (dataId) {
                    case 1:
                        itemData = itemList.get(i).getTotalCase();
                        break;
                    case 2:
                        itemData = itemList.get(i).getTotalDeath();
                        break;
                    case 3:
                        itemData = itemList.get(i).getTotalRecovered();
                        break;
                    case 4:
                        itemData = itemList.get(i).getDailyCase();
                        break;
                    case 5:
                        itemData = itemList.get(i).getDailyTest();
                        break;
                    case 6:
                        itemData = itemList.get(i).getDailyPatient();
                        break;
                    case 7:
                        itemData = itemList.get(i).getDailyDeath();
                        break;
                    case 8:
                        itemData = itemList.get(i).getDailyRecovered();
                        break;
                    case 9:
                        itemData = itemList.get(i).getTotalTest();
                        break;
                    case 10:
                        itemData = itemList.get(i).getHeavyPatients();
                        break;
                    case 11:
                        itemData = itemList.get(i).getZaturreRate();
                        break;
                    case 12:
                        itemData = itemList.get(i).getBedOccupancy();
                        break;
                    case 13:
                        itemData = itemList.get(i).getIntensiveOccupancyRate();
                        break;
                    case 14:
                        itemData = itemList.get(i).getVentilatorRate();
                        break;
                    case 15:
                        itemData = itemList.get(i).getDetectionTime();
                        break;
                    case 16:
                        itemData = itemList.get(i).getFilationRate();
                        break;
                    default:
                        itemData = itemList.get(i).getTotalCase();
                        break;
                }
                itemData = itemData.replace(".", "");
                itemData = itemData.replace(",", ".");
                thisWeekTotal += Float.parseFloat(itemData);
            }
            float thisWeekRate = thisWeekTotal / dayCount;

            float lastWeekTotal = 0;
            for (int i = dayCount; i < (dayCount + 7); i++) {
                String itemData;
                switch (dataId) {
                    case 1:
                        itemData = itemList.get(i).getTotalCase();
                        break;
                    case 2:
                        itemData = itemList.get(i).getTotalDeath();
                        break;
                    case 3:
                        itemData = itemList.get(i).getTotalRecovered();
                        break;
                    case 4:
                        itemData = itemList.get(i).getDailyCase();
                        break;
                    case 5:
                        itemData = itemList.get(i).getDailyTest();
                        break;
                    case 6:
                        itemData = itemList.get(i).getDailyPatient();
                        break;
                    case 7:
                        itemData = itemList.get(i).getDailyDeath();
                        break;
                    case 8:
                        itemData = itemList.get(i).getDailyRecovered();
                        break;
                    case 9:
                        itemData = itemList.get(i).getTotalTest();
                        break;
                    case 10:
                        itemData = itemList.get(i).getHeavyPatients();
                        break;
                    case 11:
                        itemData = itemList.get(i).getZaturreRate();
                        break;
                    case 12:
                        itemData = itemList.get(i).getBedOccupancy();
                        break;
                    case 13:
                        itemData = itemList.get(i).getIntensiveOccupancyRate();
                        break;
                    case 14:
                        itemData = itemList.get(i).getVentilatorRate();
                        break;
                    case 15:
                        itemData = itemList.get(i).getDetectionTime();
                        break;
                    case 16:
                        itemData = itemList.get(i).getFilationRate();
                        break;
                    default:
                        itemData = itemList.get(i).getTotalCase();
                        break;
                }
                itemData = itemData.replace(".", "");
                itemData = itemData.replace(",", ".");
                lastWeekTotal += Float.parseFloat(itemData);
            }
            float lastWeekRate = lastWeekTotal / 7;

            weeklyRate = (thisWeekRate-lastWeekRate) / lastWeekRate * 100;

        }

        return weeklyRate;
    }



    public float getMonthlyRate(int dataId){
        float monthlyRate = 0;
        int monthCount = 0;
        int dayCounter = 0;
        monthCount = calendar.get(Calendar.MONTH);
        monthCount += 1;
        int testMonth = monthCount;

        float thismonth = 0;
        float lastmonth = 0;

        int i = 0;
        while(testMonth == monthCount) {

            String itemData;
            switch (dataId) {
                case 1:
                    itemData = itemList.get(i).getTotalCase();
                    break;
                case 2:
                    itemData = itemList.get(i).getTotalDeath();
                    break;
                case 3:
                    itemData = itemList.get(i).getTotalRecovered();
                    break;
                case 4:
                    itemData = itemList.get(i).getDailyCase();
                    break;
                case 5:
                    itemData = itemList.get(i).getDailyTest();
                    break;
                case 6:
                    itemData = itemList.get(i).getDailyPatient();
                    break;
                case 7:
                    itemData = itemList.get(i).getDailyDeath();
                    break;
                case 8:
                    itemData = itemList.get(i).getDailyRecovered();
                    break;
                case 9:
                    itemData = itemList.get(i).getTotalTest();
                    break;
                case 10:
                    itemData = itemList.get(i).getHeavyPatients();
                    break;
                case 11:
                    itemData = itemList.get(i).getZaturreRate();
                    break;
                case 12:
                    itemData = itemList.get(i).getBedOccupancy();
                    break;
                case 13:
                    itemData = itemList.get(i).getIntensiveOccupancyRate();
                    break;
                case 14:
                    itemData = itemList.get(i).getVentilatorRate();
                    break;
                case 15:
                    itemData = itemList.get(i).getDetectionTime();
                    break;
                case 16:
                    itemData = itemList.get(i).getFilationRate();
                    break;
                default:
                    itemData = itemList.get(i).getTotalCase();
                    break;
            }
            itemData = itemData.replace(".", "");
            itemData = itemData.replace(",", ".");
            thismonth += Float.parseFloat(itemData);
            dayCounter++;
            i++;
            setCalenderDate(itemList.get(i).getDate());
            monthCount = calendar.get(Calendar.MONTH) + 1;


        }
        thismonth = thismonth/dayCounter;
        dayCounter = 0;

        while(monthCount == (testMonth-1)) {

            String itemData;
            switch (dataId) {
                case 1:
                    itemData = itemList.get(i).getTotalCase();
                    break;
                case 2:
                    itemData = itemList.get(i).getTotalDeath();
                    break;
                case 3:
                    itemData = itemList.get(i).getTotalRecovered();
                    break;
                case 4:
                    itemData = itemList.get(i).getDailyCase();
                    break;
                case 5:
                    itemData = itemList.get(i).getDailyTest();
                    break;
                case 6:
                    itemData = itemList.get(i).getDailyPatient();
                    break;
                case 7:
                    itemData = itemList.get(i).getDailyDeath();
                    break;
                case 8:
                    itemData = itemList.get(i).getDailyRecovered();
                    break;
                case 9:
                    itemData = itemList.get(i).getTotalTest();
                    break;
                case 10:
                    itemData = itemList.get(i).getHeavyPatients();
                    break;
                case 11:
                    itemData = itemList.get(i).getZaturreRate();
                    break;
                case 12:
                    itemData = itemList.get(i).getBedOccupancy();
                    break;
                case 13:
                    itemData = itemList.get(i).getIntensiveOccupancyRate();
                    break;
                case 14:
                    itemData = itemList.get(i).getVentilatorRate();
                    break;
                case 15:
                    itemData = itemList.get(i).getDetectionTime();
                    break;
                case 16:
                    itemData = itemList.get(i).getFilationRate();
                    break;
                default:
                    itemData = itemList.get(i).getTotalCase();
                    break;
            }
            itemData = itemData.replace(".", "");
            itemData = itemData.replace(",", ".");
            lastmonth += Float.parseFloat(itemData);
            dayCounter++;
            i++;
            setCalenderDate(itemList.get(i).getDate());
            monthCount = calendar.get(Calendar.MONTH)+ 1;

        }
        lastmonth = lastmonth/dayCounter;

        monthlyRate = (thismonth-lastmonth) / lastmonth * 100;
        return monthlyRate;
    }


    public void setCalenderDate(String date) {
        calendar = Calendar.getInstance();
        date = date.replace(".","/");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            calendar.setTime(sdf.parse(date));

        } catch (ParseException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }



}