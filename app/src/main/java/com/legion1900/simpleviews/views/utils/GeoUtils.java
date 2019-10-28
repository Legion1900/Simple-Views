package com.legion1900.simpleviews.views.utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

public class GeoUtils {
    // Earth radius in m.
    private static final float R = 6378137;

    private GeoUtils() {}

    public static double calculateXInMeters(double lat, double lng) {
        lat = Math.toRadians(lat);
        lng = Math.toRadians(lng);
        return R * Math.cos(lat) * Math.cos(lng);
    }

    public static double calculateYInMeters(double lat, double lng) {
        lat = Math.toRadians(lat);
        lng = Math.toRadians(lng);
        return R * Math.cos(lat) * Math.sin(lng);
    }

    public static double calculateLat(double y, double lng) {
        lng = Math.toRadians(lng);
        double value = y / (R * Math.sin(lng));
        double latInRad = Math.acos(value);
        return Math.toDegrees(latInRad);
    }

    public static double calculateLng(double x, double y) {
        double lngInRad = Math.atan2(y, x);
        return Math.toDegrees(lngInRad);
    }

    /*
    * Returns random coordinates expressed in latitude-longitude calculated by offsetting center
    * by random radius [minRad, maxRad] and rotated by random amount of degrees.
    * minRad and maxRad expressed in meters.
    * */
    public static LatLng rndDotInRadius(LatLng center, int minRad, int maxRad) {
        // Generating random radius in bounds.
        double radius = RandomUtils.randomInt(minRad, maxRad);
        // Generating random rotation angle.
        double angle = RandomUtils.randomInt(360);
        return SphericalUtil.computeOffset(center, radius, angle);
    }
}
