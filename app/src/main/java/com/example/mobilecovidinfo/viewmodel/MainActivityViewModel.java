package com.example.mobilecovidinfo.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilecovidinfo.model.Data;
import com.example.mobilecovidinfo.model.State;
import com.example.mobilecovidinfo.retrofit.RetrofitConfig;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final RetrofitConfig retrofitConfig;

    private MutableLiveData<List<State>> stateList = new MutableLiveData<>();

    public MainActivityViewModel() {
        this.retrofitConfig = new RetrofitConfig();
    }
}

