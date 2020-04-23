package com.example.backintyne.ui.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.backintyne.R;
import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;

/**
 * Custom info window popup for the map page.
 * Displays popup information for a given site entry.
 * Links to the info page.
 */
public class InfoWindowCustom implements GoogleMap.InfoWindowAdapter {

    // used to set custom info window
    private Context context;
    private DataManager data;

    InfoWindowCustom(Context context) {
        this.context = context;
        this.data = DataManager.getDataManager();
    }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.echo_info_window, null);

        // Find SiteEntry object
        SiteEntry entry = data.findEntryByName(marker.getTitle());
        if (entry != null) {

            // Find views to initialize
            TextView title = view.findViewById(R.id.title);
            TextView snippet = view.findViewById(R.id.snippet);
            ImageView image = view.findViewById(R.id.image);

            // Initialize views
            title.setText(entry.getName());
            snippet.setText(entry.getIntroduction());
            try {
                Bitmap bitmap = DataManager.getImageBitMap(entry.getGallery().get(0).getFileName());
                int pixelsConversion = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        (float) 12, context.getResources().getDisplayMetrics());
                Bitmap roundedBitmap = getRoundedCornerBitmap(bitmap, pixelsConversion);
                image.setImageBitmap(roundedBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return view;
    }

    // From https://stackoverflow.com/questions/2459916/how-to-make-an-imageview-with-rounded-corners
    private static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, (float) pixels, (float) pixels, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

}
