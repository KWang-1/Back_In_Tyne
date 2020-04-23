package com.example.backintyne.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.backintyne.R;
import com.example.backintyne.data.SiteEntry;

import java.util.Objects;

/**
 * Home page fragment.
 * Displayed on app startup.
 */
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private TextView cyclingCardsTitle;
    private TextView cyclingCardsText;
    private ImageView cyclingCardsImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Setup view model

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Setup image buttons

        ImageButton mapButton = root.findViewById(R.id.map_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_home_to_navigation_map);
            }
        });

        ImageButton eventsButton = root.findViewById(R.id.events_button);
        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_home_to_navigation_events);
            }
        });

        ImageButton searchButton = root.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_home_to_navigation_search);
            }
        });

        // Setup cycling cards observers

        cyclingCardsTitle = root.findViewById(R.id.cycling_cards_title);
        homeViewModel.getCyclingCardsTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                cyclingCardsTitle.setText(s);
            }
        });

        cyclingCardsText = root.findViewById(R.id.cycling_cards_text);
        homeViewModel.getCyclingCardsText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                cyclingCardsText.setText(s);
            }
        });

        cyclingCardsImage = root.findViewById(R.id.cycling_cards_image);
        homeViewModel.getCyclingCardsImage().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                cyclingCardsImage.setImageBitmap(bitmap);
            }
        });

        cyclingCardsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiteEntry entry = homeViewModel.getCurrentEntry();
                if (entry == null) {
                    return;
                }

                NavController navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
                Bundle bundle = new Bundle();
                bundle.putParcelable("SiteEntryFromMap", entry);
                navController.navigate(R.id.action_navigation_home_to_navigation_info, bundle);
            }
        });

        // Setup cycling cards buttons

        ImageButton cycleLeftButton = root.findViewById(R.id.cycle_left_button);
        cycleLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.cycleLeft();
            }
        });

        ImageButton cycleRightButton = root.findViewById(R.id.cycle_right_button);
        cycleRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.cycleRight();
            }
        });

        return root;
    }

}