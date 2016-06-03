package com.watsonlogic.eventsapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class EditFragment extends Fragment implements View.OnClickListener {

    private EditText username;
    private Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidgets();
    }

    private void getWidgets() {
        username = (EditText) getActivity().findViewById(R.id.username_edit_text);
        submitButton = (Button) getActivity().findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_button: {
                if (validateInput() && isAvailableNetwork()) {
                    Snackbar.make(v, "Profile saved!", Snackbar.LENGTH_LONG)
                            .setActionTextColor(ContextCompat.getColor(getActivity(), R.color.ColorBrightAccent))
                                    .setAction("UNDO", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Log.e("TAG", "Done");
                                        }
                                    }).show();
                } else if (!isAvailableNetwork()) {
                    Snackbar.make(v, "Profile not saved! Try again later.", Snackbar.LENGTH_LONG)
                            .setActionTextColor(ContextCompat.getColor(getActivity(), R.color.ColorBrightAccent))
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Log.e("TAG", "Done");
                                }
                            }).show();
                }
                break;
            }
        }
    }

    private void retryAction() {

    }

    private void undoAction() {

    }

    private boolean validateInput() {
        return true;
    }

    private boolean isAvailableNetwork() {
        return true;
    }
}
