package com.xute.materialdesign.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xute.materialdesign.MDApplication;
import com.xute.materialdesign.framework.bean.CodeEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xute on 2016/12/26.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, MDApplication.WX_APPID, false);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        String result = "";
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                EventBus.getDefault().post(new CodeEvent(CodeEvent.WX_CODE_SUCCESSFULLY, code));
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result ="Requst denied!";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "Requst canceled";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
                result = "发送返回";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }
}
