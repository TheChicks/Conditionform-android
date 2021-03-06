package com.thechicks.conditionform.ui.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.History;
import com.thechicks.conditionform.util.TimeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by opklnm102 on 2016-04-24.
 */
public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_NORMAL = 0;  //내역
    public static final int VIEW_TYPE_HEADER = 1;  //헤더

    private final Context mContext;
    private List<History> mHistoryArrayList;  //DB에서 받아온 아이템 보관용(헤더 처리전)
    private final List<Object> mHistoryHeaderExistArrayList;  //실제로 보이는 아이템(헤더 처리 후)

    //item click event
    private OnListItemClickListener mOnListItemClickListener;

    public interface OnListItemClickListener {
        void onListItemClick(History history);
    }

    public void setOnListItemClickListener(OnListItemClickListener listener) {
        mOnListItemClickListener = listener;
    }

    public HistoryListAdapter(Context context) {
        mContext = context;
        mHistoryArrayList = new ArrayList<>();
        mHistoryHeaderExistArrayList = new ArrayList<>();

        //initData();
    }

    //dummy data
    public void initData() {
        int i = 0;

        addItem(new History("#2c90d7", "안약", R.mipmap.ic_launcher, 1451828241L, 1451828241L, 0, 2, "2시간마다"), i++);
        addItem(new History("#7f26d3", "감기", R.mipmap.ic_launcher, 1454593041L, 1454593041L, 0, 2, "매일"), i++);
        addItem(new History("#7f26d3", "감기", R.mipmap.ic_launcher, 1457271441L, 1457271441L, 0, 2, "매일"), i++);
        addItem(new History("#2c90d7", "안약", R.mipmap.ic_launcher, 1457357841L, 1457357841L, 0, 2, "2시간마다"), i++);
        addItem(new History("#2c90d7", "안약", R.mipmap.ic_launcher, 1460209041L, 1460209041L, 0, 2, "2시간마다"), i++);
        addItem(new History("#2c90d7", "안약", R.mipmap.ic_launcher, 1457357841L, 1457357841L, 0, 2, "2시간마다"), i++);
        addItem(new History("#2c90d7", "안약", R.mipmap.ic_launcher, 1476193041L, 1476193041L, 0, 2, "2시간마다"), i++);
        addItem(new History("#7f26d3", "감기", R.mipmap.ic_launcher, 1465738641L, 1465738641L, 0, 2, "매일"), i++);
        addItem(new History("#2c90d7", "안약", R.mipmap.ic_launcher, 1497361041L, 1497361041L, 0, 2, "2시간마다"), i++);
        addItem(new History("#2c90d7", "안약", R.mipmap.ic_launcher, 1508593041L, 1508593041L, 0, 2, "2시간마다"), i++);
        addItem(new History("#7f26d3", "감기", R.mipmap.ic_launcher, 1515937041L, 1515937041L, 0, 2, "매일"), i++);
        addItem(new History("#2c90d7", "안약", R.mipmap.ic_launcher, 1459863441L, 1459863441L, 0, 2, "2시간마다"), i++);
        addItem(new History("#7f26d3", "감기", R.mipmap.ic_launcher, 1502890641L, 1502890641L, 0, 2, "매일"), i++);
        addItem(new History("#2c90d7", "안약", R.mipmap.ic_launcher, 1523972241L, 1523972241L, 0, 2, "2시간마다"), i++);
        addItem(new History("#7f26d3", "감기", R.mipmap.ic_launcher, 1505741841L, 1505741841L, 0, 2, "매일"), i++);
        addItem(new History("#7f26d3", "감기", R.mipmap.ic_launcher, 1462887441L, 1462887441L, 0, 2, "매일"), i++);
        addItem(new History("#2c90d7", "안약", R.mipmap.ic_launcher, 1505828241L, 1505828241L, 0, 2, "2시간마다"), i++);
        addItem(new History("#7f26d3", "감기", R.mipmap.ic_launcher, 1532093841L, 1532093841L, 0, 2, "매일"), i++);
        addItem(new History("#7f26d3", "감기", R.mipmap.ic_launcher, 1451741841L, 1451741841L, 0, 2, "매일"), i++);
        addItem(new History("#2c90d7", "안약", R.mipmap.ic_launcher, 1457357841L, 1457357841L, 0, 2, "2시간마다"), i++);

        sortHistoryList(mHistoryArrayList);
        insertHeaderView();
    }

    //헤더 처리전에 정렬
    private void sortHistoryList(List<History> list) {
        mHistoryArrayList = list;

        //내림차순 정렬
        Collections.sort(mHistoryArrayList, new Comparator<History>() {

            @Override
            public int compare(History lhs, History rhs) {
//                int lhsDateStartYear = lhs.getDateStartYear();
//                int rhsDateStartYear = rhs.getDateStartYear();
//                if (lhsDateStartYear > rhsDateStartYear) {
//                    return -1;
//                } else if (lhsDateStartYear == rhsDateStartYear) {
//                    int lhsDateStartMonth = lhs.getDateStartMonth();
//                    int rhsDateStartMonth = rhs.getDateStartMonth();
//                    if (lhsDateStartMonth > rhsDateStartMonth) {
//                        return -1;
//                    } else if (lhsDateStartMonth == rhsDateStartMonth) {
//                        int lhsDateStartDay = lhs.getDateStartDay();
//                        int rhsDateStartDay = rhs.getDateStartDay();
//                        if (lhsDateStartDay > rhsDateStartDay) {
//                            return -1;
//                        } else if (lhsDateStartDay == rhsDateStartDay) {
//                            return 0;
//                        } else {
//                            return 1;
//                        }
//                    } else {
//                        return 1;
//                    }
//                } else {
//                    return 1;
//                }

                //개선
                return (lhs.getDateStart() > rhs.getDateStart()) ? -1 : (lhs.getDateStart() > rhs.getDateStart()) ? 1 : 0;
            }
        });
    }

    //헤더 삽입
    private void insertHeaderView() {
        mHistoryHeaderExistArrayList.clear();

        String lastHeader = "";
        for (int i = 0; i < mHistoryArrayList.size(); i++) {
            History history = mHistoryArrayList.get(i);

            String header = TimeUtils.timestampToYear(history.getDateStart()) + "년 " + TimeUtils.timestampToMonth(history.getDateStart()) + "월";
            if (!TextUtils.equals(lastHeader, header)) {
                mHistoryHeaderExistArrayList.add(header);
                lastHeader = header;
            }
            mHistoryHeaderExistArrayList.add(history);
        }
    }

    //헤더인지 판단
    @Override
    public int getItemViewType(int position) {
        return mHistoryHeaderExistArrayList.get(position) instanceof String ? VIEW_TYPE_HEADER : VIEW_TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                return HistoryHeaderViewHolder.newInstance(parent);
            case VIEW_TYPE_NORMAL:
                return HistoryNormalViewHolder.newInstance(parent, mOnListItemClickListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Object item = mHistoryHeaderExistArrayList.get(position);

        if (holder instanceof HistoryNormalViewHolder) {
            ((HistoryNormalViewHolder) holder).bind((History) item);
        } else {
            ((HistoryHeaderViewHolder) holder).bind((String) item);
        }
    }

    @Override
    public int getItemCount() {
        return mHistoryHeaderExistArrayList.size();
    }

    public void addItem(History history, int position) {
        mHistoryArrayList.add(position, history);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mHistoryArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public void setItemList(List<History> historyList){

        mHistoryArrayList = historyList;

        sortHistoryList(mHistoryArrayList);
        insertHeaderView();
        notifyDataSetChanged();
    }
}
