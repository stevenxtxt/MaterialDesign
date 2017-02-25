package com.xute.materialdesign;

import android.app.Application;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by xute on 2016/11/23.
 */

public class MDApplication extends Application {

    private static MDApplication instance;

    public static MDApplication getInstance() {
        return instance;
    }

    public static final String WX_APPID = "wx4bbf5585b31ca33a";
    public static final String WX_APPSECRET = "6977937cd6cefbba0b47df00075cdb5a";

    private IWXAPI iwxapi;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        iwxapi = WXAPIFactory.createWXAPI(this, WX_APPID, true);
        iwxapi.registerApp(WX_APPID);
    }
}
