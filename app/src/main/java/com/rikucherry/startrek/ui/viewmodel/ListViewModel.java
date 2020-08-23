package com.rikucherry.startrek.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rikucherry.startrek.model.ObjectsItem;
import com.rikucherry.startrek.repository.ListViewRepository;

import java.util.List;

/**
 * ViewModel for destination activity
 */
public class ListViewModel extends AndroidViewModel {

    // Observable object
    private final LiveData<List<ObjectsItem>> objectsItemObservable;

    // Constructor
    public ListViewModel(Application application, String speed, String time){
        super(application);
        objectsItemObservable = ListViewRepository.getInstance().getObjectsItem(speed, time);
    }


    // Publish the observable list to activity
    public LiveData<List<ObjectsItem>> getObjectsItemObservable() {
        return objectsItemObservable;
    }

}
