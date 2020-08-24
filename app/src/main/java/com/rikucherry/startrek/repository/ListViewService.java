package com.rikucherry.startrek.repository;


import com.rikucherry.startrek.R;
import com.rikucherry.startrek.model.ObjectsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * service for calling search destination api
 */
public class ListViewService {

    private List<ObjectsItem> objectsItemList = new ArrayList<>();

    // return list to repository(the mediator combines viewModel and model)
    public List<ObjectsItem> getObjectsItemList(String speed, String time){

        //TODO: Replace with an actual api, show loading modal.
                setFakeData();

        return objectsItemList;

    }

    // TODO: mock-up. delete later.
    private void setFakeData(){
        ObjectsItem object1 = new ObjectsItem();
        object1.setImageResourceId(R.drawable.alpha_centauri_mock);
        object1.setObjectName("Alpha Centauri");
        object1.setObjectCategory("Star");
        object1.setObjectIntroduction("4th brightest star, a component of the triple Alpha Centauri star system. ");

        ObjectsItem object2 = new ObjectsItem();
        object2.setImageResourceId(R.drawable.beta_centauri_mock);
        object2.setObjectName("Beta Centauri");
        object2.setObjectCategory("Star");
        object2.setObjectIntroduction("Officially called Hadar,is a triple star system in the southern constellation of Centaurus.");

        ObjectsItem object3 = new ObjectsItem();
        object3.setImageResourceId(R.drawable.menkent_mock);
        object3.setObjectName("Theta Centauri");
        object3.setObjectCategory("Star");
        object3.setObjectIntroduction("Officially named Menkent,is a star in the southern constellation of Centaurus.");

        ObjectsItem object4 = new ObjectsItem();
        object4.setImageResourceId(R.drawable.gamma_centauri_mock);
        object4.setObjectName("Gamma Centauri");
        object4.setObjectCategory("Star");
        object4.setObjectIntroduction("A star in the southern constellation Centaurus. It has the proper name Muhlifain");

        ObjectsItem object5 = new ObjectsItem();
        object5.setImageResourceId(R.drawable.epsilon_centauri_mock);
        object5.setObjectName("Epsilon Centauri");
        object5.setObjectCategory("Star");
        object5.setObjectIntroduction("A star in the southern constellation of Centaurus. It is one of the brightest stars in the constellation with a slightly variable apparent visual magnitude of +2.30.");

        objectsItemList.clear();
        objectsItemList.add(object1);
        objectsItemList.add(object2);
        objectsItemList.add(object3);
        objectsItemList.add(object4);
        objectsItemList.add(object5);
    }


}
