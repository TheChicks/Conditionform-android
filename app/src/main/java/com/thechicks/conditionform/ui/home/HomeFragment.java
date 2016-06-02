package com.thechicks.conditionform.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.database.ConditionformDao;
import com.thechicks.conditionform.data.model.Disease;
import com.thechicks.conditionform.util.AsyncHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 메인 화면
 */
public class HomeFragment extends Fragment {

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Bind(R.id.recyclerView_disease)
    RecyclerView rvDisease;

//   @BindDimen(R.dimen.fab_margin)
//    int fabMargin;

    DiseaseListAdapter mDiseaseListAdapter;

    ConditionformDao conditionformDao;

    private long mCurrentDayTimestamp;

    private static final String ARG_PARAM_CURRENT_TIMESTAMP = "param_current_timestamp";


    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(long currentDayTimestamp) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM_CURRENT_TIMESTAMP, currentDayTimestamp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCurrentDayTimestamp = getArguments().getLong(ARG_PARAM_CURRENT_TIMESTAMP);
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

                        final Disease item = conditionformDao.findDiseaseWithHistoryByIdAndDate(disease.getId(), mCurrentDayTimestamp);
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

//        rvDisease.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (fabStatus) {
//                    hideFab();
//                    shrinkFlTransBg();
//                    Log.d(TAG, " recyclerView");
//                }
//                return false;
//            }
//        });

//        animationLoad();
//        setupToday();

        conditionformDao = new ConditionformDao(getActivity());
    }


    @Override
    public void onResume() {
        super.onResume();

        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Disease> diseaseList = conditionformDao.findDiseaseByDate(mCurrentDayTimestamp);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDiseaseListAdapter.setItemList(diseaseList);
                    }
                });
            }
        });
    }

//    //암시적 인텐트로 갤러리 호출
//    @Override
//    public void onClickGallery() {
//        Intent intentGallery = new Intent(Intent.ACTION_PICK);
//        intentGallery.setType(MediaStore.Images.Media.CONTENT_TYPE);
//        intentGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intentGallery, REQUEST_CODE_GALLERY);
//    }
//
//    //암시적 인텐트로 카메라 호출
//    @Override
//    public void onClickCamera() {
//        Intent intentCameraCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intentCameraCapture, REQUEST_CODE_CAMERA_CAPTURE);
//    }
//
//    public static final int REQUEST_CODE_CAMERA_CAPTURE = 2000;
//    public static final int REQUEST_CODE_GALLERY = 2001;

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case REQUEST_CODE_REGIST_MANUAL:
//                if (resultCode == Activity.RESULT_OK) {
//                    Toast.makeText(getActivity(), "등록 성공!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(), "등록 실패! 다시 등록해주세요~", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case REQUEST_CODE_CAMERA_CAPTURE:
//                if (resultCode == Activity.RESULT_OK) {
//                    //사진 Uri 인텐트로 전송
//                    Intent intent = new Intent(getActivity(), RegistAutoActivity.class);
//                    intent.setData(data.getData());  //사진 Uri 넘김
//                    startActivity(intent);
//                }
//                break;
//            case REQUEST_CODE_GALLERY:
//                if (resultCode == Activity.RESULT_OK) {
//                    //사진 Uri 인텐트로 전송
//                    Intent intent = new Intent(getActivity(), RegistAutoActivity.class);
//                    intent.setData(data.getData());  //사진 Uri 넘김
//                    startActivity(intent);
//                }
//                break;
//        }
//    }


    @Subscribe
    public void onEvent(EventDateChange eventDateChange) {

        mDiseaseListAdapter.setItemList(eventDateChange.getDiseaseArrayList());

    }
}