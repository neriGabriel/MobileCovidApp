package com.example.mobilecovidinfo.view;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobilecovidinfo.R;
import com.example.mobilecovidinfo.adapter.StateMainFragmentAdapter;
import com.example.mobilecovidinfo.databinding.FragmentMainBinding;
import com.example.mobilecovidinfo.model.State;
import com.example.mobilecovidinfo.viewmodel.MainFragmentViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    FragmentMainBinding fragmentMainBinding;
    MainFragmentViewModel mainFragmentViewModel;
    ActionBar actionBar;

    private List<State> stateList = new ArrayList<>();
    private StateMainFragmentAdapter stateAdapter;



    public MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.fragmentMainBinding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_main);
        this.fragmentMainBinding.setLifecycleOwner(this);
        this.fragmentMainBinding.getRoot();
        this.actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        this.actionBar.setTitle("Covid Info");

        this.mainFragmentViewModel = new ViewModelProvider(this).get(MainFragmentViewModel.class);

        this.stateAdapter = new StateMainFragmentAdapter(this.stateList);
        this.fragmentMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.fragmentMainBinding.recyclerView.setAdapter(this.stateAdapter);

        this.mainFragmentViewModel.getAlertMessage().observe(fragmentMainBinding.getLifecycleOwner(), s -> {
            fragmentMainBinding.txtAlerta.setText(s);
        });

        this.mainFragmentViewModel.getCovidInfo().observe(fragmentMainBinding.getLifecycleOwner(), states -> {
            if(states != null) {
                this.stateList.addAll(states);
                this.stateAdapter.notifyDataSetChanged();
            }else {
                Toast.makeText(getContext(), "Não foi possível conectar ao provedor de informações!", Toast.LENGTH_SHORT).show();
            }

        });

        return this.getView();
    }
}
