package com.xute.materialdesign.framework.net;

import android.util.Log;

import com.xute.materialdesign.framework.net.converter.GsonConverterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by xute on 2016/12/21.
 */

public class HttpExecutor {

    private final static String GITHUB_URL = "https://api.github.com/";
    private final static String WECHAT_URL = "https://api.weixin.qq.com/";

    protected ApiService apiService;
    protected WXService wxService;

    private OkHttpClient client = new OkHttpClient();

    private HttpExecutor() {
        client = client.newBuilder().addInterceptor(new HeadInterceptor()).build();

        apiService = new Retrofit.Builder().baseUrl(GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build().create(ApiService.class);

        wxService = new Retrofit.Builder().baseUrl(WECHAT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build().create(WXService.class);
    }

    private static HttpExecutor instance = new HttpExecutor();

    public static HttpExecutor getInstance() {
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public WXService getWxService() {
        return wxService;
    }

    class HeadInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request.newBuilder()
                    .addHeader("token", "myToken")
                    .build();

            Response response = chain.proceed(request);
            Log.i("LOG", "Response>>> ulr>>>>" + response.request().url());
            Log.i("LOG", "Response>>> status>>>>" + response.code());
            return response;
        }
    }
}
