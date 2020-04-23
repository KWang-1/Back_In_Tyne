package com.example.backintyne.ui.settings;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * View model for settings class
 * Handles backend logic including email messaging.
 */
public class SettingsViewModel extends ViewModel {

    public SettingsViewModel() {}

    void sendEmail(Activity activity, String content) {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");

        // Where to send the email
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"b8024568@newcastle.ac.uk"});

        // Set the subject of the email
        i.putExtra(Intent.EXTRA_SUBJECT, "backintyneApp");

        i.putExtra(Intent.EXTRA_TEXT   , content);
        try {
            activity.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (ActivityNotFoundException ignored) {

        }
    }
}