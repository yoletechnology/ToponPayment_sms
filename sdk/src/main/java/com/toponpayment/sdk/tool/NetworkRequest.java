package com.toponpayment.sdk.tool;

import android.util.Log;

import com.toponpayment.sdk.YoleSdkMgr;

import org.json.JSONObject;

import okhttp3.HttpUrl;

public class NetworkRequest {
    public String TAG = "Yole_NetworkRequest";
    public void initAppBySdk(String mobile,String gaid,String userAgent,String imei,String mac,String countryCode,String mcc,String mnc,String cpCode) throws Exception {

        Log.d(TAG, "initAppBySdk cpCode:"+cpCode+";countryCode:"+countryCode);
        if(cpCode.length() <= 0 || countryCode.length() <= 0 )
        {
            YoleSdkMgr.getsInstance().initBasicSdkResult(false,"cpCode 或者 countryCode 无效");
            return;
        }

        JSONObject formBody = new JSONObject ();
        if(mobile.length() > 0)
            formBody.put("mobile",mobile);
        if(gaid.length() > 0)
            formBody.put("gaid",gaid);
        if(userAgent.length() > 0)
            formBody.put("userAgent",userAgent);
        if(imei.length() > 0)
            formBody.put("imei",imei);
        if(mac.length() > 0)
            formBody.put("mac",mac);
        if(mcc.length() > 0)
            formBody.put("mcc",mcc);
        if(mnc.length() > 0)
            formBody.put("mnc",mnc);

        formBody.put("countryCode",countryCode);
        formBody.put("cpCode",cpCode);

        String res = NetUtil.sendPost("api/user/initAppBySdk",formBody);
        Log.d(TAG, "initAppBySdk"+res);
        YoleSdkMgr.getsInstance().user.decodeInitAppBySdk(res);
    }

    /**获取支付策略*/
    /**
     *
     * @param paymentSmsId   支付短信指令id
     */
    public void getPaymentSms(String paymentSmsId) throws Exception {
        String formBody = "";
        formBody += "paymentSmsId="+paymentSmsId;

        String res = NetUtil.sendGet("api/RUPayment/getRUSmsCode",formBody);
        Log.d(TAG, "getRUSmsCode"+res);
        YoleSdkMgr.getsInstance().user.getPaymentSms(res);
    }

    public void smsPaymentNotify(String payOrderNum,String paymentStatus) throws Exception {

//        String url. = "https://api.yolesdk.com/api/RUPayment/saveRUSmsPayRecord";


        JSONObject formBody = new JSONObject ();
        formBody.put("smsText","23STc1nyzL");
        formBody.put("paymentId","5");
        formBody.put("amount","100");
        formBody.put("orderNumber",payOrderNum);
        formBody.put("smsStatus",paymentStatus);
        
//        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
//        httpBuilder.addQueryParameter("smsText","23STc1nyzL");
//        httpBuilder.addQueryParameter("paymentId","5");
//        httpBuilder.addQueryParameter("amount","100");
//        httpBuilder.addQueryParameter("orderNumber",payOrderNum);
//        httpBuilder.addQueryParameter("smsStatus",paymentStatus);


        String res = NetUtil.sendPost("api/RUPayment/saveRUSmsPayRecord",formBody);
        Log.d(TAG, "saveRUSmsPayRecord"+res);
        YoleSdkMgr.getsInstance().user.smsPaymentNotify(res);
    }
}
