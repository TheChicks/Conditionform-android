package com.thechicks.conditionform.data.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dong on 2016-04-09.
 */
public class Disease implements Serializable {

    int id;
    String color;  //#ffffff
    int img;
    String name;

    ArrayList<Pill> mPillArrayList;

    long dateStart;  //시작일
    long dateEnd;  //종료일

    int dosageType;  //식사, 시간마다. type에 따라 뷰가 바뀐다.

    // dosage type: 매일, 2일, 3일
    // 표시 여부
    boolean enabledWakeup;
    boolean enabledMorning;
    boolean enabledLunch;
    boolean enabledEvening;
    boolean enabledSleep;

    //먹었느지 안먹었는지
    boolean takeWakeup;
    boolean takeMorning;
    boolean takeLunch;
    boolean takeEvening;
    boolean takeSleep;

    //type: 시간마다. 얼마나 먹었는지 표시
    int timeStartHour;
    int timeStartMinute;
    int timeInterval;
    int dosageCurrnt;
    int dosageTotal;

    ArrayList<TimeItem> mTimeItems;

    int dosageOneTime;
    int dosageTotalDays;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Pill> getPillArrayList() {
        return mPillArrayList;
    }

    public void setPillArrayList(ArrayList<Pill> pillArrayList) {
        mPillArrayList = pillArrayList;
    }

    public long getDateStart() {
        return dateStart;
    }

    public void setDateStart(long dateStart) {
        this.dateStart = dateStart;
    }

    public long getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(long dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getDosageType() {
        return dosageType;
    }

    public void setDosageType(int dosageType) {
        this.dosageType = dosageType;
    }

    public boolean isEnabledWakeup() {
        return enabledWakeup;
    }

    public void setEnabledWakeup(boolean enabledWakeup) {
        this.enabledWakeup = enabledWakeup;
    }

    public boolean isEnabledMorning() {
        return enabledMorning;
    }

    public void setEnabledMorning(boolean enabledMorning) {
        this.enabledMorning = enabledMorning;
    }

    public boolean isEnabledLunch() {
        return enabledLunch;
    }

    public void setEnabledLunch(boolean enabledLunch) {
        this.enabledLunch = enabledLunch;
    }

    public boolean isEnabledEvening() {
        return enabledEvening;
    }

    public void setEnabledEvening(boolean enabledEvening) {
        this.enabledEvening = enabledEvening;
    }

    public boolean isEnabledSleep() {
        return enabledSleep;
    }

    public void setEnabledSleep(boolean enabledSleep) {
        this.enabledSleep = enabledSleep;
    }

    public boolean isTakeWakeup() {
        return takeWakeup;
    }

    public void setTakeWakeup(boolean takeWakeup) {
        this.takeWakeup = takeWakeup;
    }

    public boolean isTakeMorning() {
        return takeMorning;
    }

    public void setTakeMorning(boolean takeMorning) {
        this.takeMorning = takeMorning;
    }

    public boolean isTakeLunch() {
        return takeLunch;
    }

    public void setTakeLunch(boolean takeLunch) {
        this.takeLunch = takeLunch;
    }

    public boolean isTakeEvening() {
        return takeEvening;
    }

    public void setTakeEvening(boolean takeEvening) {
        this.takeEvening = takeEvening;
    }

    public boolean isTakeSleep() {
        return takeSleep;
    }

    public void setTakeSleep(boolean takeSleep) {
        this.takeSleep = takeSleep;
    }

    public int getTimeStartHour() {
        return timeStartHour;
    }

    public void setTimeStartHour(int timeStartHour) {
        this.timeStartHour = timeStartHour;
    }

    public int getTimeStartMinute() {
        return timeStartMinute;
    }

    public void setTimeStartMinute(int timeStartMinute) {
        this.timeStartMinute = timeStartMinute;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public int getDosageCurrnt() {
        return dosageCurrnt;
    }

    public void setDosageCurrnt(int dosageCurrnt) {
        this.dosageCurrnt = dosageCurrnt;
    }

    public int getDosageTotal() {
        return dosageTotal;
    }

    public void setDosageTotal(int dosageTotal) {
        this.dosageTotal = dosageTotal;
    }

    public ArrayList<TimeItem> getTimeItems() {
        return mTimeItems;
    }

    public void setTimeItems(ArrayList<TimeItem> timeItems) {
        mTimeItems = timeItems;
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

    public Disease() {
    }

    public Disease(String color, int img, String name, ArrayList<Pill> pillArrayList, long dateStart, long dateEnd, int dosageType, boolean enabledWakeup, boolean enabledMorning, boolean enabledLunch, boolean enabledEvening, boolean enabledSleep, boolean takeWakeup, boolean takeMorning, boolean takeLunch, boolean takeEvening, boolean takeSleep, int timeStartHour, int timeStartMinute, int timeInterval, int dosageCurrnt, int dosageTotal, ArrayList<TimeItem> timeItems, int dosageOneTime, int dosageTotalDays) {
        this.color = color;
        this.img = img;
        this.name = name;
        mPillArrayList = pillArrayList;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dosageType = dosageType;
        this.enabledWakeup = enabledWakeup;
        this.enabledMorning = enabledMorning;
        this.enabledLunch = enabledLunch;
        this.enabledEvening = enabledEvening;
        this.enabledSleep = enabledSleep;
        this.takeWakeup = takeWakeup;
        this.takeMorning = takeMorning;
        this.takeLunch = takeLunch;
        this.takeEvening = takeEvening;
        this.takeSleep = takeSleep;
        this.timeStartHour = timeStartHour;
        this.timeStartMinute = timeStartMinute;
        this.timeInterval = timeInterval;
        this.dosageCurrnt = dosageCurrnt;
        this.dosageTotal = dosageTotal;
        mTimeItems = timeItems;
        this.dosageOneTime = dosageOneTime;
        this.dosageTotalDays = dosageTotalDays;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "color='" + color + '\'' +
                ", img=" + img +
                ", name='" + name + '\'' +
                ", mPillArrayList=" + mPillArrayList +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", dosageType=" + dosageType +
                ", enabledWakeup=" + enabledWakeup +
                ", enabledMorning=" + enabledMorning +
                ", enabledLunch=" + enabledLunch +
                ", enabledEvening=" + enabledEvening +
                ", enabledSleep=" + enabledSleep +
                ", takeWakeup=" + takeWakeup +
                ", takeMorning=" + takeMorning +
                ", takeLunch=" + takeLunch +
                ", takeEvening=" + takeEvening +
                ", takeSleep=" + takeSleep +
                ", timeStartHour=" + timeStartHour +
                ", timeStartMinute=" + timeStartMinute +
                ", timeInterval=" + timeInterval +
                ", dosageCurrnt=" + dosageCurrnt +
                ", dosageTotal=" + dosageTotal +
                ", mTimeItems=" + mTimeItems +
                ", dosageOneTime=" + dosageOneTime +
                ", dosageTotalDays=" + dosageTotalDays +
                '}';
    }
}
