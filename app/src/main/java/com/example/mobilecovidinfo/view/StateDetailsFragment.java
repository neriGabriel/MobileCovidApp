package com.example.mobilecovidinfo.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobilecovidinfo.R;
import com.example.mobilecovidinfo.databinding.FragmentStateDetailsBinding;
import com.example.mobilecovidinfo.model.State;
import com.example.mobilecovidinfo.util.DateUtil;
import com.example.mobilecovidinfo.util.NumberUtil;
import com.example.mobilecovidinfo.viewmodel.MainFragmentViewModel;
import com.example.mobilecovidinfo.viewmodel.StateDetailsFragmentViewModel;

public class StateDetailsFragment extends Fragment {

    FragmentStateDetailsBinding fragmentStateDetailsBinding;
    StateDetailsFragmentViewModel stateDetailsFragmentViewModel;
    ActionBar actionBar;
    State state ;



    public StateDetailsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.fragmentStateDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_state_details, container, false);
        this.fragmentStateDetailsBinding.setLifecycleOwner(this);
        this.stateDetailsFragmentViewModel = new ViewModelProvider(this).get(StateDetailsFragmentViewModel.class);

        this.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        this.actionBar.setTitle("State info");

        this.stateDetailsFragmentViewModel.getStateMutableLiveData().observe(this.fragmentStateDetailsBinding.getLifecycleOwner(), state1 -> {
            this.fragmentStateDetailsBinding.txtTotalCasos.setText(NumberUtil.currencyFormat(String.valueOf(state.getCases())));
            this.fragmentStateDetailsBinding.txtTotalMortes.setText(NumberUtil.currencyFormat(String.valueOf(state.getDeaths())));
            this.fragmentStateDetailsBinding.txtTotalRecusados.setText(NumberUtil.currencyFormat(String.valueOf(state.getRefuses())));
            this.fragmentStateDetailsBinding.txtTotalSuspeitos.setText(NumberUtil.currencyFormat(String.valueOf(state.getSuspects())));
            this.fragmentStateDetailsBinding.txtTotalComentarios.setText(state.getComments());
            this.fragmentStateDetailsBinding.txtSgila.setText(state.getUf());
            this.fragmentStateDetailsBinding.txtEstado.setText(state.getState());
        });

        return this.fragmentStateDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null) {
            state = StateDetailsFragmentArgs.fromBundle(getArguments()).getState();

            this.stateDetailsFragmentViewModel.setStateMutableLiveData(state).observe(this.fragmentStateDetailsBinding.getLifecycleOwner(), state -> {
                if(state != null) {
                    Log.d("StateDetailsFragment", "[LOG] state data setted to viewmodel");
                } else {
                    Log.d("StateDetailsFragment", "[LOG] erro to set state to viewmodel");
                }
            });
        } else {
            state = new State();
        }
    }
}
