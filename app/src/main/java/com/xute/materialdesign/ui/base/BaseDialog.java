package com.xute.materialdesign.ui.base;


import android.app.Dialog;
import android.content.Context;

import butterknife.ButterKnife;

/**
 * Created by xute on 2016/12/23.
 */

public class BaseDialog extends Dialog {
    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }
}
