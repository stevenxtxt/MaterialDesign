package com.xute.materialdesign.ui.presenter;

import com.xute.materialdesign.MDApplication;
import com.xute.materialdesign.framework.bean.User;
import com.xute.materialdesign.framework.bean.WechatBean;
import com.xute.materialdesign.framework.bean.WechatUser;
import com.xute.materialdesign.framework.net.OnResponseListenter;
import com.xute.materialdesign.ui.base.BasePresenter;
import com.xute.materialdesign.ui.base.IMvpView;

import retrofit2.Call;

/**
 * Created by xute on 2016/12/23.
 */

public class UserPresenter extends BasePresenter<UserPresenter.MvpView> {

    public void getUserInfo(String username) {
        Call<User> call = apiService.getUserInfo(username);
        call.enqueue(new OnResponseListenter<User>(getContext()) {

            @Override
            public void onSuccess(User user) {
                mvpView.showUserInfo(user);
            }
        });
    }

    public void getAccessToken(String code) {
        Call<WechatBean> call = wxService.getWechatBase(MDApplication.WX_APPID, MDApplication.WX_APPSECRET, code, "authorization_code");
        call.enqueue(new OnResponseListenter<WechatBean>(getContext()) {
            @Override
            public void onSuccess(WechatBean wechatBean) {
                 mvpView.handleAccessToken(wechatBean);
            }
        });
    }

    public void getWechatUserInfo(String accessToken, String openId) {
        Call<WechatUser> call = wxService.getWechatUserInfo(accessToken, openId);
        call.enqueue(new OnResponseListenter<WechatUser>(getContext()) {
            @Override
            public void onSuccess(WechatUser wechatUser) {
                mvpView.showWechatUserInfo(wechatUser);
            }
        });
    }

    public interface MvpView extends IMvpView {
        void showUserInfo(User user);

        void handleAccessToken(WechatBean wechatBean);

        void showWechatUserInfo(WechatUser wechatUser);
    }
}
