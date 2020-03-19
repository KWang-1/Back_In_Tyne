package com.example.backintyne.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.backintyne.R;

public class SearchFragment extends Fragment {

    private SearchViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        final TextView textView = root.findViewById(R.id.text_search);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        Button infoButton = root.findViewById(R.id.info_button);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_navigation_search_to_navigation_info);
            }
        });

        return root;
    }
}