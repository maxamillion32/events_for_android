package com.watsonlogic.eventsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.text.TextUtilsCompat;
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

    private void getWidgets(){
        username = (EditText)getActivity().findViewById(R.id.username_edit_text);
        submitButton = (Button)getActivity().findViewById(R.id.submit_button);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.submit_button:{
                validateInput();
                break;
            }
        }
    }

    private void validateInput(){

    }
}
