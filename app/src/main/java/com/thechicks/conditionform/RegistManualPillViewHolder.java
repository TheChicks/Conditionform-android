package com.thechicks.conditionform;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-04-27.
 */
public class RegistManualPillViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.textView_pill_name)
    TextView tvKoName;

    @Bind(R.id.imageView_pill_remove)
    ImageView ivRemove;

    View mView;

    RegistManualPillAdapter.OnListItemClickListener mListener;

    public static RegistManualPillViewHolder newInstance(ViewGroup parent, RegistManualPillAdapter.OnListItemClickListener listener) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_regist_pill, parent, false);
        return new RegistManualPillViewHolder(itemView, listener);
    }

    private RegistManualPillViewHolder(View itemView, RegistManualPillAdapter.OnListItemClickListener listener) {
        super(itemView);

        mView = itemView;
        mListener = listener;

        ButterKnife.bind(this, mView);
    }

    public void bind(final Pill pill) {

        tvKoName.setText(pill.getKoName());
        tvKoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                int margin = ViewUtils.dpToPixel(mView.getContext(), 10);
                params.setMargins(margin, 0, margin, 0);

                final AppCompatEditText editText = new AppCompatEditText(mView.getContext());
                editText.setLayoutParams(params);

                new AlertDialog.Builder(mView.getContext())
                        .setTitle("약 이름 수정")
                        .setMessage("수정할 약 이름을 입력해주세요.")
                        .setView(editText)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String modifyName = editText.getText().toString();
                                tvKoName.setText(modifyName);
                                pill.setKoName(modifyName);

                                if(mListener != null){
                                    mListener.onListItemChange(getAdapterPosition());
                                }
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    //아이템 삭제 요청을 Adapter에 알린다.
                    mListener.onListItemRemove(getAdapterPosition());
                }
            }
        });
    }
}