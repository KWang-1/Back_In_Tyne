package com.example.backintyne.ui.map;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.backintyne.MainActivity;
import com.example.backintyne.R;
import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;
import com.example.backintyne.ui.info.InfoFragment;

public class MapFragment extends Fragment {

    private MapViewModel dashboardViewModel;
    private Button mapSite0;
    private Button mapSite1;
    private Button mapSite2;
    private SiteEntry siteEntry;
    private DataManager dataManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(MapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        mapSite0 = root.findViewById(R.id.mapSite0);
        mapSite1 = root.findViewById(R.id.mapSite1);
        mapSite2 = root.findViewById(R.id.mapSite2);

        final Bundle bundle = new Bundle();
        dataManager = MainActivity.getDataManager();

        mapSite0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siteEntry = dataManager.getSiteData().get(0);
                bundle.putParcelable("SiteEntryFromMap", siteEntry);
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_map_to_navigation_info, bundle);
            }
        });

        mapSite1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siteEntry = dataManager.getSiteData().get(1);
                bundle.putParcelable("SiteEntryFromMap", siteEntry);
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_map_to_navigation_info, bundle);
            }
        });

        mapSite2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                siteEntry = dataManager.getSiteData().get(2);
                bundle.putParcelable("SiteEntryFromMap", siteEntry);
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_map_to_navigation_info, bundle);
            }
        });

        return root;
    }
}