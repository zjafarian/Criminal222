package com.example.criminalintent.controller.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.criminalintent.controller.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {

    public static final String EXTRA_CURRENT_INDEX = "com.example.criminalintent.CurrentIndex";

    public static Intent newIntent(Context context, int index) {
        Intent intent = new Intent(context, CrimeListActivity.class);
        intent.putExtra(EXTRA_CURRENT_INDEX,index);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        int index = getIntent().getIntExtra(EXTRA_CURRENT_INDEX,0);
        CrimeListFragment crimeListFragment = CrimeListFragment.newInstance(index);
        return crimeListFragment;
    }
}