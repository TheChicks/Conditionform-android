package com.thechicks.conditionform;

/**
 * Created by Administrator on 2016-04-23.
 */
public class AlarmItem {

    String illness;
    String color;

    public AlarmItem(){
        this.illness = "";
        this.color = "";
    }
    public AlarmItem(String illness, String color){
        this.illness = illness;
        this.color = color;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
