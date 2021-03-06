package com.thechicks.conditionform.ui.search;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thechicks.conditionform.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by opklnm102 on 2016-04-29.
 */
public class SearchEditWidget extends LinearLayout {

    @Bind(R.id.imageView_search_query_clear)
    ImageView ivClear;

    @Bind(R.id.editText_search_query)
    EditText etSearch;

    public SearchEditWidget(Context context) {
        super(context);
        init();
    }

    public SearchEditWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchEditWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_search_edit, this, true);

        ButterKnife.bind(this);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etSearch.getText().length() == 0) {
                    ivClear.setVisibility(INVISIBLE);
                } else {
                    ivClear.setVisibility(VISIBLE);
//                    EventBus.getDefault().post(new EventPillSearch(etSearch.getText().toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    //검색 api 호출
                    EventBus.getDefault().post(new EventPillSearch(etSearch.getText().toString()));

                    etSearch.setText(null);
                    return true;
                }
                return false;
            }
        });

        ivClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.setText(null);
            }
        });
    }
}
