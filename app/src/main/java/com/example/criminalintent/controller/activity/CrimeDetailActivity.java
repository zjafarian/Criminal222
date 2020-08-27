package com.example.criminalintent.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.criminalintent.R;
import com.example.criminalintent.controller.fragment.CrimeDetailFragment;

public class CrimeDetailActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new CrimeDetailFragment();
    }
}