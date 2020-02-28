package com.example.backintyne.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.backintyne.R;
import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.io.IOException;

public class InfoFragment extends Fragment {

    private InfoViewModel infoViewModel;
    private int id;
    private SiteEntry siteEntry;
    //private List<ImageData> pics;
    private DataManager dataManager;

    //private Button gallery;
    private TextView name;
    private ImageView pic;
    private TextView address;
    private TextView introduction;
    private TextView details;
    private TextView facities;
    //private TextView public_transport;

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
        facities = root.findViewById(R.id.infoSiteFacilities);

        id = getArguments().getInt("mapButtonId");
    /*
        switch (id) {
            case R.id.mapSite0:
                setup(0);
                break;

            case R.id.mapSite1:
                setup(1);
                break;

            case R.id.mapSite2:
                setup(2);
                break;
        }

     */

        return root;
    }

    private void setup (int Id) {
        dataManager = DataManager.getDataManager();
        siteEntry = dataManager.getSiteData().get(Id);

        try {
            name.setText(siteEntry.getName());
            pic.setImageBitmap(dataManager.getImageBitMap(siteEntry.getGallery().get(0).getFileName()));
            address.setText(siteEntry.getAddress());
            introduction.setText(siteEntry.getIntroduction());
            details.setText(siteEntry.getDetails());
            facities.setText(siteEntry.getFacilities());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}