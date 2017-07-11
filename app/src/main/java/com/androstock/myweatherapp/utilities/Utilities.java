package com.androstock.myweatherapp.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by DMI on 11-07-2017.
 */

public class Utilities {
    private static final int REQUEST = 102;

    public static boolean getLocationPermission(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST);
        } else {
            return true;
        }
        return false;
    }
}
