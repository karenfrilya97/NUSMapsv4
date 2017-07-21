package com.example.bryan.nusmapsv2;

import java.util.List;

/**
 * Created by Bryan on 24/6/2017.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
