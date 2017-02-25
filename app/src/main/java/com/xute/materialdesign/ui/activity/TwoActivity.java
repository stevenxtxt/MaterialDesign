package com.xute.materialdesign.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xute.materialdesign.MDApplication;
import com.xute.materialdesign.R;
import com.xute.materialdesign.framework.bean.CodeEvent;
import com.xute.materialdesign.framework.bean.User;
import com.xute.materialdesign.framework.bean.WechatBean;
import com.xute.materialdesign.framework.bean.WechatUser;
import com.xute.materialdesign.ui.presenter.UserPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by xute on 2016/12/8.
 */

public class TwoActivity extends AppCompatActivity implements UserPresenter.MvpView {

    @BindView(R.id.civ_photo) CircleImageView civPhoto;
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_followers) TextView tvFollowers;
    @BindView(R.id.tv_followings) TextView tvFollowings;
    @BindView(R.id.tv_repos) TextView tvRepos;
    @BindView(R.id.btn_login) Button btnLogin;

    private UserPresenter mPresenter = new UserPresenter();

    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter.attachView(this);

        mPresenter.getUserInfo("stevenxtxt");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @OnClick(R.id.btn_login)
    public void onWechatLogin() {
        if (api == null) {
            api = WXAPIFactory.createWXAPI(this, MDApplication.WX_APPID, true);
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_login_demo";
        api.sendReq(req);
    }

    @Override
    public void showUserInfo(User user) {
        Glide.with(this).load(user.getAvatar_url()).into(civPhoto);
        tvName.setText(user.getLogin());
        tvFollowers.setText("Follower:" + user.getFollowers());
        tvFollowings.setText("Following:" + user.getFollowing());
    }

    @Override
    public void handleAccessToken(WechatBean wechatBean) {
        String accessToken = wechatBean.getAccess_token();
        String openId = wechatBean.getOpenid();
        mPresenter.getWechatUserInfo(accessToken, openId);
    }

    @Override
    public void showWechatUserInfo(WechatUser wechatUser) {
        Glide.with(this).load(wechatUser.getHeadimgurl()).into(civPhoto);
        tvName.setText(wechatUser.getNickname());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Subscribe
    public void onEvent(CodeEvent event) {
        if (event.code == CodeEvent.WX_CODE_SUCCESSFULLY) {
            Toast.makeText(this, "Login successfully!", Toast.LENGTH_LONG).show();
            Log.d("WECHAT_LOGING>>>>>>>>>", event.getMessage());
            String code = event.getMessage();
            mPresenter.getAccessToken(code);
        }
    }
}
