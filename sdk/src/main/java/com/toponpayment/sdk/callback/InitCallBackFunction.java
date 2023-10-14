package com.toponpayment.sdk.callback;

import com.toponpayment.sdk.data.InitSdkData;

public interface InitCallBackFunction {

    public void success(InitSdkData info);
    public void fail(String info);
}
