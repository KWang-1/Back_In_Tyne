package com.example.backintyne.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.backintyne.R;
import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.io.IOException;
import java.util.Objects;

public class InfoFragment extends Fragment {

    private InfoViewModel infoViewModel; // For possible future extension
    private SiteEntry siteEntry;

    private TextView name;
    private ImageButton pic;
    private TextView address;
    private TextView introduction;
    private TextView details;
    private TextView description;
    private TextView facilities;
    private TextView public_transport;

    // UI

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        infoViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_info, container, false);

        name = root.findViewById(R.id.infoSiteName);
        pic = root.findViewById(R.id.infoSitePic1);
        address = root.findViewById(R.id.infoSiteAddress);
        introduction = root.findViewById(R.id.infoSiteIntroduction);
        details = root.findViewById(R.id.infoSiteDetails);
        description = root.findViewById(R.id.infoSiteDescription);
        facilities = root.findViewById(R.id.infoSiteFacilities);
        public_transport = root.findViewById(R.id.infoSitePublicTransport);

        assert getArguments() != null;
        siteEntry = getArguments().getParcelable("SiteEntryFromMap");
        assert siteEntry != null;
        setup(siteEntry);

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
                Bundle bundle = new Bundle();
                SiteEntry entry = siteEntry;
                bundle.putParcelable("SiteEntryFromInfo", entry);
                navController.navigate(R.id.action_navigation_info_to_navigation_gallery, bundle);
            }
        });

        return root;
    }

    private void setup (SiteEntry siteEntry) {
        try {
            name.setText(siteEntry.getName());
            pic.setImageBitmap(DataManager.getImageBitMap(siteEntry.getGallery().get(0).getFileName()));
            address.setText(siteEntry.getAddress());
            introduction.setText(siteEntry.getIntroduction());
            details.setText(siteEntry.getDetails());
            description.setText(siteEntry.getDescription());
            facilities.setText(siteEntry.getFacilities());
            public_transport.setText(siteEntry.getPublicTransport());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}