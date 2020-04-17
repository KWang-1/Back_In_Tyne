package com.example.backintyne.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.backintyne.MainActivity;
import com.example.backintyne.R;
import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.ImageData;
import com.example.backintyne.data.SiteEntry;

import java.io.IOException;
import java.util.List;

public class InfoFragment extends Fragment {

    private InfoViewModel infoViewModel;
    private int id;
    private SiteEntry siteEntry;
    private List<ImageData> pics;
    private DataManager dataManager;

    private Button gallery;
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
        infoViewModel =
                ViewModelProviders.of(this).get(InfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_info, container, false);

        name = root.findViewById(R.id.infoSiteName);
        pic = root.findViewById(R.id.infoSitePic1);
        address = root.findViewById(R.id.infoSiteAddress);
        introduction = root.findViewById(R.id.infoSiteIntroduction);
        details = root.findViewById(R.id.infoSiteDetails);
        description = root.findViewById(R.id.infoSiteDescription);
        facilities = root.findViewById(R.id.infoSiteFacilities);
        public_transport = root.findViewById(R.id.infoSiteAddress); // TODO Replace

        assert getArguments() != null;
        siteEntry = getArguments().getParcelable("SiteEntryFromMap");
        assert siteEntry != null;
        setup(siteEntry);

        return root;
    }

    private void setup (SiteEntry siteEntry) {
        dataManager = DataManager.getDataManager();

        try {
            name.setText(siteEntry.getName());
            pic.setImageBitmap(DataManager.getImageBitMap(siteEntry.getGallery().get(0).getFileName()));
            address.setText(siteEntry.getAddress());
            introduction.setText(siteEntry.getIntroduction());
            details.setText(siteEntry.getDetails());
            description.setText(siteEntry.getDescription());
            facilities.setText(siteEntry.getFacilities());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}