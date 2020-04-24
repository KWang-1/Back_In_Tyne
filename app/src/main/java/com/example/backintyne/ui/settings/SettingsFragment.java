package com.example.backintyne.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.backintyne.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Settings page fragment.
 * Interface for app settings and contact information.
 */
public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;

    private TextInputLayout emailText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        emailText = root.findViewById(R.id.emailContent);

        //set on click listener for contact us button
        Button contactUs = root.findViewById(R.id.send_email_button);
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsViewModel.sendEmail(Objects.requireNonNull(getActivity()), emailText.getEditText().getText().toString());
            }
        });

        return root;
    }





}

