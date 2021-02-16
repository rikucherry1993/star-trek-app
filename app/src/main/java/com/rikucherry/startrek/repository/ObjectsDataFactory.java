package com.rikucherry.startrek.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class ObjectsDataFactory extends DataSource.Factory {

    private String speed;
    private String time;
    private ObjectDataSource dataSource;
    private MutableLiveData<ObjectDataSource> mutableLiveData;

    public ObjectsDataFactory(String speed, String time){
        this.speed = speed;
        this.time = time;
        this.mutableLiveData = new MutableLiveData<>();
    }


    @NonNull
    @Override
    public DataSource create() {

        dataSource = new ObjectDataSource(speed,time);
        mutableLiveData.postValue(dataSource);

        return dataSource;
    }


    public MutableLiveData<ObjectDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

}
