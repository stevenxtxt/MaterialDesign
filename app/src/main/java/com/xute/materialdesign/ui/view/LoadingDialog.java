package com.xute.materialdesign.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.xute.materialdesign.R;
import com.xute.materialdesign.ui.base.BaseDialog;

/**
 * Created by xute on 2016/12/23.
 */

public class LoadingDialog extends BaseDialog {


    public LoadingDialog(Context context) {
        super(context, R.style.Dialog_NullBackground);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public static LoadingDialog show(Context context) {
        LoadingDialog dialog = new LoadingDialog(context);
        try {
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
    }
}
