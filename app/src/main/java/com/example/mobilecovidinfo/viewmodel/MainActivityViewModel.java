package com.example.mobilecovidinfo.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilecovidinfo.model.Data;
import com.example.mobilecovidinfo.model.State;
import com.example.mobilecovidinfo.retrofit.RetrofitConfig;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    private final RetrofitConfig retrofitConfig;

    private MutableLiveData<List<State>> stateList = new MutableLiveData<>();

    public MainActivityViewModel() {
        this.retrofitConfig = new RetrofitConfig();
    }
}

