package com.example.backintyne.ui.gallery;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.backintyne.R;
import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.io.IOException;

public class GalleryFragment extends Fragment {
    private DataManager dataManager;
    private int id;
    private SiteEntry siteEntry;
    private LinearLayout gpics;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        //final TextView textView = root.findViewById(R.id.text_settings);
        gpics = root.findViewById(R.id.gallery_pics);
        int numofpics;

        SiteEntry entry;

        //get the site entry data from the info page
        assert getArguments() != null;
        siteEntry = getArguments().getParcelable("SiteEntryFromInfo");
        assert siteEntry != null;

        entry = siteEntry;

        //set peramiters for the pictures to be displayed
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int pixelsConversion = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) 8, getResources().getDisplayMetrics());
        layoutParams.setMargins(0, 0, 0, pixelsConversion);


        numofpics = entry.getGallery().size(); //get the number of pictures needed to be shown in the gallery
        for (int i =0; i< numofpics;i++){

            //create new image xml and set as each picture
            View result = LayoutInflater.from(getContext()).inflate(R.layout.gallery_image, null);

            ImageView resultsImage = result.findViewById(R.id.result_Image);
            try {
                //set the image
                resultsImage.setImageBitmap(DataManager.getImageBitMap(entry.getGallery().get(i).getFileName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            TextView eventDate = result.findViewById(R.id.image_attribution);
            eventDate.setText(entry.getGallery().get(i).getAttribution());

            //add the picture to the gallery
            gpics.addView(result, layoutParams);
        }


        return root;
    }





}

