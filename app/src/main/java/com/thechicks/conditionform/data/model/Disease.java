package com.thechicks.conditionform.data.model;

import java.util.ArrayList;

/**
 * Created by Dong on 2016-04-09.
 */
public class Disease {

    // dosage type
    public static final int DOSAGE_TYPE_EVERYDAY = 5000;  //매일
    public static final int DOSAGE_TYPE_TWODAY = 5001;  //2일
    public static final int DOSAGE_TYPE_THREEDAY = 5002;  //3일
    public static final int DOSAGE_TYPE_EVERYHOUR = 5003;  //시간마다

    String color;  //#ffffff
    int img;
    String name;

    ArrayList<Pill> mPillArrayList;

    long dateStart;  //시작일
    long dateEnd;  //종료일

    int dosageType;  //식사, 시간마다. type에 따라 뷰가 바뀐다.

    // dosage type: 매일, 2일, 3일
    // 표시 여부
    boolean showMorning;
    boolean showLunch;
    boolean showDinner;
    boolean showSleep;

    //먹었느지 안먹었는지
    boolean morning;
    boolean lunch;
    boolean dinner;
    boolean sleep;

    //type: 시간마다. 얼마나 먹었는지 표시
    int timeStartHour;
    int timeStartMinute;
    int timeInterval;
    int dosageCurrnt;
    int dosageTotal;

    ArrayList<TimeItem> mTimeItems;


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

    public boolean isShowMorning() {
        return showMorning;
    }

    public void setShowMorning(boolean showMorning) {
        this.showMorning = showMorning;
    }

    public boolean isShowLunch() {
        return showLunch;
    }

    public void setShowLunch(boolean showLunch) {
        this.showLunch = showLunch;
    }

    public boolean isShowDinner() {
        return showDinner;
    }

    public void setShowDinner(boolean showDinner) {
        this.showDinner = showDinner;
    }

    public boolean isShowSleep() {
        return showSleep;
    }

    public void setShowSleep(boolean showSleep) {
        this.showSleep = showSleep;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isLunch() {
        return lunch;
    }

    public void setLunch(boolean lunch) {
        this.lunch = lunch;
    }

    public boolean isDinner() {
        return dinner;
    }

    public void setDinner(boolean dinner) {
        this.dinner = dinner;
    }

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
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

    public Disease(String color, int img, String name, ArrayList<Pill> pillArrayList, long dateStart, long dateEnd, int dosageType, boolean showMorning, boolean showLunch, boolean showDinner, boolean showSleep, boolean morning, boolean lunch, boolean dinner, boolean sleep, int timeInterval, int dosageCurrnt, int dosageTotal, ArrayList<TimeItem> timeItems) {
        this.color = color;
        this.img = img;
        this.name = name;
        mPillArrayList = pillArrayList;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dosageType = dosageType;
        this.showMorning = showMorning;
        this.showLunch = showLunch;
        this.showDinner = showDinner;
        this.showSleep = showSleep;
        this.morning = morning;
        this.lunch = lunch;
        this.dinner = dinner;
        this.sleep = sleep;
        this.timeInterval = timeInterval;
        this.dosageCurrnt = dosageCurrnt;
        this.dosageTotal = dosageTotal;
        mTimeItems = timeItems;
    }

    public Disease() {
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
                ", showMorning=" + showMorning +
                ", showLunch=" + showLunch +
                ", showDinner=" + showDinner +
                ", showSleep=" + showSleep +
                ", morning=" + morning +
                ", lunch=" + lunch +
                ", dinner=" + dinner +
                ", sleep=" + sleep +
                ", timeStartHour=" + timeStartHour +
                ", timeStartMinute=" + timeStartMinute +
                ", timeInterval=" + timeInterval +
                ", dosageCurrnt=" + dosageCurrnt +
                ", dosageTotal=" + dosageTotal +
                ", mTimeItems=" + mTimeItems +
                '}';
    }
}
