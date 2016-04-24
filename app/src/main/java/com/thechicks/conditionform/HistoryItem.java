package com.thechicks.conditionform;

/**
 * Created by Administrator on 2016-04-24.
 */
public class HistoryItem {

    String color;  //#ffffff
    String name;
    int img;

    long dateStart;  //시작일
    long dateEnd;  //종료일

    int type;  //식사, 시간마다. type에 따라 뷰가 바뀐다.

    //시간마다.
    int timeInterval;

    String strDosageType;

    public HistoryItem() {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getStrDosageType() {
        return strDosageType;
    }

    public void setStrDosageType(String strDosageType) {
        this.strDosageType = strDosageType;
    }

    public HistoryItem(String color, String name, int img, long dateStart, long dateEnd, int type, int timeInterval, String strDosageType) {

        this.color = color;
        this.name = name;
        this.img = img;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.type = type;
        this.timeInterval = timeInterval;
        this.strDosageType = strDosageType;
    }
}
