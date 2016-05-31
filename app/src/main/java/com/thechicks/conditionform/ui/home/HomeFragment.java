package com.thechicks.conditionform.ui.home;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.database.ConditionformDao;
import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.ui.RegistAutoActivity;
import com.thechicks.conditionform.ui.regist.RegistManualActivity;
import com.thechicks.conditionform.util.AsyncHandler;
import com.thechicks.conditionform.util.TimeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 메인 화면
 */
public class HomeFragment extends Fragment implements RegistAutoDialog.RegistAutoDialogListener {

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Bind(R.id.textView_date_toDay)
    TextView tvDateToday;

    @Bind(R.id.recyclerView_disease)
    RecyclerView rvDisease;

    @Bind(R.id.fab_menu_register_auto)
    FloatingActionButton fabRegisterAuto;

    @Bind(R.id.fab_menu_register_manual)
    FloatingActionButton fabRegisterManual;

    @Bind(R.id.relative_fab_menu_register_auto)
    RelativeLayout rlFabMenuRegisterAuto;

    @Bind(R.id.relative_fab_menu_register_manual)
    RelativeLayout rlFabMenuRegisterManual;

    @Bind(R.id.trans_bg)
    FrameLayout flTransBg;

//   @BindDimen(R.dimen.fab_margin)
//    int fabMargin;

    //Save the fab's active status
    //false -> fab = close
    //true -> fab = open
    private boolean fabStatus = false;

    //Animations
    Animation animShowFabMenuRegisterAuto;
    Animation animHideFabMenuRegisterAuto;
    Animation animShowFabMenuRegisterManual;
    Animation animHideFabMenuRegisterManual;
    Animation animGrow;
    Animation animShrink;

    DiseaseListAdapter mDiseaseListAdapter;

    ConditionformDao conditionformDao;

