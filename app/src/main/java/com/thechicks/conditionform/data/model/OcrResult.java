package com.thechicks.conditionform.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by opklnm102 on 2016-05-20.
 */
public class OcrResult implements Serializable {

    Pill pill;

    @SerializedName("quantity")
    int dosageOneTime;  // 1회복용량

    @SerializedName("onedayDosage")
    int dosageOneDay;  // 1일 투여횟수

    @SerializedName("totalDayDosage")
    int dosageTotalDays;  // 총 투약 일수

    public Pill getPill() {
        return pill;
    }

    public void setPill(Pill pill) {
        this.pill = pill;
    }

    public int getDosageOneTime() {
        return dosageOneTime;
    }

    public void setDosageOneTime(int dosageOneTime) {
        this.dosageOneTime = dosageOneTime;
    }

    public int getDosageOneDay() {
        return dosageOneDay;
    }

    public void setDosageOneDay(int dosageOneDay) {
        this.dosageOneDay = dosageOneDay;
    }

    public int getDosageTotalDays() {
        return dosageTotalDays;
    }

    public void setDosageTotalDays(int dosageTotalDays) {
        this.dosageTotalDays = dosageTotalDays;
    }
}
