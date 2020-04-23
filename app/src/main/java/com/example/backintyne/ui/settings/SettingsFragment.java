package com.example.backintyne.ui.settings;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.backintyne.R;
import com.google.android.material.textfield.TextInputLayout;

public class SettingsFragment extends Fragment {

    private SettingsViewModel notificationsViewModel;


    private TextInputLayout emailText;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        emailText  = (TextInputLayout) root.findViewById(R.id.emailContent);

        //set on click listener for contact us button
        Button contactUs = root.findViewById(R.id.emailSend);
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        return root;
    }




    public void sendEmail(){

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");

        //where to send the email
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"b8024568@newcastle.ac.uk"});

        //set the subject of the email
        i.putExtra(Intent.EXTRA_SUBJECT, "backintyneApp");

        //get the text to send in the email
        String content = emailText.toString();

        i.putExtra(Intent.EXTRA_TEXT   , content);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (ActivityNotFoundException ex) {

        }
    }



}

