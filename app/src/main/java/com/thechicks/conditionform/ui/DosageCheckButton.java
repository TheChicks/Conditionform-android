package com.thechicks.conditionform.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import com.thechicks.conditionform.util.ViewUtils;

/**
 * Created by opklnm102 on 2016-06-01.
 */
public class DosageCheckButton extends Button {

    private String colorChecked;
    private String colorUnChecked;
    private boolean checked;

    public DosageCheckButton(Context context) {
        super(context);
        init();
    }

    public DosageCheckButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DosageCheckButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        colorUnChecked = "#c2c2c2";
    }

    public String getColorChecked() {
        return colorChecked;
    }

    public void setColorChecked(String colorChecked) {
        this.colorChecked = colorChecked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        update();
    }

    private void update() {
        if (checked) {
            this.setText("복용");
            this.setTextColor(Color.parseColor(colorChecked));
            ((GradientDrawable) this.getBackground()).setStroke(ViewUtils.dpToPixel(getContext(), 1), Color.parseColor(colorChecked));
        } else {
            this.setText("빠트림");
            this.setTextColor(Color.parseColor(colorUnChecked));
            ((GradientDrawable) this.getBackground()).setStroke(ViewUtils.dpToPixel(getContext(), 1), Color.parseColor(colorUnChecked));
        }
    }
}
