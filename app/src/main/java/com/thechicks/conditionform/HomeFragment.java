package com.thechicks.conditionform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 메인 화면
 */
public class HomeFragment extends Fragment {

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Bind(R.id.imageView_date_previous)
    ImageView ivDatePrevious;

    @Bind(R.id.imageView_date_next)
    ImageView ivDateNext;

    @Bind(R.id.textView_date_toDay)
    TextView tvDateToday;

    @Bind(R.id.recyclerView_disease)
    RecyclerView rvDisease;

    @Bind(R.id.fab_add)
    FloatingActionButton fabAdd;

    @Bind(R.id.fab_1)
    FloatingActionButton fab1;

    @Bind(R.id.fab_2)
    FloatingActionButton fab2;

    @Bind(R.id.relative_fab_menu1)
    RelativeLayout rlFabMenu1;

    @Bind(R.id.relative_fab_menu2)
    RelativeLayout rlFabMenu2;

    //Save the fab's active status
    //false -> fab = close
    //true -> fab = open
    private boolean fabStatus = false;

    //Animations
    Animation showFab1;
    Animation hideFab1;
    Animation showFab2;
    Animation hideFab2;

    DiseaseListAdapter mDiseaseListAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Log.d("onCreate ", TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvDisease.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvDisease.setHasFixedSize(true);

        mDiseaseListAdapter = new DiseaseListAdapter(getActivity());
        rvDisease.setAdapter(mDiseaseListAdapter);

        rvDisease.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(fabStatus){
                    hideFab();
                    fabStatus = false;
                }
                return fabStatus;
            }
        });

        showFab1 = AnimationUtils.loadAnimation(getActivity(), R.anim.fab1_show);
        hideFab1 = AnimationUtils.loadAnimation(getActivity(), R.anim.fab1_hide);
        showFab2 = AnimationUtils.loadAnimation(getActivity(), R.anim.fab2_show);
        hideFab2 = AnimationUtils.loadAnimation(getActivity(), R.anim.fab2_hide);

        setupToday();
    }

    public void setupToday() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        tvDateToday.setText(month + "월 " + day + "일");
    }

    //Todo: 이전 날짜로 이동
    @OnClick(R.id.imageView_date_previous)
    public void onClickDatePrevious() {

    }

    //Todo: 다음 날짜로 이동
    @OnClick(R.id.imageView_date_next)
    public void onClickDateNext() {

    }

    //Todo: DatePicker 표시하고 넘어온 Date로 변화
    @OnClick(R.id.textView_date_toDay)
    public void onClickDateToday() {

    }

    @OnClick(R.id.fab_add)
    public void onClickFabAdd() {

        if (fabStatus) {
            //Close FAB menu
            hideFab();
            fabStatus = false;
        } else {
            //Display FAB menu
            expandFab();
            fabStatus = true;
        }
    }

    @OnClick(R.id.fab_1)
    public void onClickFabMenu1(){
        Toast.makeText(getActivity(), "fab1", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.fab_2)
    public void onClickFabMenu2(){
        Toast.makeText(getActivity(), "fab2", Toast.LENGTH_LONG).show();
    }

    private void expandFab() {

        FrameLayout.LayoutParams lpFabMenu1 = (FrameLayout.LayoutParams)rlFabMenu1.getLayoutParams();
        lpFabMenu1.bottomMargin += (int)(rlFabMenu1.getHeight() * 1.2);
        rlFabMenu1.setLayoutParams(lpFabMenu1);
        rlFabMenu1.startAnimation(showFab1);
        fab1.setClickable(true);

        FrameLayout.LayoutParams lpFabMenu2 = (FrameLayout.LayoutParams)rlFabMenu2.getLayoutParams();
        lpFabMenu2.bottomMargin += (int)(rlFabMenu2.getHeight() * 2.3);
        rlFabMenu2.setLayoutParams(lpFabMenu2);
        rlFabMenu2.startAnimation(showFab2);
        fab2.setClickable(true);
    }

    private void hideFab() {

        FrameLayout.LayoutParams lpFabMenu1 = (FrameLayout.LayoutParams)rlFabMenu1.getLayoutParams();
        lpFabMenu1.bottomMargin -= (int)(rlFabMenu1.getHeight() * 1.2);
        rlFabMenu1.setLayoutParams(lpFabMenu1);
        rlFabMenu1.startAnimation(hideFab1);
        fab1.setClickable(fabStatus);

        FrameLayout.LayoutParams lpFabMenu2 = (FrameLayout.LayoutParams)rlFabMenu2.getLayoutParams();
        lpFabMenu2.bottomMargin -= (int)(rlFabMenu2.getHeight() * 2.3);
        rlFabMenu2.setLayoutParams(lpFabMenu2);
        rlFabMenu2.startAnimation(hideFab2);
        fab1.setClickable(fabStatus);
    }
}
