package com.thechicks.conditionform.ui;

import com.thechicks.conditionform.data.model.OcrResult;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-05-28.
 */
public class EventOcrResult {

    private ArrayList<OcrResult> mOcrResults;

    public EventOcrResult() {
    }

    public EventOcrResult(ArrayList<OcrResult> ocrResults) {
        mOcrResults = ocrResults;
    }

    public ArrayList<OcrResult> getOcrResults() {
        return mOcrResults;
    }
}
