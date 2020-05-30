package com.example.mobilecovidinfo.retrofit;

import com.example.mobilecovidinfo.model.Data;
import com.example.mobilecovidinfo.model.State;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface StateAPI {
    @GET(".")
    Observable<Data> getAllInfo();
}
