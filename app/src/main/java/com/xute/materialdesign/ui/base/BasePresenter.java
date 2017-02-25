package com.xute.materialdesign.ui.base;

import android.content.Context;

import com.xute.materialdesign.framework.net.ApiService;
import com.xute.materialdesign.framework.net.HttpExecutor;
import com.xute.materialdesign.framework.net.WXService;

/**
 * Created by xute on 2016/12/23.
 */

public abstract class BasePresenter<V extends IMvpView> implements Presenter<V> {
    protected final String CODE_SUCCESS = "0000";

    protected ApiService apiService = HttpExecutor.getInstance().getApiService();

    protected WXService wxService = HttpExecutor.getInstance().getWxService();

    protected V mvpView;

    @Override
    public void attachView(V view) {
        mvpView = view;
    }

    @Override
    public void detachView() {
        mvpView = null;
    }

    protected Context getContext() {
        if (mvpView != null) {
            return mvpView.getContext();
        }
        return null;
    }
}
