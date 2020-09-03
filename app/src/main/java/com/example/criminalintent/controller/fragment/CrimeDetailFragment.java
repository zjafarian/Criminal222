package com.example.criminalintent.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.criminalintent.R;
import com.example.criminalintent.controller.activity.CrimeDetailActivity;
import com.example.criminalintent.model.Crime;
import com.example.criminalintent.repository.CrimeRepository;
import com.example.criminalintent.repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeDetailFragment extends Fragment {

    public static final String TAG = "CDF";
    public static final String ARGS_CRIME_ID = "crimeId";

    private EditText mEditTextTitle;
    private Button mButtonDate;
    private CheckBox mCheckBoxSolved;
    private Crime mCrime;
    private IRepository mRepository;
    private ImageButton mImgBtnFirst;
    private ImageButton mImgBtnPrevious;
    private ImageButton mImgBtnNext;
    private ImageButton mImgBtnLast;
    private int mCurrentIndex;
    private List<Crime> mCrimeList;

    public static CrimeDetailFragment newInstance(UUID crimeId) {

        Bundle args = new Bundle();
        args.putSerializable(ARGS_CRIME_ID, crimeId);

        CrimeDetailFragment fragment = new CrimeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CrimeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
        mCrimeList = new ArrayList<>();
        mRepository = CrimeRepository.getInstance();
        mCrimeList = mRepository.getCrimes();

        //this is storage of this fragment
        UUID crimeId = (UUID) getArguments().getSerializable(ARGS_CRIME_ID);
        mCrime = mRepository.getCrime(crimeId);
        for (int i = 0; i < mRepository.getCrimes().size(); i++) {
            if (mCrimeList.get(i).getId().equals(crimeId))
                mCurrentIndex = i;
        }
    }

    /**
     * 1. Inflate the layout (or create layout in code)
     * 2. find all views
     * 3. logic for all views (like setListeners)
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime_detail, container, false);

        findViews(view);
        initViews();
        setListeners();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        updateCrime();

        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.d(TAG, "onDetach");
    }

    private void findViews(View view) {
        mEditTextTitle = view.findViewById(R.id.crime_title);
        mButtonDate = view.findViewById(R.id.crime_date);
        mCheckBoxSolved = view.findViewById(R.id.crime_solved);
        mImgBtnPrevious = view.findViewById(R.id.img_btn_previous);
        mImgBtnFirst = view.findViewById(R.id.img_btn_first);
        mImgBtnNext = view.findViewById(R.id.img_btn_next);
        mImgBtnLast = view.findViewById(R.id.img_btn_last);
    }

    private void initViews() {
        mEditTextTitle.setText(mCrime.getTitle());
        mCheckBoxSolved.setChecked(mCrime.isSolved());
        mButtonDate.setText(mCrime.getDate().toString());
        mButtonDate.setEnabled(false);
    }

    private void setListeners() {
        mEditTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: " + s + ", " + start + ", " + before + ", " + count);

                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCheckBoxSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mImgBtnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = 0;
                updateData(mCurrentIndex);
            }
        });

        mImgBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1 + mCrimeList.size()) % mCrimeList.size();
                updateData(mCurrentIndex);
            }
        });

        mImgBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mCrimeList.size();
                updateData(mCurrentIndex);
            }
        });

        mImgBtnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = mCrimeList.size() - 1;
                updateData(mCurrentIndex);;
            }
        });


    }

    private void updateData(int index) {
        mEditTextTitle.setText(mCrimeList.get(index).getTitle().toString());
        mCheckBoxSolved.setChecked(mCrimeList.get(index).isSolved());
    }

    private void updateCrime() {
        mRepository.updateCrime(mCrime);
    }
}