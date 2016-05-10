package com.thechicks.conditionform.ui.search;

/**
 * Created by Administrator on 2016-04-11.
 */
public class PillSearchItem {
    String image;
    String pillName;
    int pillNumber;

    public PillSearchItem(String image, String pillName, int pillNumber) {
        this.image = image;
        this.pillName = pillName;
        this.pillNumber = pillNumber;
    }

    public PillSearchItem() {
        this.image = "";
        this.pillName = "";
        this.pillNumber = -1;
    }


    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public int getPillNumber() {
        return pillNumber;
    }

    public void setPillNumber(int pillNumber) {
        this.pillNumber = pillNumber;
    }
}
