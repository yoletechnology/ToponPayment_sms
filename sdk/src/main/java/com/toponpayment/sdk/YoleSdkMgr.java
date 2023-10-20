package com.toponpayment.sdk;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.toponpayment.sdk.data.InitSdkData;
import com.toponpayment.sdk.callback.CallBackFunction;

public class YoleSdkMgr extends YoleSdkBase{

    private String TAG = "Yole_YoleSdkMgr";
    private static  YoleSdkMgr _instance = null;
    public String ruPayOrderNum = "";

    public static YoleSdkMgr getsInstance() {
        if(YoleSdkMgr._instance == null)
        {
            YoleSdkMgr._instance = new YoleSdkMgr();
        }
        return YoleSdkMgr._instance;
    }
    private YoleSdkMgr() {
        Log.e(TAG,"YoleSdkMgr");
    }

    /*****************************************************************/
    /************************SMS 支付*********************************/
    /*****************************************************************/
    /**sms模块的权限注册*/
    public void  smsRequest(Activity var1) {

        sms.smsRequest(var1);
    }
    /**sms支付开始 设置回调，显示loading界面***/
    public void  smsStartPay(Activity var1,String _payOrderNum,CallBackFunction callBack) {

        ruPayOrderNum = _payOrderNum;
        LoadingDialog.getInstance(var1).show();//显示
        user.setPayCallBack(new CallBackFunction(){
            @Override
            public void onCallBack(boolean data, String info, String billingNumber) {
                LoadingDialog.getInstance(var1).hide();//显示
                callBack.onCallBack(data,info,billingNumber);

            }
        });

        this.paySdkStartPay();
    }

    /**sms支付开始 发送短信功能***/
    private void paySdkStartPay()
    {
        sms.sendSMSS(user.getSmsCode(),user.getSmsNumber());
    }
    /**sms支付完成 同步服务器结果***/
    public void smsPaymentNotify(boolean  paymentStatus)
    {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    request.smsPaymentNotify(ruPayOrderNum,paymentStatus == true ? "SUCCESSFUL" : "FAILED");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
