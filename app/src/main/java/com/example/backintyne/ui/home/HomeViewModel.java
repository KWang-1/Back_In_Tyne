package com.example.backintyne.ui.home;

import android.graphics.Bitmap;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.io.IOException;

public class HomeViewModel extends ViewModel {

    private final DataManager dataManager;

    private Handler cyclingHandler;
    private int entryIndex = 0;
    private static final int CYCLE_DELAY_MILLIS = 5000;

    private MutableLiveData<String> cyclingCardsTitle = new MutableLiveData<>();
    private MutableLiveData<String> cyclingCardsText = new MutableLiveData<>();
    private MutableLiveData<Bitmap> cyclingCardsImage = new MutableLiveData<>();

    public HomeViewModel() {
        dataManager = DataManager.getDataManager();
        cyclingHandler = new Handler();
        cycleCards.run();
    }

    private Runnable cycleCards = new Runnable() {
        @Override
        public void run() {
            try {
                SiteEntry entry = dataManager.getSiteData().get(entryIndex);
                cyclingCardsTitle.setValue(entry.getName());
                cyclingCardsText.setValue(entry.getIntroduction());
                cyclingCardsImage.setValue(DataManager.getImageBitMap(entry.getGallery().get(0).getFileName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                entryIndex = (entryIndex + 1) % dataManager.getSiteData().size();
                cyclingHandler.postDelayed(cycleCards, CYCLE_DELAY_MILLIS);
            }
        }
    };

    void cycleLeft() {
        cyclingHandler.removeCallbacks(cycleCards);
        int dataSize = dataManager.getSiteData().size();
        entryIndex = ((entryIndex - 2) + dataSize) % dataSize;
        cycleCards.run();
    }

    void cycleRight() {
        cyclingHandler.removeCallbacks(cycleCards);
        cycleCards.run();
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