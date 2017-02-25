package com.xute.materialdesign.framework.net;

import com.xute.materialdesign.framework.bean.WechatBean;
import com.xute.materialdesign.framework.bean.WechatUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by xute on 2016/12/26.
 */

public interface WXService {
    @GET("sns/oauth2/access_token")
    Call<WechatBean> getWechatBase(@Query("appid") String appId, @Query("secret") String secret,
                                   @Query("code") String code, @Query("grant_type") String grantType);

    @GET("sns/userinfo")
    Call<WechatUser> getWechatUserInfo(@Query("access_token") String accessToken, @Query("openid") String openId);
}
