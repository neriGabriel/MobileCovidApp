package com.example.mobilecovidinfo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilecovidinfo.model.Data;
import com.example.mobilecovidinfo.model.State;
import com.example.mobilecovidinfo.retrofit.RetrofitConfig;
import com.example.mobilecovidinfo.util.NumberUtil;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainFragmentViewModel extends ViewModel {

    private final RetrofitConfig retrofitConfig;

    private MutableLiveData<String> alertMessage = new MutableLiveData<>();
    private MutableLiveData<List<State>> state = new MutableLiveData<>();

    public MainFragmentViewModel() {
        this.retrofitConfig = new RetrofitConfig();
    }

    public MutableLiveData<String> getAlertMessage() {
            Integer totalCases = 0;

            if(this.state.getValue() != null) {
                for (State state : this.state.getValue()) {
                    totalCases += state.getCases();
                }
            }
            this.alertMessage.setValue("Nº de casos no país subiu para "+ NumberUtil.currencyFormat(String.valueOf(totalCases)));
        return  alertMessage;
    }

    public MutableLiveData<List<State>> getCovidInfo() {
        if(this.state.getValue() == null) {
            this.retrofitConfig.getStateApi()
                               .getAllInfo()
                               .subscribeOn(Schedulers.newThread())
                               .observeOn(AndroidSchedulers.mainThread())
                               .subscribe(data -> {
                                   this.state.setValue(data.getData());
                               }, Throwable -> {
                                   this.state.setValue(null);
                               });
        }
        return state;
    }
}
