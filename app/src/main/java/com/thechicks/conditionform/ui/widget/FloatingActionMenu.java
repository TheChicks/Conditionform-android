package com.thechicks.conditionform.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.ui.extension.PushBehavior;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dong on 2016-07-22.
 */
//https://github.com/ctrannik/RecyclerSyncDemo 참고
@CoordinatorLayout.DefaultBehavior(PushBehavior.class)
public class FloatingActionMenu extends LinearLayout {

    private Boolean isExpanded = Boolean.FALSE;

    @Bind({R.id.fab_menu_register_auto, R.id.fab_menu_register_manual})
    View[] fabMenus;

    @Bind({R.id.relative_fab_menu_register_auto, R.id.relative_fab_menu_register_manual})
    View[] rlFabMenu;

    @Bind(R.id.fab_menu_open)
    FloatingActionButton fabMenuOpen;

//    @Bind(R.id.fab_menu_register_auto)
//    FloatingActionButton fabRegisterAuto;
//
//    @Bind(R.id.fab_menu_register_manual)
//    FloatingActionButton fabRegisterManual;
//
//    @Bind(R.id.relative_fab_menu_register_auto)
//    RelativeLayout rlFabMenuRegisterAuto;
//
//    @Bind(R.id.relative_fab_menu_register_manual)
//    RelativeLayout rlFabMenuRegisterManual;

    //Animations
    Animation animShowFabMenuRegisterAuto;
    Animation animHideFabMenuRegisterAuto;
    Animation animShowFabMenuRegisterManual;
    Animation animHideFabMenuRegisterManual;

    private final Context mContext;

    private OnClickListener[] mOnClickListeners;

    public FloatingActionMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        if (!isInEditMode()) {
            LayoutInflater.from(context).inflate(R.layout.fab_menu, this);
            ButterKnife.bind(this);

            init(context, attrs);
        }
    }

    private void init(@NonNull final Context context, AttributeSet attrs) {
        final TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.FloatingActionButton,
                0, 0);

        try {
            isExpanded = a.getBoolean(R.styleable.FloatingActionMenu_fam_expanded, Boolean.FALSE);
        } finally {
            a.recycle();
        }

        mOnClickListeners = new OnClickListener[fabMenus.length];

        animShowFabMenuRegisterAuto = AnimationUtils.loadAnimation(context, R.anim.show_fab_menu_register_auto);
        animHideFabMenuRegisterAuto = AnimationUtils.loadAnimation(context, R.anim.hide_fab_menu_register_auto);
        animShowFabMenuRegisterManual = AnimationUtils.loadAnimation(context, R.anim.show_fab_menu_register_manual);
        animHideFabMenuRegisterManual = AnimationUtils.loadAnimation(context, R.anim.hide_fab_menu_register_manual);

        if (isExpanded) {
            toggleExpand();
        }
    }

    @OnClick(R.id.fab_menu_open)
    void toggleExpand() {
        if (isExpanded) {
            hideFab();
//            Integer delayMs = 50;
//            for (final View v : fabMenus) {
//                final Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.show_fab_menu_register_auto);
//                anim.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        v.setVisibility(INVISIBLE);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//                    }
//                });
//                anim.setInterpolator(new Interpolator() {
//                    @Override
//                    public float getInterpolation(float input) {
//                        return Math.abs(input - 1f);
//                    }
//                });
//                anim.setStartOffset(delayMs);
//                delayMs -= 50;
//                v.startAnimation(anim);
//            }
        } else {
            expandFab();
//            Integer delayMs = 0;
//            for (final View v : fabMenus) {
//                final Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.show_fab_menu_register_auto);
//                anim.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//                        v.setVisibility(VISIBLE);
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//                    }
//                });
//                anim.setStartOffset(delayMs);
//                delayMs += 50;
//                v.startAnimation(anim);
//            }
        }
        isExpanded = !isExpanded;
    }

    @OnClick(R.id.fab_menu_register_auto)
    void onClick1() {
        triggerClickOnButton(0);
        toggleExpand();
    }

    @OnClick(R.id.fab_menu_register_manual)
    void onClick2() {
        triggerClickOnButton(1);
        toggleExpand();
    }

    public void setOnItemClickListener(@NonNull final Integer actionIndex, final OnClickListener listener) {
        mOnClickListeners[actionIndex] = listener;
    }

    private void triggerClickOnButton(@NonNull final Integer index) {
        if (mOnClickListeners[index] != null) {
            mOnClickListeners[index].onClick(fabMenus[index]);
        }
    }

    private void expandFab() {
        FrameLayout.LayoutParams lpFabMenu1 = (FrameLayout.LayoutParams) rlFabMenu[0].getLayoutParams();
        lpFabMenu1.bottomMargin += (int) (rlFabMenu[0].getHeight() * 1.2);
        rlFabMenu[0].setLayoutParams(lpFabMenu1);
        rlFabMenu[0].startAnimation(animShowFabMenuRegisterAuto);
        fabMenus[0].setClickable(true);

        FrameLayout.LayoutParams lpFabMenu2 = (FrameLayout.LayoutParams) rlFabMenu[1].getLayoutParams();
        lpFabMenu2.bottomMargin += (int) (rlFabMenu[1].getHeight() * 2.3);
        rlFabMenu[1].setLayoutParams(lpFabMenu2);
        rlFabMenu[1].startAnimation(animShowFabMenuRegisterManual);
        fabMenus[1].setClickable(true);
    }

    private void hideFab() {
        FrameLayout.LayoutParams lpFabMenu1 = (FrameLayout.LayoutParams) rlFabMenu[0].getLayoutParams();
        lpFabMenu1.bottomMargin -= (int) (rlFabMenu[0].getHeight() * 1.2);
        rlFabMenu[0].setLayoutParams(lpFabMenu1);
        rlFabMenu[0].startAnimation(animHideFabMenuRegisterAuto);
        fabMenus[0].setClickable(false);

        FrameLayout.LayoutParams lpFabMenu2 = (FrameLayout.LayoutParams) rlFabMenu[1].getLayoutParams();
        lpFabMenu2.bottomMargin -= (int) (rlFabMenu[1].getHeight() * 2.3);
        rlFabMenu[1].setLayoutParams(lpFabMenu2);
        rlFabMenu[1].startAnimation(animHideFabMenuRegisterManual);
        fabMenus[1].setClickable(false);
    }
}