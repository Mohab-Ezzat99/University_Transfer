package mrandroid.app.util;

import android.Manifest;
import android.app.Activity;

import pub.devrel.easypermissions.EasyPermissions;

public class LocationPermission {

    public static boolean requestLocationPermission(Activity activity) {
        if (isLocationGranted(activity)) return true;
        else {
            EasyPermissions.requestPermissions(
                    activity,
                    "You need to accept location permissions to use this app.",
                    199,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            );
            return false;
        }
    }

    private static boolean isLocationGranted(Activity activity) {
        return EasyPermissions.hasPermissions(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        );
    }

}