    //시간 관리용
    int currentDisplayYear;
    int currentDisplayMonth;
    int currentDisplayDay;
    long currentDayTimestamp;

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
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
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
        mDiseaseListAdapter.setOnListItemClickListener(new DiseaseListAdapter.OnListItemClickListener() {
            @Override
            public void onListItemClick(final Disease disease) {
                // 복용내역 상세정보 표기
                //데이터 로드
                AsyncHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        final Disease item = conditionformDao.findDiseaseWithHistoryByIdAndDate(disease.getId(), currentDayTimestamp);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                DiseaseDosageCheckBottomSheetDialogFragment diseaseDosageCheckBottomSheetDialogFragment = DiseaseDosageCheckBottomSheetDialogFragment.newInstance(item);
                                diseaseDosageCheckBottomSheetDialogFragment.show(getChildFragmentManager(), diseaseDosageCheckBottomSheetDialogFragment.getTag());
                            }
                        });
                    }
                });
            }
        });

        rvDisease.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (fabStatus) {
                    hideFab();
                    shrinkFlTransBg();
                    Log.d(TAG, " recyclerView");
                }
                return false;
            }
        });

        animationLoad();
        setupToday();

        conditionformDao = new ConditionformDao(getActivity());
    }

    private void animationLoad() {
        animShowFabMenuRegisterAuto = AnimationUtils.loadAnimation(getActivity(), R.anim.show_fab_menu_register_auto);
        animHideFabMenuRegisterAuto = AnimationUtils.loadAnimation(getActivity(), R.anim.hide_fab_menu_register_auto);
        animShowFabMenuRegisterManual = AnimationUtils.loadAnimation(getActivity(), R.anim.show_fab_menu_register_manual);
        animHideFabMenuRegisterManual = AnimationUtils.loadAnimation(getActivity(), R.anim.hide_fab_menu_register_manual);
        animGrow = AnimationUtils.loadAnimation(getActivity(), R.anim.grow_color);
        animShrink = AnimationUtils.loadAnimation(getActivity(), R.anim.shrink_color);
    }

    public void setupToday() {
        final Calendar c = Calendar.getInstance();
        currentDayTimestamp = TimeUtils.getTodayUnixTimeStamp();

        currentDisplayYear = TimeUtils.timestampToYear(currentDayTimestamp);
        currentDisplayMonth = TimeUtils.timestampToMonth(currentDayTimestamp);
        currentDisplayDay = TimeUtils.timestampToDay(currentDayTimestamp);

        Log.e(TAG, currentDisplayYear + "년 " + currentDisplayMonth + "월 " + currentDisplayDay + "일");
        Log.e(TAG, " " + currentDayTimestamp);

        tvDateToday.setText(TimeUtils.unixTimeStampToStringDateMonthDay(currentDayTimestamp));
    }

    //이전 날짜로 이동
    @OnClick(R.id.imageView_date_previous)
    public void onClickDatePrevious() {
        Toast.makeText(getActivity(), "previous day", Toast.LENGTH_SHORT).show();

        currentDayTimestamp = TimeUtils.getYesterdayUnixTimeStamp(currentDayTimestamp);
        currentDisplayYear = TimeUtils.timestampToYear(currentDayTimestamp);
        currentDisplayMonth = TimeUtils.timestampToMonth(currentDayTimestamp);
        currentDisplayDay = TimeUtils.timestampToDay(currentDayTimestamp);

        tvDateToday.setText(TimeUtils.unixTimeStampToStringDateMonthDay(currentDayTimestamp));

        //데이터 로드
        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                final List<Disease> diseaseList = conditionformDao.findDiseaseByDate(currentDayTimestamp);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDiseaseListAdapter.setItemList(diseaseList);
                    }
                });
            }
        });
    }

    //다음 날짜로 이동
    @OnClick(R.id.imageView_date_next)
    public void onClickDateNext() {
        Toast.makeText(getActivity(), "next day", Toast.LENGTH_SHORT).show();

        currentDayTimestamp = TimeUtils.getTomorrowUnixTimeStamp(currentDayTimestamp);
        currentDisplayYear = TimeUtils.timestampToYear(currentDayTimestamp);
        currentDisplayMonth = TimeUtils.timestampToMonth(currentDayTimestamp);
        currentDisplayDay = TimeUtils.timestampToDay(currentDayTimestamp);

        tvDateToday.setText(TimeUtils.unixTimeStampToStringDateMonthDay(currentDayTimestamp));

        //데이터 로드
        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                final List<Disease> diseaseList = conditionformDao.findDiseaseByDate(currentDayTimestamp);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDiseaseListAdapter.setItemList(diseaseList);
                    }
                });
            }
        });
    }

    //DatePicker 표시하고 넘어온 Date로 변화
    @OnClick(R.id.textView_date_toDay)
    public void onClickDateToday() {
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                currentDisplayYear = year;
                currentDisplayMonth = monthOfYear + 1;
                currentDisplayDay = dayOfMonth;

                Log.e(TAG, currentDisplayYear + "년 " + currentDisplayMonth + "월 " + currentDisplayDay + "일");

                currentDayTimestamp = TimeUtils.getDayTimeStamp(currentDisplayYear, currentDisplayMonth, currentDisplayDay);

                tvDateToday.setText(TimeUtils.unixTimeStampToStringDateMonthDay(currentDayTimestamp));

                //데이터 로드
                AsyncHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        final List<Disease> diseaseList = conditionformDao.findDiseaseByDate(currentDayTimestamp);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mDiseaseListAdapter.setItemList(diseaseList);
                            }
                        });
                    }
                });
            }
        }, currentDisplayYear, currentDisplayMonth - 1, currentDisplayDay).show();
    }

    @OnClick(R.id.fab_menu_open)
    public void onClickFabMenuOpen() {

        if (fabStatus) {
            //Close FAB menu
            hideFab();
            shrinkFlTransBg();
        } else {
            //Display FAB menu
            expandFab();
            growFlTransBg();
        }
    }

    //Todo: 이벤트가 안날라온다. FrameLayout 영역문제. 애니메이션으로 커지는 영역은 실 영역이 아니다.
    @OnClick(R.id.trans_bg)
    public void onClickFlTransBg() {
        hideFab();
        shrinkFlTransBg();
    }

    private void shrinkFlTransBg() {

        flTransBg.startAnimation(animShrink);

//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)flTransBg.getLayoutParams();
//        layoutParams.width = 10;
//        layoutParams.height = 10;
//        layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
//        layoutParams.setMargins(fabMargin, fabMargin, fabMargin, fabMargin);
//        flTransBg.setLayoutParams(layoutParams);
    }

    private void growFlTransBg() {

        flTransBg.startAnimation(animGrow);

//        CoordinatorLayout.LayoutParams layoutParams =  (CoordinatorLayout.LayoutParams)flTransBg.getLayoutParams();
//        layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
//        layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT;
//        flTransBg.setLayoutParams(layoutParams);
    }

    //Todo: 자동 등록 화면으로 연결
    @OnClick(R.id.fab_menu_register_auto)
    public void onClickFabMenuRegisterAuto() {

        RegistAutoDialog registAutoDialog = RegistAutoDialog.newInstance();
        registAutoDialog.setTargetFragment(this, 0);
        registAutoDialog.show(getChildFragmentManager(), "fragment_regist_auto");
    }

    public static final int REQUEST_CODE_REGIST_MANUAL = 1002;

    //직접 등록 화면으로 연결
    @OnClick(R.id.fab_menu_register_manual)
    public void onClickFabMenuRegisterManual() {
        Intent intent = new Intent(getActivity(), RegistManualActivity.class);
        startActivityForResult(intent, REQUEST_CODE_REGIST_MANUAL);
    }

    private void expandFab() {

        FrameLayout.LayoutParams lpFabMenu1 = (FrameLayout.LayoutParams) rlFabMenuRegisterAuto.getLayoutParams();
        lpFabMenu1.bottomMargin += (int) (rlFabMenuRegisterAuto.getHeight() * 1.2);
        rlFabMenuRegisterAuto.setLayoutParams(lpFabMenu1);
        rlFabMenuRegisterAuto.startAnimation(animShowFabMenuRegisterAuto);
        fabRegisterAuto.setClickable(true);

        FrameLayout.LayoutParams lpFabMenu2 = (FrameLayout.LayoutParams) rlFabMenuRegisterManual.getLayoutParams();
        lpFabMenu2.bottomMargin += (int) (rlFabMenuRegisterManual.getHeight() * 2.3);
        rlFabMenuRegisterManual.setLayoutParams(lpFabMenu2);
        rlFabMenuRegisterManual.startAnimation(animShowFabMenuRegisterManual);
        fabRegisterManual.setClickable(true);

        fabStatus = true;
    }

    private void hideFab() {

        FrameLayout.LayoutParams lpFabMenu1 = (FrameLayout.LayoutParams) rlFabMenuRegisterAuto.getLayoutParams();
        lpFabMenu1.bottomMargin -= (int) (rlFabMenuRegisterAuto.getHeight() * 1.2);
        rlFabMenuRegisterAuto.setLayoutParams(lpFabMenu1);
        rlFabMenuRegisterAuto.startAnimation(animHideFabMenuRegisterAuto);
        fabRegisterAuto.setClickable(false);

        FrameLayout.LayoutParams lpFabMenu2 = (FrameLayout.LayoutParams) rlFabMenuRegisterManual.getLayoutParams();
        lpFabMenu2.bottomMargin -= (int) (rlFabMenuRegisterManual.getHeight() * 2.3);
        rlFabMenuRegisterManual.setLayoutParams(lpFabMenu2);
        rlFabMenuRegisterManual.startAnimation(animHideFabMenuRegisterManual);
        fabRegisterManual.setClickable(false);

        fabStatus = false;
    }

    @Override
    public void onResume() {
        super.onResume();

        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                final List<Disease> diseaseList = conditionformDao.findDiseaseByDate(currentDayTimestamp);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDiseaseListAdapter.setItemList(diseaseList);
                    }
                });
            }
        });
    }

    //암시적 인텐트로 갤러리 호출
    @Override
    public void onClickGallery() {
        Intent intentGallery = new Intent(Intent.ACTION_PICK);
        intentGallery.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intentGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentGallery, REQUEST_CODE_GALLERY);
    }

    //암시적 인텐트로 카메라 호출
    @Override
    public void onClickCamera() {
        Intent intentCameraCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCameraCapture, REQUEST_CODE_CAMERA_CAPTURE);
    }

    public static final int REQUEST_CODE_CAMERA_CAPTURE = 2000;
    public static final int REQUEST_CODE_GALLERY = 2001;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_REGIST_MANUAL:
                if(resultCode == Activity.RESULT_OK){
                    Toast.makeText(getActivity(), "등록 성공!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "등록 실패! 다시 등록해주세요~", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_CAMERA_CAPTURE:
                if (resultCode == Activity.RESULT_OK) {
                    //사진 Uri 인텐트로 전송
                    Intent intent = new Intent(getActivity(), RegistAutoActivity.class);
                    intent.setData(data.getData());  //사진 Uri 넘김
                    startActivity(intent);
                }
                break;
            case REQUEST_CODE_GALLERY:
                if (resultCode == Activity.RESULT_OK) {
                    //사진 Uri 인텐트로 전송
                    Intent intent = new Intent(getActivity(), RegistAutoActivity.class);
                    intent.setData(data.getData());  //사진 Uri 넘김
                    startActivity(intent);
                }
                break;
        }
    }

    @Subscribe
    public void onEvent(final EventDosageCheckUpdate eventDosageCheckUpdate){
        //Todo: DB update. 현재날짜와 id를 가지고

        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                //Todo: type 구분

                Disease disease = new Disease();
                disease.setId(eventDosageCheckUpdate.getDiseaseId());
                disease.setTakeWakeup(eventDosageCheckUpdate.isTakeWakeup());
                disease.setTakeMorning( eventDosageCheckUpdate.isTakeMorning());
                disease.setTakeLunch(eventDosageCheckUpdate.isTakeLunch());
                disease.setTakeEvening(eventDosageCheckUpdate.isTakeEvening());
                disease.setTakeSleep(eventDosageCheckUpdate.isTakeSleep());

                Log.e(TAG, " " + eventDosageCheckUpdate.getDiseaseId() + " " + eventDosageCheckUpdate.isTakeWakeup()
                + " " + eventDosageCheckUpdate.isTakeMorning() + " " + eventDosageCheckUpdate.isTakeLunch()
                + " " + eventDosageCheckUpdate.isTakeEvening() + " " + eventDosageCheckUpdate.isTakeSleep());

                if(conditionformDao.updateTakeHistory(currentDayTimestamp, disease)){
                    Toast.makeText(getActivity(), "수정됨", Toast.LENGTH_SHORT).show();

                    final List<Disease> diseaseList = conditionformDao.findDiseaseByDate(currentDayTimestamp);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mDiseaseListAdapter.setItemList(diseaseList);
                        }
                    });
                }
            }
        });
    }
}