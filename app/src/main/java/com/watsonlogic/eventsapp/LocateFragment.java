package com.watsonlogic.eventsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class LocateFragment extends Fragment {
    private static final String TAG = "LocateFragment";
    private MapView mv;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_locate, container, false);
        mv = (MapView) v.findViewById(R.id.map_view);
        mv.onCreate(savedInstanceState);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mv.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mv.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mv.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mv.onLowMemory();
    }
}
