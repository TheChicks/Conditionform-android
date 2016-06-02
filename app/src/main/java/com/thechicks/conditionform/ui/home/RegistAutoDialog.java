package com.thechicks.conditionform.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by opklnm102 on 2016-05-20.
 */
public class RegistAutoDialog extends DialogFragment {

    public interface RegistAutoDialogListener {
        public void onClickGallery();
        public void onClickCamera();
    }

    RegistAutoDialogListener callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (RegistAutoDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement NoticeDislogListener");
        }
    }

    public static RegistAutoDialog newInstance() {
        RegistAutoDialog fragment = new RegistAutoDialog();
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("처방전 가져오기")
                .setMessage("")
                .setPositiveButton("사진첩", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onClickGallery();
                    }
                })
                .setNegativeButton("찍기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onClickCamera();
                    }
                });
        return builder.create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (callback != null) {
            callback = null;
        }
    }
}
