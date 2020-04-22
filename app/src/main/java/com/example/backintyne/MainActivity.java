package com.example.backintyne;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.backintyne.data.DataManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataManager.createDataManager(getResources());

        setContentView(R.layout.activity_main);
        view= this.getWindow().getDecorView();
        // Setup bottom bar
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Setup top bar
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_map, R.id.navigation_search, R.id.navigation_settings)
                .build();

        // Setup app navigation
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Set action bar configurations
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //change the apps colour to its colour blind mode
    public void changeColourCBM(View v){
        Window window = this.getWindow();
        view.setBackgroundResource(R.color.colourBlindMain);//set the background colour
        window.setStatusBarColor(getResources().getColor(R.color.colourBlindStatus));//change the status bar colour
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colourBlindSecondary)));//change the support bar
    }

    //change the apps colour scheme to its dark mode
    public void changeColourDM(View v){
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.black));
        view.setBackgroundResource(R.color.dark);//set the background to dark gray
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkPrimary)));//change the support bar colour
    }

    //reset the colours to remove other colour schemes
    public void changeColourReset(View v){
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.black));
        view.setBackgroundResource(R.color.colourBackground);//reset the background colour
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));//reset the support bar
    }


}
