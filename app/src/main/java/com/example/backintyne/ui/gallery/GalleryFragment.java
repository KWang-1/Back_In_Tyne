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
import androidx.lifecycle.ViewModelProviders;

import com.example.backintyne.R;
import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.io.IOException;

/**
 * Gallery page fragment.
 * Displays each image and its attribution for a given site entry.
 */
public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private SiteEntry siteEntry;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);

        LinearLayout galleryPics = root.findViewById(R.id.gallery_pics);
        int picNum;

        SiteEntry entry;

        // Get the site entry data from the info page
        assert getArguments() != null;
        siteEntry = getArguments().getParcelable("SiteEntryFromInfo");
        assert siteEntry != null;

        entry = siteEntry;

        // Set parameters for the pictures to be displayed
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int pixelsConversion = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) 8, getResources().getDisplayMetrics());
        layoutParams.setMargins(0, 0, 0, pixelsConversion);


        picNum = entry.getGallery().size(); // Get the number of pictures needed to be shown in the gallery
        for (int i = 0; i< picNum; i++){

            // Create new image xml and set as each picture
            View result = LayoutInflater.from(getContext()).inflate(R.layout.gallery_image, null);

            ImageView resultsImage = result.findViewById(R.id.result_Image);
            try {
                // Set the image
                resultsImage.setImageBitmap(DataManager.getImageBitMap(entry.getGallery().get(i).getFileName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            TextView eventDate = result.findViewById(R.id.image_attribution);
            eventDate.setText(entry.getGallery().get(i).getAttribution());

            // Add the picture to the gallery
            galleryPics.addView(result, layoutParams);
        }


        return root;
    }





}

