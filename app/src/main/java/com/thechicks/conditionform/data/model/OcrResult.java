package com.thechicks.conditionform.data.model;

/**
 * Created by opklnm102 on 2016-05-20.
 */
public class OcrResult {

    String pillName;

    String dosageTotal;

    String dosageOneTime;

    String dosageTotalDays;

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public String getDosageTotal() {
        return dosageTotal;
    }

    public void setDosageTotal(String dosageTotal) {
        this.dosageTotal = dosageTotal;
    }

    public String getDosageOneTime() {
        return dosageOneTime;
    }

    public void setDosageOneTime(String dosageOneTime) {
        this.dosageOneTime = dosageOneTime;
    }

    public String getDosageTotalDays() {
        return dosageTotalDays;
    }

    public void setDosageTotalDays(String dosageTotalDays) {
        this.dosageTotalDays = dosageTotalDays;
    }
}
