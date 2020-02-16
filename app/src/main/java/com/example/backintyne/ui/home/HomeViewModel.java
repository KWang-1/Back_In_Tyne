package com.example.backintyne.ui.home;

import android.graphics.Bitmap;
import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.backintyne.MainActivity;
import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.io.IOException;

public class HomeViewModel extends ViewModel {
    /*
    private final DataManager dataManager;

    private Handler cyclingHandler;
    private int entryIndex = 0;
    private static final int CYCLE_DELAY_MILLIS = 5000;

    private MutableLiveData<String> cyclingCardsTitle;
    private MutableLiveData<String> cyclingCardsText;
    private MutableLiveData<Bitmap> cyclingCardsImage;

    public HomeViewModel() {
        dataManager = MainActivity.getDataManager();
    }

    private Runnable cycleCards = new Runnable() {
        @Override
        public void run() {
            try {
                SiteEntry entry = dataManager.getSiteData().get(entryIndex);
                cyclingCardsTitle.setValue(entry.getName());
                cyclingCardsText.setValue(entry.getIntroduction());
                cyclingCardsImage.setValue(dataManager.getImageBitMap(entry.getGallery().get(0).getFileName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                entryIndex = (entryIndex + 1) % dataManager.getSiteData().size();
                cyclingHandler.postDelayed(cycleCards, CYCLE_DELAY_MILLIS);
            }
        }
    };
    */
}