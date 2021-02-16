package com.rikucherry.startrek.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.rikucherry.startrek.model.ObjectsItem;
import com.rikucherry.startrek.repository.ObjectsDataFactory;

/**
 * ViewModel for destination activity
 */
public class ListViewModel extends AndroidViewModel {

    // Observable object
    public LiveData<PagedList<ObjectsItem>> objectsItemObservable;

    // Constructor
    public ListViewModel(Application application, String speed, String time){
        super(application);

        ObjectsDataFactory dataFactory = new ObjectsDataFactory(speed,time);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(5).build();

        objectsItemObservable = (new LivePagedListBuilder(dataFactory,pagedListConfig)).build();
    }

}
