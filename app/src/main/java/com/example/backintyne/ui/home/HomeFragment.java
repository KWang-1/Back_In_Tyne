package com.example.backintyne.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.backintyne.MainActivity;
import com.example.backintyne.R;
import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private Handler cyclingHandler;

    private DataManager dataManager;
    private int entryIndex = 0;

    private TextView cyclingCardsTitle;
    private TextView cyclingCardsText;
    private ImageView cyclingCardsImage;

    private static final int CYCLE_DELAY_MILLIS = 5000;

    private Runnable cycleCards = new Runnable() {
        @Override
        public void run() {
            try {
                SiteEntry entry = dataManager.getSiteData().get(entryIndex);
                cyclingCardsTitle.setText(entry.getName());
                cyclingCardsText.setText(entry.getIntroduction());
                cyclingCardsImage.setImageBitmap(dataManager.getImageBitMap(entry.getGallery().get(0).getFileName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                entryIndex = (entryIndex + 1) % dataManager.getSiteData().size();
                cyclingHandler.postDelayed(cycleCards, CYCLE_DELAY_MILLIS);
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Setup image buttons

        ImageButton mapButton = root.findViewById(R.id.map_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_home_to_navigation_map);
            }
        });

        ImageButton eventsButton = root.findViewById(R.id.events_button);
        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_home_to_navigation_events);
            }
        });

        ImageButton searchButton = root.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_home_to_navigation_search);
            }
        });

        // Setup cycling cards

        cyclingCardsTitle = root.findViewById(R.id.cycling_cards_title);
        cyclingCardsText = root.findViewById(R.id.cycling_cards_text);
        cyclingCardsImage = root.findViewById(R.id.cycling_cards_image);

        dataManager = MainActivity.getDataManager();
        cyclingHandler = new Handler();
        cyclingHandler.postDelayed(cycleCards, CYCLE_DELAY_MILLIS);

        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        cyclingHandler.removeCallbacks(cycleCards);
    }

    @Override
    public void onResume() {
        super.onResume();
        cycleCards.run();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cyclingHandler.removeCallbacks(cycleCards);
    }

}