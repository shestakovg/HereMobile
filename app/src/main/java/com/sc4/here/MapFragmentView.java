package com.sc4.here;

/**
 * Created by gshestakov on 11/2/2017.
 */

import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;

import android.app.Activity;
import android.app.Fragment;
import android.widget.Toast;

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
                    .findFragmentById(R.id.mapfragment);
        else
            m_mapFragment = (MapFragment) m_activity.getFragmentManager()
                .findFragmentById(R.id.mapfragment);

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
                    } else {
                        Toast.makeText(m_activity,
                                "ERROR: Cannot initialize Map with error " + error,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}
