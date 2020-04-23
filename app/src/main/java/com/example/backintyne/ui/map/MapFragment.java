package com.example.backintyne.ui.map;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import com.example.backintyne.R;

// import google maps required parts
import android.location.LocationListener;

import com.example.backintyne.data.SiteEntry;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

// implements OnMapReadyCallback to allow things to be done once map has been created
// implements LocationListener to get accurate GPS for direction

/**
 * Map page fragment.
 * Displays an interactive map with data for each site.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    private MapViewModel mapViewModel;

    private Button directionButton;

    private final static int FINE_LOCATION_PERMISSION_REQUEST = 0;
    private final static int ACCESS_COARSE_LOCATION_REQUEST = 0;

    private Marker focusedMarker; // used to track most recent pointer to use it in direction or info button
    private Polyline routeToDestination; // line used to draw between user and location they want  to get to
    private LatLng currentLocation = new LatLng(55.4, -1.65);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        // gets map fragment to use on app
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        // Setup rest of UI
        directionButton = root.findViewById(R.id.Directions);

        // Disables buttons as they have no focus pointer so won't work yet
        directionButton.setAlpha(.5f);
        directionButton.setClickable(false);
        directionButton.setEnabled(false);

        return root;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        // Uses custom info window
        googleMap.setInfoWindowAdapter(new InfoWindowCustom(getContext()));

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                SiteEntry entry = mapViewModel.findEntryByName(marker.getTitle());
                if (entry != null) {
                    focusedMarker = null;
                    NavController navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("SiteEntryFromMap", entry);
                    navController.navigate(R.id.action_navigation_map_to_navigation_info, bundle);
                }
            }
        });

        // Add scenery markers
        LatLng home = new LatLng(55.4, -1.65);
        BitmapDescriptor shipIcon = BitmapDescriptorFactory.fromAsset("map_icons/ship.png");
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(55.366625, -1.413960))
                .icon(shipIcon));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(55.966625, -1.213960))
                .icon(shipIcon));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(55.766625, -1.243960))
                .icon(shipIcon));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(55.166625, -1.12960))
                .icon(shipIcon));

        // Set camera defaults
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(home));
        googleMap.setMinZoomPreference(8.5f);
        googleMap.setMaxZoomPreference(14.0f);

        // Add each entry as marker
        for (SiteEntry entry : mapViewModel.getEntries()) {
            String imgPath = "map_icons/" + entry.getType() + ".png";
            BitmapDescriptor icon = BitmapDescriptorFactory.fromAsset(imgPath);
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(entry.getLatitude(), entry.getLongitude()))
                    .title(entry.getName())
                    .snippet(entry.getIntroduction())
                    .icon(icon));
        }

        // Event whenever marker is clicked
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // sets marker a focus and allows button usage
                if (focusedMarker == null) {
                    directionButton.setAlpha(1);
                    directionButton.setClickable(true);
                    directionButton.setEnabled(true);
                }
                focusedMarker = marker;
                return false;
            }
        });

        // Direction button event
        directionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // removes old line
                if (routeToDestination != null) {
                    routeToDestination.remove();
                }
                // creates new line between user and focus pointer
                routeToDestination = googleMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(currentLocation, focusedMarker.getPosition()));
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
    }

    private boolean getPermission() {
        Context context = getContext();
        assert context != null;
        Activity activity = getActivity();
        assert activity != null;

        // checks for permission and requests if not got
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("MapsActivity", "getting permissions");
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_PERMISSION_REQUEST);
        } else {
            Log.d("MapsActivity", "got permissions");
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d("MapsActivity", "getting permissions");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, ACCESS_COARSE_LOCATION_REQUEST);
            } else {
                Log.d("MapsActivity", "got permissions");
                // creates location manager to get gps
                // used in tracking gps location
                LocationManager locationManager = (LocationManager) Objects.requireNonNull(getContext()).getSystemService(Context.LOCATION_SERVICE);
                assert locationManager != null;
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
                return true;
            }
            return false;
        }
        return false;
    }

    // require from implementation but not required
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }



}

