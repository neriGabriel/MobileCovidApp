package com.example.mobilecovidinfo.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilecovidinfo.database.DatabaseConnection;
import com.example.mobilecovidinfo.model.Data;
import com.example.mobilecovidinfo.model.State;
import com.example.mobilecovidinfo.retrofit.RetrofitConfig;
import com.example.mobilecovidinfo.util.NumberUtil;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainFragmentViewModel extends AndroidViewModel {

    private final RetrofitConfig retrofitConfig;
    private final DatabaseConnection connection;

    private MutableLiveData<String> alertMessage = new MutableLiveData<>();
    private MutableLiveData<List<State>> state = new MutableLiveData<>();

    public MainFragmentViewModel(@NonNull Application application) {
        super(application);
        this.retrofitConfig = new RetrofitConfig();
        this.connection = DatabaseConnection.getInstance(application);
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
                                   this.connection.stateDao().delete();
                                   this.connection.stateDao().insert(data.getData());
                               }, Throwable -> {
                                   this.state.setValue(this.getCovidInfoRoom().getValue());
                                   Toast.makeText(getApplication(), "Não foi possível conectar com o servidor de informações! utilizaremos as informações armazenadas no device!", Toast.LENGTH_SHORT).show();
                               });
        }
        return state;
    }

    private MutableLiveData<List<State>> getCovidInfoRoom() {
        if(this.state.getValue() == null) {
            state.setValue(this.connection.stateDao().getLastRegisters());
        }
        return state;
    }
}
