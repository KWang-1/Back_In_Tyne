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

/**
 * Activity loaded on startup.
 * Initializes base layout, navigation system, and data management system.
 * Implements app settings.
 */
public class MainActivity extends AppCompatActivity {

    private View decorView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize data management module
        DataManager.createDataManager(getResources());

        // Setup layout
        setContentView(R.layout.activity_main);

        // Save reference to app background
        decorView = this.getWindow().getDecorView();

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

        // Setup action bar
        actionBar = getSupportActionBar();
        assert actionBar != null;
    }

    // Implements action bar back button functionality
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
        decorView.setBackgroundResource(R.color.colourBlindMain); // set the background colour
        window.setStatusBarColor(getResources().getColor(R.color.colourBlindStatus)); // change the status bar colour
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colourBlindSecondary))); // change the support bar
    }

    //change the apps colour scheme to its dark mode
    public void changeColourDM(View v){
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.black));
        decorView.setBackgroundResource(R.color.dark); // set the background to dark gray
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkPrimary))); // change the support bar colour
    }

    //reset the colours to remove other colour schemes
    public void changeColourReset(View v){
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.black));
        decorView.setBackgroundResource(R.color.colourBackground); // reset the background colour
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary))); // reset the support bar
    }


}
