
package com.toponpayment.sdk.data;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.toponpayment.sdk.data.init.YoleInitConfig;
import com.toponpayment.sdk.tool.PhoneInfo;
import com.toponpayment.sdk.callback.CallBackFunction;

public class UserInfoBase {
    protected static Context act;
    protected static PhoneInfo info =  null;
//    protected static PayInfo payInfo =  null;
    protected static YoleInitConfig config =  null;
    protected static String phoneNumber = "";//bigossp sdk用到的=》手机号
    protected static String payOrderNum = "";//bigossp sdk用到的=》支付的订单号
    protected static String amount = "";//bigossp sdk用到的=》价格

    protected static String smsNumber= "";//ru-sms sdk用到的=》目标手机号
    protected static String smsCode= "";//ru-sms sdk用到的=》短信内容

    protected static CallBackFunction backFunc = null;
    public static InitSdkData initSdkData = null;

    public  String getSmsNumber()
    {
        if(config.isDebug() == true)
        {
            return YoleSdkDefaultValue.Demo_SmsNumber;
        }
        return smsNumber;
    }
    public  String getSmsCode()
    {
        if(config.isDebug() == true)
        {
            return YoleSdkDefaultValue.Demo_SmsCode;
        }
        return smsCode;
    }
    public  String getCpCode()
    {
        if(config.isDebug() == true)
        {
            return YoleSdkDefaultValue.Demo_CpCode;
        }
        return config.getCpCode();
    }
    public  String getAppkey()
    {
        if(config.isDebug() == true)
        {
            return YoleSdkDefaultValue.Demo_Appkey;
        }
        return config.getAppKey();
    }
    public  String getMcc()
    {
        if(config.isDebug() == true)
        {
            return "250";
        }
        return info.mcc_sim;
    }
    public  String getMnc()
    {
        if(config.isDebug() == true)
        {
            return "99";
        }
        return info.mnc_sim;
    }
    public  String getCountryCode()
    {
        if(config.isDebug() == true)
        {
            return YoleSdkDefaultValue.Demo_CountryCode;
        }
        return info.countryCode;
    }
    public  String getImei()
    {
        return info.imei;
    }
    public  String getMac()
    {
        return info.mac;
    }
    public  String getPackageName()
    {
        return info.packageName;
    }
    public  String getAppName()
    {
        return info.appName;
    }
    public  Drawable getIcon()
    {
        return info.icon;
    }
    public  String getVersionName()
    {
        return info.VersionName;
    }
    public  String getPhoneModel()
    {
        return info.phoneModel.replace(" ","-");
    }
    public  String getGaid()
    {
        return info.gaid;
    }
    public  String getPhoneNumber()
    {
        if(config.isDebug() == true && phoneNumber.length() <= 0)
        {
            return YoleSdkDefaultValue.Demo_PhoneNumber;
        }
        return phoneNumber;
    }
    public  void setPhoneNumber(String phone)
    {
        phoneNumber = phone;
    }
    public  String getPayOrderNum()
    {
        return payOrderNum;
    }
    public  void setPayOrderNum(String orderNum)
    {
        payOrderNum = orderNum;
    }
    public  String getAmount()
    {
        return amount;
    }
    public  void setAmount(String value)
    {
        amount = value;
    }
    public  void setPayCallBack(CallBackFunction value)
    {
        backFunc = value;
    }
    public  CallBackFunction getPayCallBack()
    {
        return backFunc;
    }




}
