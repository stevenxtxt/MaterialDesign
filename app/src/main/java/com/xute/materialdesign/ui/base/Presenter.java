package com.xute.materialdesign.ui.base;

/**
 * Created by xute on 2016/12/23.
 */

public interface Presenter<V> {
    void attachView(V view);
    void detachView();
}
