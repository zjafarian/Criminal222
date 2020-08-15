package com.example.criminalintent.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.criminalintent.R;

public class CrimeDetailFragment extends Fragment {

    public CrimeDetailFragment() {
        // Required empty public constructor
    }

    /**
     * 1. Inflate the layout (or create layout in code)
     * 2. find all views
     * 3. logic for all views (like setListeners)
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime_detail, container, false);
        return view;
    }
}