package com.example.mobilecovidinfo.retrofit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit config;

    private String baseUrl = "https://covid19-brazil-api.now.sh/api/report/v1/";

    public RetrofitConfig() {
        this.config = new Retrofit.Builder()
                                  .baseUrl(this.baseUrl)
                                  .addConverterFactory(GsonConverterFactory.create())
                                  .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                                  .build();
    }

    public StateAPI getStateApi() {
        return this.config.create(StateAPI.class);
    }
}
