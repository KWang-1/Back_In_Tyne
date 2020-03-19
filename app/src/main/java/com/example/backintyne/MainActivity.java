package com.example.backintyne;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;
import com.example.backintyne.ui.info.InfoFragment;
import com.example.backintyne.ui.map.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static DataManager dataManager;
    private InfoFragment infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        dataManager = new DataManager(getResources(), getResources().openRawResource(R.raw.site_data));
    }

    public static DataManager getDataManager() {
        return dataManager;
    }
}
