package com.rikucherry.startrek.model;

/**
 * Item class for DestinationActivity.
 */
public class ObjectsItem {

    private int parentImageId1;
    private int parentImageId2;
    private int parentImageId3;
    private int childImageId;
    private String systemName;
    private String objectName;
    private String objectCategory;
    private String objectIntroduction;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public int getParentImageId1() {
        return parentImageId1;
    }

    public void setParentImageId1(int parentImageId1) {
        this.parentImageId1 = parentImageId1;
    }

    public int getParentImageId2() {
        return parentImageId2;
    }

    public void setParentImageId2(int parentImageId2) {
        this.parentImageId2 = parentImageId2;
    }

    public int getParentImageId3() {
        return parentImageId3;
    }

    public void setParentImageId3(int parentImageId3) {
        this.parentImageId3 = parentImageId3;
    }

    public int getChildImageId() {
        return childImageId;
    }

    public void setChildImageId(int childImageId) {
        this.childImageId = childImageId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectCategory() {
        return objectCategory;
    }

    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory;
    }

    public String getObjectIntroduction() {
        return objectIntroduction;
    }

    public void setObjectIntroduction(String objectIntroduction) {
        this.objectIntroduction = objectIntroduction;
    }


}
