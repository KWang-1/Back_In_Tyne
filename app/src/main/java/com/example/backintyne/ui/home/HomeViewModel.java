package com.example.backintyne.ui.home;

import android.graphics.Bitmap;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.io.IOException;

/**
 * View model for home page.
 * Handles backend logic and data retrieval for the cycling cards feature.
 */
public class HomeViewModel extends ViewModel {

    private final DataManager dataManager;

    // Variables for controlling cycling action
    private Handler cyclingHandler;
    private int entryIndex = 0;
    private static final int CYCLE_DELAY_MILLIS = 5000;

    // Mutable data for observation by view
    private MutableLiveData<String> cyclingCardsTitle = new MutableLiveData<>();
    private MutableLiveData<String> cyclingCardsText = new MutableLiveData<>();
    private MutableLiveData<Bitmap> cyclingCardsImage = new MutableLiveData<>();

    public HomeViewModel() {
        dataManager = DataManager.getDataManager();
        cyclingHandler = new Handler();
        cycleCards.run();
    }

    // Cycling action thread
    private Runnable cycleCards = new Runnable() {
        @Override
        public void run() {
            // Attempt to set data to new entry index
            try {
                SiteEntry entry = dataManager.getSiteData().get(entryIndex);
                cyclingCardsTitle.setValue(entry.getName());
                cyclingCardsText.setValue(entry.getIntroduction());
                cyclingCardsImage.setValue(DataManager.getImageBitMap(entry.getGallery().get(0).getFileName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                // Increment index regardless of success or failure
                entryIndex = (entryIndex + 1) % dataManager.getSiteData().size();
                cyclingHandler.postDelayed(cycleCards, CYCLE_DELAY_MILLIS);
            }
        }
    };

    // Change entry index to previous index
    void cycleLeft() {
        cyclingHandler.removeCallbacks(cycleCards);
        int dataSize = dataManager.getSiteData().size();
        entryIndex = ((entryIndex - 2) + dataSize) % dataSize;
        cycleCards.run();
    }

    // Change entry index to next index
    void cycleRight() {
        cyclingHandler.removeCallbacks(cycleCards);
        cycleCards.run();
    }

    // For navigation using cycling cards
    SiteEntry getCurrentEntry() {
        return dataManager.getSiteData().get(entryIndex - 1);
    }

    MutableLiveData<String> getCyclingCardsTitle() {
        return cyclingCardsTitle;
    }

    MutableLiveData<String> getCyclingCardsText() {
        return cyclingCardsText;
    }

    MutableLiveData<Bitmap> getCyclingCardsImage() {
        return cyclingCardsImage;
    }
}