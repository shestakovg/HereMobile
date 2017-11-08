package com.sc4.here;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private Fragment currentFragmnet;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.calendar:
                    setFragment(CalendarFragment.GetInstance());
                    return true;
                case R.id.notifications:

                    setFragment(NotificationFragment.GetInstance());
                    //Toast.makeText(getApplicationContext(), "DASH BOARD",  Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.profile:
                    setFragment(ProfileFragment.GetInstance());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        requestPermissions();
//        if (this.currentFragmnet!=null)
//            setFragment(this.currentFragmnet);
//        else
            setFragment(ProfileFragment.GetInstance());
        Activity act = this;

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.currentFragmnet!= null)
            setFragment(currentFragmnet);
    }

    private void setFragment(Fragment fragment)
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
        this.currentFragmnet = fragment;
    }

    private void requestPermissions() {

        final List<String> requiredSDKPermissions = new ArrayList<String>();
        requiredSDKPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        requiredSDKPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        requiredSDKPermissions.add(Manifest.permission.INTERNET);
        requiredSDKPermissions.add(Manifest.permission.ACCESS_WIFI_STATE);
        requiredSDKPermissions.add(Manifest.permission.ACCESS_NETWORK_STATE);

        ActivityCompat.requestPermissions(this,
                requiredSDKPermissions.toArray(new String[requiredSDKPermissions.size()]),
                REQUEST_CODE_ASK_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                for (int index = 0; index < permissions.length; index++) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {

                        /**
                         * If the user turned down the permission request in the past and chose the
                         * Don't ask again option in the permission request system dialog.
                         */
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                                permissions[index])) {
                            Toast.makeText(this,
                                    "Required permission " + permissions[index] + " not granted. "
                                            + "Please go to settings and turn on for sample app",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this,
                                    "Required permission " + permissions[index] + " not granted",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }

                /**
                 * All permission requests are being handled.Create map fragment view.Please note
                 * the HERE SDK requires all permissions defined above to operate properly.
                 */
                // m_mapFragmentView = new MapFragmentView(getActivity(), this);
                break;
            }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
