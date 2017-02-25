package com.xute.materialdesign.framework.net;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xute.materialdesign.framework.bean.CodeMessage;
import com.xute.materialdesign.ui.view.LoadingDialog;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xute on 2016/12/21.
 */

public abstract class OnResponseListenter<T> implements Callback<T> {

    protected Context mContext;

    private LoadingDialog mLoadingDialog;

    public OnResponseListenter(Context context, boolean show) {
        mContext = context;
        if (show) {
            mLoadingDialog = LoadingDialog.show(mContext);
        }
    }

    public OnResponseListenter(Context context) {
        this(context, true);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        if (response.code() == 200) {
            onSuccess(response.body());
            mContext = null;
        } else if (response.code() == 400 || response.code() == 401) {
            try {
                String jsonStr = response.errorBody().string();
                if (!TextUtils.isEmpty(jsonStr)) {
                    CodeMessage codeMessage = new Gson().fromJson(jsonStr, CodeMessage.class);
                    onFailure(codeMessage.getCode(), codeMessage.getMessage());
                } else {
                    onFailure(response.code() + "", "接口返回 400 数据为空");
                }

            } catch (IOException e) {
                onFailure(response.code() + "", "接口返回 400 数据格式异常");
            }

        } else {
            onFailure(response.code() + "", "接口未知错误 " + response.code());
        }
    }

    public abstract void onSuccess(T t);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        onFailure("", "未知错误，请检查网络");
        mContext = null;
    }

    public void onFailure(String code, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
}
