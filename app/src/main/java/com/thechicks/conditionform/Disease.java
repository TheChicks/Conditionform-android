package com.thechicks.conditionform;

import java.util.ArrayList;

/**
 * Created by Dong on 2016-04-09.
 */
public class Disease {

    String color;  //#ffffff
    String name;
    int img;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    ArrayList<Pill> mPillArrayList;

    long dateStart;  //시작일
    long dateEnd;  //종료일

    int type;  //식사, 시간마다. type에 따라 뷰가 바뀐다.

    //type: 식사마다.
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
    int timeInterval;
    int dosageCurrnt;
    int dosageTotal;

    //Todo: 알람부분 채워 넣기


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Disease(String color, String name, int img, ArrayList<Pill> pillArrayList, long dateStart, long dateEnd, int type, boolean showMorning, boolean showLunch, boolean showDinner, boolean showSleep, boolean morning, boolean lunch, boolean dinner, boolean sleep, int timeInterval, int dosageCurrnt, int dosageTotal) {
        this.color = color;
        this.name = name;
        this.img = img;
        mPillArrayList = pillArrayList;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.type = type;
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
    }

    public Disease() {
    }
}
