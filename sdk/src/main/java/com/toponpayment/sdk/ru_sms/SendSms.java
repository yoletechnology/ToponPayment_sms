package com.toponpayment.sdk.ru_sms;

import static android.text.TextUtils.isEmpty;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.toponpayment.sdk.YoleSdkMgr;
import com.toponpayment.sdk.callback.CallBackFunction;

import java.util.ArrayList;

public class SendSms {
    private String TAG = "Yole_SendSms";
    private Activity var =  null;
    private static final int SEND_SMS = 100;
    private static final String SMS_SENT_ACTION = "SMS_SENT";

    public SendSms (Context  _context)
    {
//        context = _context;

    }
    public void smsRequest(Activity var1)
    {
        var = var1;
        this.requestPermission(var1);
    }

    private BroadcastReceiver smsSentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG,"MyBroadcastReceiver"+ "Received broadcast: " + intent.getAction());
            Log.i(TAG,"code"+ String.valueOf(getResultCode()));
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    // 短信发送成功
                    Log.i(TAG,"signal"+"成功");
                    smeResult(true,"短信发送成功yyyyy","");
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    // 短信发送失败
                    Log.i(TAG,"signal"+"失败");
                    smeResult(false,"短信发送失败","");
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    // 手机没有信号，无法发送短信
                    Log.i(TAG,"signal"+"失败");
                    smeResult(false,"手机无信号，无法发送短信","");
                    break;
                default:
                    // 更多其他错误码可根据需要进行处理
                    Log.i(TAG,"signal"+"更多其他错误码可根据需要进行处理");
                    smeResult(false,"更多其他错误码可根据需要进行处理","");
                    break;
            }
        }
    };

    private void requestPermission(Activity var1) {
        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission( var1, Manifest.permission.CALL_PHONE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED && var1 != null) {
                Log.i(TAG,"申请权限");
                ActivityCompat.requestPermissions( var1, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS);
                return;
            } else {

                //已有权限
                Log.i(TAG,"已有权限");
            }
        } else {
            //API 版本在23以下
            Log.i(TAG,"API 版本在23以下");
        }
    }

    //发送短信
    @SuppressLint("Range")
    public void sendSMSS(String content, String phone) {

        Log.e(TAG,"手机:"+phone+";content:"+content);
        var.registerReceiver(smsSentReceiver, new IntentFilter(SMS_SENT_ACTION));

        if (!isEmpty(content) && !isEmpty(phone)) {
            SmsManager manager = SmsManager.getDefault();

            Intent sentIntent = new Intent(SMS_SENT_ACTION);
            PendingIntent sentPendingIntent = PendingIntent.getBroadcast(this.var, 0, sentIntent, PendingIntent.FLAG_IMMUTABLE);

            ArrayList<String> strings = manager.divideMessage(content);
            for (int i = 0; i < strings.size(); i++) {
                boolean isMessageSent = false;
                try {
                    manager.sendTextMessage(phone, null, content, sentPendingIntent, null);
                    isMessageSent = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (isMessageSent) {
                    Log.i(TAG,"发送状态"+ "成功");
                    // 短信发送成功
                } else {
                    // 短信发送失败
                    Log.i(TAG,"发送状态"+ "失败");
//                    smeResult(false,"发送状态"+ "失败","");
//                    return;
                }
            }
        } else {
            smeResult(false,"手机号或内容不能为空","");
            return;
        }
    }
    public void smeResult(boolean data,String info,String billingNumber)
    {
        Log.i(TAG,info);
        YoleSdkMgr.getsInstance().smsPaymentNotify(data);
//        Toast.makeText(var,info, Toast.LENGTH_SHORT).show();
        YoleSdkMgr.getsInstance().user.getPayCallBack().onCallBack(data,info,billingNumber);
    }



}