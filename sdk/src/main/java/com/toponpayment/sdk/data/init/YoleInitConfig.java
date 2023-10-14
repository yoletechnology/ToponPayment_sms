package com.toponpayment.sdk.data.init;

import java.util.HashMap;
import java.util.Map;

public class YoleInitConfig {
    private String appId="";
    private String appKey="";
    private String cpCode="";
    private boolean debug=false;
    private  YoleInitDcbConfig dcbConfig = null;
    private  YoleInitRuSmsConfig ruSmsConfig = null;

    private final Map<String, String> extra = new HashMap();

     public YoleInitConfig() {
        this.extra.clear();
    }

    /******************************************************/
    /*******************基本信息**************************/
    /******************************************************/
    public  YoleInitConfig setAppId(String var1) {
        this.appId = var1;
        return this;
    }
    public  YoleInitConfig setAppKey(String var1) {
        this.appKey = var1;
        return this;
    }
    public  YoleInitConfig setCpCode(String var1) {
        this.cpCode = var1;
        return this;
    }
    public  YoleInitConfig setDebug(boolean var1) {
        this.debug = var1;
        return this;
    }
    public String getAppId() {
        return this.appId;
    }
    public String getAppKey() {
        return this.appKey;
    }
    public String getCpCode() {
        return this.cpCode;
    }
    public boolean isDebug() {
        return this.debug;
    }

    /******************************************************/
    /*******************dcb信息**************************/
    /******************************************************/
    public String getUserAgent() {
        if(this.isDcb() == false)
        {
            return "";
        }
        return this.dcbConfig.getUserAgent();
    }
    public String getMobile() {
        if(this.isDcb() == false)
        {
            return "";
        }
        return this.dcbConfig.getMobile();
    }
    public  boolean isDcb() {
        return this.dcbConfig != null;
    }
    public  YoleInitConfig setDcbConfig(YoleInitDcbConfig var1) {
        this.dcbConfig = var1;
        return this;
    }
    /******************************************************/
    /*******************Ru_Sms信息**************************/
    /******************************************************/
    public  boolean isRuSms() {
        return this.ruSmsConfig != null;
    }
    public  YoleInitConfig setRuSmsConfig(YoleInitRuSmsConfig var1) {
        this.ruSmsConfig = var1;
        return this;
    }




}

