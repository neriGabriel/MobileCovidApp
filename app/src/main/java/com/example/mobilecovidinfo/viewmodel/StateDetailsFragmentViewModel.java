package com.example.mobilecovidinfo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilecovidinfo.model.State;

public class StateDetailsFragmentViewModel extends ViewModel {

    private MutableLiveData<State> stateMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<State> getStateMutableLiveData() {
        return stateMutableLiveData;
    }

    public MutableLiveData<State> setStateMutableLiveData(State state) {
        if(state != null) {
            stateMutableLiveData.setValue(state);
        }
        return stateMutableLiveData;
    }
}
