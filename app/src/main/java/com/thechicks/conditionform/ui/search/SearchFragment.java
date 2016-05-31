package com.thechicks.conditionform.ui.search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.thechicks.conditionform.data.remote.BackendHelper;
import com.thechicks.conditionform.data.model.Pill;
import com.thechicks.conditionform.ui.detail.PillDetailsActivity;
import com.thechicks.conditionform.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 검색 화면
 */
public class SearchFragment extends Fragment {

    public static final String TAG = SearchFragment.class.getSimpleName();

    @Bind(R.id.recyclerview_search_item)
    RecyclerView recyclerView;

    PillSearchListAdapter mPillSearchListAdapter;

    protected static BackendHelper sBackendHelper;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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

        if (sBackendHelper == null) {
            sBackendHelper = BackendHelper.getInstance();
        }

        Log.d("onCreate ", TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        mPillSearchListAdapter = new PillSearchListAdapter(getActivity());
        recyclerView.setAdapter(mPillSearchListAdapter);
        mPillSearchListAdapter.setOnListItemClickListener(new PillSearchListAdapter.OnListItemClickListener() {
            @Override
            public void onListItemClick(Pill pill) {
                Intent intent = new Intent(getActivity(), PillDetailsActivity.class);

                // 데이터 넘기기
                intent.putExtra("pill", pill);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
            }
        });
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

    @Subscribe
    public void onEvent(EventPillSearch event) {

        Call<JsonArray> call = sBackendHelper.getPillInformation(event.word);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                JsonArray jaRoot = response.body();

                Log.e(TAG, " onResponse()");

                if (jaRoot != null) {

                    Log.e(TAG, " " + jaRoot);

                    ArrayList<Pill> pillArrayList = new ArrayList<Pill>();

                    for (int i = 0; i < jaRoot.size(); i++) {
                        Pill pill = new Gson().fromJson(jaRoot.get(i), Pill.class);
                        pillArrayList.add(pill);
                    }

                    mPillSearchListAdapter.setData(pillArrayList);
                } else {
                    Log.e(TAG, "jaRoot null");
                    //Todo: 검색결과 없음을 표시
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e(TAG, " Throwable is " + t);
            }
        });
    }
}
