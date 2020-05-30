package com.example.mobilecovidinfo.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<State> data = new ArrayList<>();

    public Data() {
    }

    public Data(List<State> data) {
        this.data = data;
    }

    public List<State> getData() {
        return data;
    }

    public void setData(List<State> data) {
        this.data = data;
    }
}
