package com.example.mobilecovidinfo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilecovidinfo.model.Data;
import com.example.mobilecovidinfo.model.State;
import com.example.mobilecovidinfo.retrofit.RetrofitConfig;

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
            Random random = new Random();
            int randomNumber = random.nextInt(1000);
            this.alertMessage.setValue("Nº de casos no país subiu para "+randomNumber);
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
