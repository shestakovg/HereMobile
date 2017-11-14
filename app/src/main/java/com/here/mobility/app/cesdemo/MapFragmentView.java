package com.here.mobility.app.cesdemo;

/**
 * Created by gshestakov on 11/2/2017.
 */

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.mapping.MapMarker;

import android.app.Activity;
import android.app.Fragment;
import android.widget.Toast;

import java.io.IOException;

import Location.LocationObject;
import Location.LocationSettings;

/**
 * This class encapsulates the properties and functionality of the Map view.
 */
public class MapFragmentView {
    private MapFragment m_mapFragment;
    private Activity m_activity;
    private Map m_map;
    private Fragment parentFragment;

    public MapFragmentView(Activity activity, Fragment parentFragment) {
        m_activity = activity;
        this.parentFragment = parentFragment;
        initMapFragment();
    }

    private void initMapFragment() {
        /* Locate the mapFragment UI element */
        if (parentFragment!= null)
            m_mapFragment = (MapFragment) parentFragment.getChildFragmentManager()
                    .findFragmentById(com.here.mobility.app.cesdemo.R.id.mapfragment);
        else
            m_mapFragment = (MapFragment) m_activity.getFragmentManager()
                .findFragmentById(com.here.mobility.app.cesdemo.R.id.mapfragment);

        if (m_mapFragment != null) {
            /* Initialize the MapFragment, results will be given via the called back. */
            m_mapFragment.init(new OnEngineInitListener() {
                @Override
                public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {

                    if (error == Error.NONE) {
                        /*
                         * If no error returned from map fragment initialization, the map will be
                         * rendered on screen at this moment.Further actions on map can be provided
                         * by calling Map APIs.
                         */
                        m_map = m_mapFragment.getMap();
                        LocationObject lObj = LocationSettings.getCenterPoint();
                        m_map.setCenter(new GeoCoordinate(lObj.getLat(), lObj.getLng(), 0.0),
                                Map.Animation.NONE);
                        double mz = m_map.getMinZoomLevel();
                        double maxz = m_map.getMaxZoomLevel();
                        m_map.setZoomLevel(14.0);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        m_map.setZoomLevel(4);
                        for (LocationObject obj : LocationSettings.getDefaultMarkers())
                        {
                            createMapMarker(obj);
                        }
//                        createMapMarker(LocationSettings.getTestMarker());
                    } else {
                        Toast.makeText(m_activity,
                                "ERROR: Cannot initialize Map with error " + error,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    private void createMapMarker(LocationObject locationObject) {
        // create an image from cafe.png.
        Image marker_img = new Image();
        try {
            marker_img.setImageResource(R.drawable.mapmarker3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // create a MapMarker centered at current location with png image.
        MapMarker m_map_marker = new MapMarker(new GeoCoordinate(locationObject.getLat(), locationObject.getLng()), marker_img);
        // add a MapMarker to current active map.
        m_map.addMapObject(m_map_marker);
    }
}
