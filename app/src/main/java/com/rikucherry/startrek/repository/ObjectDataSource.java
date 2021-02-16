package com.rikucherry.startrek.repository;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.rikucherry.startrek.model.ObjectsItem;

import java.util.List;

public class ObjectDataSource extends PageKeyedDataSource<Integer, ObjectsItem> {

    private List<ObjectsItem> dataList;
    private ListViewService listViewService;
    private String speed;
    private String time;


    public ObjectDataSource(String speed, String time){
        listViewService = new ListViewService();
        this.speed = speed;
        this.time = time;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ObjectsItem> callback) {
        List<ObjectsItem> dataList = listViewService.getObjectsItemList(speed, time);
        callback.onResult(dataList, null, 2);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ObjectsItem> callback) {
//        List<ObjectsItem> dataList = listViewService.getObjectsItemList(speed, time);
//        Integer key = (params.key > 1) ? params.key - 1 : null;
//        callback.onResult(dataList,key);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ObjectsItem> callback) {
//        List<ObjectsItem> dataList = listViewService.getObjectsItemList(speed, time);
//        Integer key = (params.key > dataList.size() - 1) ? params.key + 1 : params.key;
//        callback.onResult(dataList,key);
    }
}
