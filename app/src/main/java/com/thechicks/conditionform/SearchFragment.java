package com.thechicks.conditionform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 검색 화면
 */
public class SearchFragment extends Fragment {

    public static final String TAG = SearchFragment.class.getSimpleName();

    @Bind(R.id.recyclerview_search_item)
    RecyclerView rvDisease;

    PillSearchListAdapter mDiseaseListAdapter;

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

        Log.d("onCreate ", TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_search, container, false);

/*
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.cardview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<PillSearchItem> items=new ArrayList<>();
        PillSearchItem[] item=new PillSearchItem[5];


        //recyclerView.setAdapter(new RecyclerAdapter(getActivity(),items,R.layout.activity_main));
*/
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvDisease.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvDisease.setHasFixedSize(true);

        mDiseaseListAdapter = new PillSearchListAdapter(getActivity());
        rvDisease.setAdapter(mDiseaseListAdapter);
        mDiseaseListAdapter.setOnListItemClickListener(new PillSearchListAdapter.OnListItemClickListener() {
            @Override
            public void onListItemClick(PillSearchItem disease) {
//                Intent intent = new Intent(getActivity(), 이동할 액티비티);
//                startActivity(intent);
                Toast.makeText(getActivity(), "List Item Click", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
