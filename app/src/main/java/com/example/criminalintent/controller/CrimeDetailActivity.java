package com.example.criminalintent.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.criminalintent.R;

public class CrimeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflate layout for activity
        setContentView(R.layout.activity_crime_detail);

        //add CrimeDetailFragment to this activity (dynamic)

        //get fragment manager
        final FragmentManager fragmentManager = getSupportFragmentManager();

        //create an add fragment transaction for CrimeDetailFragment

        final CrimeDetailFragment crimeDetailFragment = new CrimeDetailFragment();

        Button button = findViewById(R.id.add_crime);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, crimeDetailFragment)
                        .commit();
            }
        });

        Button button1 = findViewById(R.id.remove_crime);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(crimeDetailFragment)
                        .commit();
            }
        });
    }
}