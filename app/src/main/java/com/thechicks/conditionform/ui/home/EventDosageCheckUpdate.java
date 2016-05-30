package com.thechicks.conditionform.ui.home;

/**
 * Created by opklnm102 on 2016-05-30.
 */
public class EventDosageCheckUpdate {

    int diseaseId;
    boolean takeWakeup;
    boolean takeMorning;
    boolean takeLunch;
    boolean takeEvening;
    boolean takeSleep;

    public EventDosageCheckUpdate(int diseaseId, boolean takeWakeup, boolean takeMorning, boolean takeLunch, boolean takeEvening, boolean takeSleep) {
        this.diseaseId = diseaseId;
        this.takeWakeup = takeWakeup;
        this.takeMorning = takeMorning;
        this.takeLunch = takeLunch;
        this.takeEvening = takeEvening;
        this.takeSleep = takeSleep;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
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
}
