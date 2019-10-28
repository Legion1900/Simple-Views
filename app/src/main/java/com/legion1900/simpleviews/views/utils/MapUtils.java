package com.legion1900.simpleviews.views.utils;

public class MapUtils {
    // Earth radius in m.
    private static final float R = 6378137;

    private MapUtils() {}

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
}
