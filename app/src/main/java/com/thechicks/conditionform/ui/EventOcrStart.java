package com.thechicks.conditionform.ui;

import java.io.File;

/**
 * Created by Administrator on 2016-05-28.
 */
public class EventOcrStart {

    private File imageFileName;

    public EventOcrStart() {
    }

    public EventOcrStart(File imageFileName) {

        this.imageFileName = imageFileName;
    }

    public File getImageFileName() {
        return imageFileName;
    }
}
