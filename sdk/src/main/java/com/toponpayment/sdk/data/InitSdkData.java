package com.toponpayment.sdk.data;

import android.graphics.Bitmap;

public class InitSdkData {
    public enum PayType {
        /**不可用*/
        UNAVAILABLE,

        OP_DCB,
        OP_SMS,
    }
    /**用户编码*/
    public String userCode = "";
    /**商品名称*/
    public String productName = "";
    /**商品图标*/
    public String productIcon = "";
    /**商品图标*/
    public Bitmap productIconBitmap = null;
    /**公司名称*/
    public String companyName = "";
    /**货币符号*/
    public String currencySymbol = "";
    /**是否开启广告*/
    public boolean adsOpen = false;
    /**支持小数点位数*/
    public int currencyDecimal = 0;
    /**方式*/
    public PayType payType = PayType.UNAVAILABLE;
    /**区号*/
    public String areaCode = "";
}
