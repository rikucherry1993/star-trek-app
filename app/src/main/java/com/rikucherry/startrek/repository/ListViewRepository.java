package com.rikucherry.startrek.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rikucherry.startrek.model.ObjectsItem;

import java.util.List;

/**
 * repository provides data to view model
 * and gets data from DB or via api(not decided)
 */
public class ListViewRepository {

    private ListViewService listViewService;
    private static ListViewRepository listViewRepository;


    public static ListViewRepository getInstance() {

        if (listViewRepository == null) {
            listViewRepository = new ListViewRepository();
        }
        return listViewRepository;
    }

    private ListViewRepository() {

        listViewService = new ListViewService();
    }


    // get data from service layer that would be called by viewModel
    public LiveData<List<ObjectsItem>> getObjectsItem(String speed, String time, String timeUnit){
        final MutableLiveData<List<ObjectsItem>> data = new MutableLiveData<>();

        data.postValue(listViewService.getObjectsItemList(speed,time,timeUnit));

        return data;

    }



}
