package com.xute.materialdesign.framework.net;

import com.xute.materialdesign.framework.bean.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xute on 2016/12/21.
 */

public interface ApiService {

    @GET("users/{user}")
    Call<User> getUserInfo(@Path("user") String user);
}
