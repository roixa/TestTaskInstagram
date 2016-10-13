package com.roix.testtaskinstagram.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roix.testtaskinstagram.ContentView;
import com.roix.testtaskinstagram.parralax.ParralaxView;
import com.roix.testtaskinstagram.pojo.Photo;

import java.util.List;

public class ParralaxFragment extends Fragment implements ContentView {
    private ParralaxView view;

    public ParralaxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=new ParralaxView(getActivity());
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        view.deactivate();
    }

    @Override
    public void updatePhotos(List<Photo> photos) {
        view.activate(photos);
    }
}
