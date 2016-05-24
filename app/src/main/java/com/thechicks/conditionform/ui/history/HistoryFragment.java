package com.thechicks.conditionform.ui.history;

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
import android.widget.Toast;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.database.ConditionformDao;
import com.thechicks.conditionform.data.model.History;
import com.thechicks.conditionform.ui.HistoryDetailActivity;
import com.thechicks.conditionform.util.AsyncHandler;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 복용 내역 화면
 */
public class HistoryFragment extends Fragment {

    public static final String TAG = HistoryFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Bind(R.id.recyclerView_history)
    RecyclerView rvHistory;

    HistoryListAdapter mHistoryListAdapter;

    ConditionformDao conditionformDao;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvHistory.setHasFixedSize(true);

        mHistoryListAdapter = new HistoryListAdapter(getActivity());
        mHistoryListAdapter.setOnListItemClickListener(new HistoryListAdapter.OnListItemClickListener() {
            @Override
            public void onListItemClick(History history) {
                //복용내역 상세정보로 이동
                Intent intent = new Intent(getActivity(), HistoryDetailActivity.class);
                intent.putExtra("diseaseId", history.getDiseaseId());
                startActivity(intent);
            }
        });
        rvHistory.setAdapter(mHistoryListAdapter);

        conditionformDao = new ConditionformDao(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();

        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                final List<History> histories = conditionformDao.findAllDisease();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mHistoryListAdapter.setItemList(histories);
                    }
                });
            }
        });
    }
}
