package com.example.bryan.nusmapsv2;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Bryan on 24/6/2017.
 */

public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;

}
