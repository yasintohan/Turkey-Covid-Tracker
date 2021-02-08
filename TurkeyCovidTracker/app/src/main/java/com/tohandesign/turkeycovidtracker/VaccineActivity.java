package com.tohandesign.turkeycovidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;
import com.google.android.material.navigation.NavigationView;
import com.levitnudi.legacytableview.LegacyTableView;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class VaccineActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    public static TextView mainCountText;
    ImageView mapImage;

    public static int vaccineCount = 0;
    public static List<VaccineItem> itemList = new ArrayList<VaccineItem>();


    private static final String[] TABLE_HEADERS = { "#","City",  "Vaccinated"};
    private static final String[][] DATA_TO_SHOW = new String[81][3];



    public VectorDrawableCompat.VFullPath pathNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navmenu);
        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,null,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();

        mainCountText = (TextView) findViewById(R.id.mainCount);
        mainCountText.setText(String.valueOf(vaccineCount));

        mapImage = (ImageView) findViewById(R.id.mapImage);





        mapImage.invalidate();

        int counter = 0;
        for(VaccineItem item : VaccineTracker.itemList) {
            DATA_TO_SHOW[counter][0] = String.valueOf(counter + 1);
            DATA_TO_SHOW[counter][1] = item.getCityName();
            DATA_TO_SHOW[counter][2] =  String.valueOf(item.getValue());
            counter++;
        }

        TableView<String[]> tableView = (TableView<String[]>) findViewById(R.id.legacy_table_view);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, TABLE_HEADERS));
        tableView.setDataAdapter(new SimpleTableDataAdapter(this, DATA_TO_SHOW));
        tableView.setHeaderBackgroundColor(Color.parseColor("#FF018786"));
        tableView.addDataClickListener(new TableDataClickListener() {
            @Override
            public void onDataClicked(int rowIndex, Object clickedData) {
                setCityBg(((String[])clickedData)[0], ((String[])clickedData)[1]);
            }
        });




    }

    public void setCityBg(String city, String name){
        VectorChildFinder vector = new VectorChildFinder(getApplication(), R.drawable.ic_turkey_map, mapImage);
        //Toast.makeText(VaccineActivity.this, name, Toast.LENGTH_SHORT).show();
        String newPathStr = city + "city";
        pathNew = vector.findPathByName(newPathStr);
        pathNew.setFillColor(Color.RED);
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


    public void navDraverClicked(View v)
    {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

}