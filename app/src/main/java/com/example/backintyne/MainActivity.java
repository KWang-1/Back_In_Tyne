package com.example.backintyne;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;

import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        DataManager.createDataManager(getResources());
    }


    public void changeColourCBM(View v){
        Window window = this.getWindow();
        view.setBackgroundResource(R.color.colourBlind);
        window.setStatusBarColor(getResources().getColor(R.color.colourBlind));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colourBlind)));
    }

    public void changeColourDM(View v){
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.black));
        view.setBackgroundResource(R.color.dark);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkPrimary)));
    }

    public void changeColourReset(View v){
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.black));
        view.setBackgroundResource(R.color.colourBackground);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
    }

}
