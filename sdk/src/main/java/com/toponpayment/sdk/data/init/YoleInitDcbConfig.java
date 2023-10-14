package com.toponpayment.sdk.data.init;

public class YoleInitDcbConfig {
    private String userAgent="";
    private String mobile="";
    public YoleInitDcbConfig() {
    }
    public String getUserAgent() {
        return this.userAgent;
    }
    public String getMobile() {
        return this.mobile;
    }
    public YoleInitDcbConfig setUserAgent(String val) {
        this.userAgent = val;
        return this;
    }
    public YoleInitDcbConfig setMobile(String val) {
        this.mobile = val;
        return this;
    }
}
