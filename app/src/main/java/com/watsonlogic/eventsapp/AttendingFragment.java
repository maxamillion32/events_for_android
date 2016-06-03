package com.watsonlogic.eventsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class AttendingFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attending, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.event_banner);
        Glide.with(this)
                .load("http://m.c.lnkd.licdn.com/mpr/mpr/AAEAAQAAAAAAAARQAAAAJDc2OWViMmI3LWJiOGEtNDAwMi1hNTFjLTY1M2UyMDg1Y2ZhNw.jpg")
                .centerCrop()
                .into(imageView);
    }

}
