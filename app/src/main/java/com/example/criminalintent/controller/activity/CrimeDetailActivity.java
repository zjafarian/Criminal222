package com.example.criminalintent.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.criminalintent.controller.fragment.CrimeDetailFragment;

import java.util.UUID;

public class CrimeDetailActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID = "com.example.criminalintent.crimeId";



    /**
     * intent to start CrimeDetailActivity with extras. we need crime id to show
     * the coresponding crime detail in this activity.
     *
     * @param context src object of context for intent
     * @param crimeId extra needed for this activity to work properly. id of the crime
     * @return the new intent for starting this activity properly
     */
    public static Intent newIntent(Context context, UUID crimeId, int index) {
        Intent intent = new Intent(context, CrimeDetailActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        /*CrimeDetailFragment crimeDetailFragment = new CrimeDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_CRIME_ID, crimeId);
        crimeDetailFragment.setArguments(args);*/

        CrimeDetailFragment crimeDetailFragment = CrimeDetailFragment.newInstance(crimeId);
        return crimeDetailFragment;
    }
}