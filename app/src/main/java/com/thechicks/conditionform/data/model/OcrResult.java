package com.thechicks.conditionform.data.model;

import java.io.Serializable;

/**
 * Created by opklnm102 on 2016-05-20.
 */
public class OcrResult implements Serializable {

    Pill pill;

    int dosageTotal;

    int dosageOneTime;

    int dosageTotalDays;

    public Pill getPill() {
        return pill;
    }

    public void setPill(Pill pill) {
        this.pill = pill;
    }

    public int getDosageTotal() {
        return dosageTotal;
    }

    public void setDosageTotal(int dosageTotal) {
        this.dosageTotal = dosageTotal;
    }

    public int getDosageOneTime() {
        return dosageOneTime;
    }

    public void setDosageOneTime(int dosageOneTime) {
        this.dosageOneTime = dosageOneTime;
    }

    public int getDosageTotalDays() {
        return dosageTotalDays;
    }

    public void setDosageTotalDays(int dosageTotalDays) {
        this.dosageTotalDays = dosageTotalDays;
    }
}
