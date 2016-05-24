package com.thechicks.conditionform.data.model;

import com.thechicks.conditionform.util.Constants;
import com.thechicks.conditionform.util.TimeUtils;

/**
 * Created by opklnm102 on 2016-04-24.
 */
public class History {

    int diseaseId;
    String color;  //#ffffff
    String name;
    int img;

    long dateStart;  //시작일
    long dateEnd;  //종료일

    int dosageType;  //식사, 시간마다. type에 따라 뷰가 바뀐다.

    //시간마다.
    int timeInterval;

    String strDosageType;

    public History() {
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
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

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;

        setStrDosageType();
    }

    public String getStrDosageType() {
        return strDosageType;
    }

    private void setStrDosageType() {

        switch (dosageType) {
            case Constants.DOSAGE_TYPE_EVERYHOUR:
                this.strDosageType = timeInterval + "시간마다";
                break;
            case Constants.DOSAGE_TYPE_EVERYDAY:
                this.strDosageType = "매일";
                break;
            case Constants.DOSAGE_TYPE_TWODAY:
                this.strDosageType = "2일마다";
                break;

            case Constants.DOSAGE_TYPE_THREEDAY:
                this.strDosageType = "3일마다";
                break;
        }
    }

    public History(String color, String name, int img, long dateStart, long dateEnd, int dosageType, int timeInterval, String strDosageType) {

        this.color = color;
        this.name = name;
        this.img = img;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dosageType = dosageType;
        this.timeInterval = timeInterval;

        setStrDosageType();
    }
}
