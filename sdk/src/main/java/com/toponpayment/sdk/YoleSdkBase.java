package com.toponpayment.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.toponpayment.sdk.callback.CallBackFunction;
import com.toponpayment.sdk.callback.InitCallBackFunction;
import com.toponpayment.sdk.data.UserInfo;
import com.toponpayment.sdk.data.init.YoleInitConfig;
import com.toponpayment.sdk.ru_sms.SendSms;
import com.toponpayment.sdk.tool.NetworkRequest;

import java.io.IOException;
import java.net.URL;
import java.util.Timer;

public class YoleSdkBase {
    private String TAG = "Yole_YoleSdkBase";
//    private static  YoleSdkMgr _instance = null;
    protected Context context =  null;
    protected boolean isDebugger = false;


    /**各种网络接口**/
    protected NetworkRequest request = null;
    /**用户信息(通过用户设置 和 请求的返回。组装成的数据)**/
    public UserInfo user =  null;
    protected SendSms sms =  null;
    /**广告sdk初始化定时器*/
    protected Timer bigosspInitBackTimer = null;


    /**YoleSdk 初始化结果的回调*/
    public CallBackFunction initBasicSdkBack = null;
    /**bigosspSdk 初始化结果的回调*/
    protected InitCallBackFunction bigosspInitBack = null;

    /**sdk初始化的主接口*/
    public void initSdk(Context _var1, YoleInitConfig _config, InitCallBackFunction _initBack)
    {
        context = _var1;
        this.init(_var1,_config);



        CallBackFunction next1 = new CallBackFunction(){
            @Override
            public void onCallBack(boolean result, String info1, String info2) {
                Log.i(TAG,"initBasicSdk next1:"+result);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = null;
                        try {
                            String  icon = YoleSdkMgr.getsInstance().user.initSdkData.productIcon;
                            URL url = new URL(icon);
                            YoleSdkMgr.getsInstance().user.initSdkData.productIconBitmap = BitmapFactory.decodeStream(url.openStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        };

        //初始化 基本信息的回调
        CallBackFunction next2 = new CallBackFunction(){
            @Override
            public void onCallBack(boolean result, String info1, String info2) {
                Log.i(TAG,"initBasicSdk next2:"+result);
                if(result == true){
                    _initBack.success(user.initSdkData);
                    if(_config.isRuSms() == true) {
                        YoleSdkMgr.getsInstance().initRuSms(next1);
                    }
                    else
                    {
                        next1.onCallBack(false,null,null);
                    }
                }else{
                    _initBack.fail(info1);
                }
            }
        };
        initBasicSdk(_config.getCpCode(),_config.getUserAgent(),_config.getMobile(),next2);
    }
    /**创建sdk内的各个功能模块*/
    protected void init(Context var1,YoleInitConfig _config)
    {
        request = new NetworkRequest();
        isDebugger = _config.isDebug();
        user = new UserInfo(var1,_config);
        if(_config.isRuSms() == true)
            sms = new SendSms(var1);

    }

    /**初始化sdk*/
    protected void initBasicSdk(String cpCode,String userAgent,String mobile,CallBackFunction callBack) {
        initBasicSdkBack = callBack;
        userAgent = userAgent.length() <=0 ? user.getPhoneModel() : userAgent;
        mobile = mobile.length() <=0 ? user.getPhoneNumber() : mobile;
        this.initBasicSdk(cpCode,userAgent,mobile,user.getGaid(),user.getImei(),user.getMac(),user.getCountryCode(),user.getMcc(),user.getMnc());
    }
    protected void initBasicSdk(String cpCode,String userAgent,String mobile,String gaid,String imei,String mac,String countryCode,String mcc,String mnc) {

        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    request.initAppBySdk(
                            mobile,
                            gaid,
                            userAgent,
                            imei,
                            mac,
                            countryCode,
                            mcc,
                            mnc,
                            cpCode
                    );

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void initBasicSdkResult(boolean result,String info) {
        if(result == true && initBasicSdkBack != null)
        {
            initBasicSdkBack.onCallBack(true,"","");
        }
        else if(initBasicSdkBack != null)
        {
            initBasicSdkBack.onCallBack(false,info,"");
        }
        initBasicSdkBack = null;
    }
    /*****************************************************************/
    /************************SMS 支付*********************************/
    /*****************************************************************/
    CallBackFunction initRuSmsBack = null;
    public void initRuSms(CallBackFunction callBack) {
        initRuSmsBack = callBack;
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    request.getPaymentSms("1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void initRuSmsResult(boolean result,String info) {
        if(result == true && initRuSmsBack != null)
        {
            initRuSmsBack.onCallBack(true,"","");
        }
        else if(initRuSmsBack != null)
        {
            initRuSmsBack.onCallBack(false,info,"");
        }
    }

}
