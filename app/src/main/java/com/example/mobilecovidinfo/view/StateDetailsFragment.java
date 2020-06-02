package com.example.mobilecovidinfo.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobilecovidinfo.R;
import com.example.mobilecovidinfo.databinding.FragmentStateDetailsBinding;
import com.example.mobilecovidinfo.model.State;
import com.example.mobilecovidinfo.util.DateUtil;

public class StateDetailsFragment extends Fragment {

    FragmentStateDetailsBinding fragmentStateDetailsBinding;
    ActionBar actionBar;
    State state ;



    public StateDetailsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.fragmentStateDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_state_details, container, false);
        this.fragmentStateDetailsBinding.setLifecycleOwner(this);

        this.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        this.actionBar.setTitle("State info");

        return this.fragmentStateDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null) {
            state = StateDetailsFragmentArgs.fromBundle(getArguments()).getState();
        } else {
            state = new State();
        }
    }
}
