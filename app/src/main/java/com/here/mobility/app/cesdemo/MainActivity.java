package com.here.mobility.app.cesdemo;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.util.ArrayList;
import java.util.List;

import Common.SvgImage;
import FireBase.FireBaseProvider;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;


public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private Fragment currentFragmnet;
    private BottomNavigationViewEx navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case com.here.mobility.app.cesdemo.R.id.calendar:
                    setFragment(CalendarFragment.GetInstance());
                    return true;
                case com.here.mobility.app.cesdemo.R.id.notifications:

                    setFragment(NotificationFragment.GetInstance());
                    //Toast.makeText(getApplicationContext(), "DASH BOARD",  Toast.LENGTH_SHORT).show();
                    return true;
                case com.here.mobility.app.cesdemo.R.id.profile:
                    setFragment(ProfileFragment.GetInstance());
                    return true;
            }
            return false;
        }
    };

    public MainActivity() {
        FireBaseProvider.getInstance(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(com.here.mobility.app.cesdemo.R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(com.here.mobility.app.cesdemo.R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
         navigation =  findViewById(com.here.mobility.app.cesdemo.R.id.bnve_no_text);
         navigation.setTextVisibility(false);
         navigation.enableAnimation(false);
         navigation.enableShiftingMode(false);
         navigation.enableItemShiftingMode(false);

         navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        addBadgeAt(2,1);
        // navigation.setVisibility(View.INVISIBLE);
/*********************************************************************************************************************************/
//        LinearLayout mapContainerLayout = (LinearLayout) findViewById(R.id.tableContainer);
//        LinearLayout navbar = (LinearLayout) findViewById(com.here.mobility.app.cesdemo.R.id.navbar);
//        DisplayMetrics metrics = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int width = metrics.widthPixels;
//        int height = metrics.heightPixels;
//        int availableHeight = height;
//       //DisplayMetrics metricsBar = new DisplayMetrics();
//        //navbar.getDisplay().getMetrics(metricsBar);
//
//        ViewGroup.LayoutParams paramsContainer = mapContainerLayout.getLayoutParams();
//        paramsContainer.width=width;
//        paramsContainer.height= height - 210;// navbar.getHeight();
//        availableHeight -= paramsContainer.height;
//        mapContainerLayout.setLayoutParams(paramsContainer);
/*********************************************************************************************************************************/

        requestPermissions();

        setFragment(ProfileFragment.GetInstance());
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        String token = FirebaseInstanceId.getInstance().getToken();

        ImageView imageView = (ImageView) findViewById(com.here.mobility.app.cesdemo.R.id.logo_image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
////        SVG svg = SVGParser.getSVGFromResource(getResources(), R.drawable.header_logo);
//////The following is needed because of image accelaration in some devices such as samsung
////        imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
////        imageView.setImageDrawable(svg.createPictureDrawable());
////        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//        SvgImage rainSVG = new SvgImage(MainActivity.this, R.id.logo_image, R.drawable.demo_logo);

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
        fragmentTransaction.replace(com.here.mobility.app.cesdemo.R.id.fragmentContainer, fragment);
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

    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(40, 3, true)
                .bindTarget(navigation.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
                            Toast.makeText(MainActivity.this,"tips_badge_removed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
