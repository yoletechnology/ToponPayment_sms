package com.toponpayment.sdk.tool;

import android.util.Log;

import com.toponpayment.sdk.YoleSdkMgr;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetUtil{
    public static String TAG = "Yole_NetUtil";

    NetUtil()
    {
        Log.d(TAG, TAG);
    }
    public  static String sendPost(String url,JSONObject formBody) {
        url = "https://api.yolesdk.com/"+url;
        //创建OkHttp客户端
        OkHttpClient okHttpClient = new OkHttpClient();

        // 封装请求体
        MediaType mediaType = MediaType.parse("application/json");

        //创建请求对象
        RequestBody requestBody = RequestBody.create(mediaType, formBody.toString());

        Log.d(TAG, "appkey:"+ YoleSdkMgr.getsInstance().user.getAppkey());
        Log.d(TAG, "url:"+url);
        Log.d(TAG, "FormBody:"+formBody.toString());


        Request request = new Request.Builder()
                .url(url)
                .addHeader("appkey",YoleSdkMgr.getsInstance().user.getAppkey())
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();

        try {
            // 执行这个请求对象
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();
            Log.d(TAG, "onResponse2:"+result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public  static String sendPostQueryParameter(HttpUrl.Builder httpBuilder) {

        //创建OkHttp客户端
        OkHttpClient okHttpClient = new OkHttpClient();

        Log.d(TAG, "appkey:"+ YoleSdkMgr.getsInstance().user.getAppkey());
        Log.d(TAG, "url:"+httpBuilder.build());

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .header("appkey",YoleSdkMgr.getsInstance().user.getAppkey())
                .build();

        try {
            // 执行这个请求对象
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();
            Log.d(TAG, "onResponse2:"+result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    public  static String sendGet(String url,String formBody) {
        url = "https://api.yolesdk.com/"+url;
        //创建OkHttp客户端
        OkHttpClient okHttpClient = new OkHttpClient();

        // 封装请求体
        MediaType mediaType = MediaType.parse("application/json");

        //创建请求对象
        RequestBody requestBody = RequestBody.create(mediaType, formBody.toString());

        Log.d(TAG, "appkey:"+ YoleSdkMgr.getsInstance().user.getAppkey());
        Log.d(TAG, "url:"+url);
        Log.d(TAG, "FormBody:"+formBody.toString());

        url +="?";
        url += formBody;
        Request request = new Request.Builder()
                .get()
                .url(url)
                .addHeader("appkey",YoleSdkMgr.getsInstance().user.getAppkey())
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            // 执行这个请求对象
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();
            Log.d(TAG, "onResponse2:"+result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    public static String serializeMetadata(HashMap<String, String> metadata) throws IOException {
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(metadata);
            return baos.toString();
        }
    }
    public String toString(Set<Map.Entry<String,String>> entrySet) {
        Iterator<Map.Entry<String,String>> i = entrySet.iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Map.Entry<String,String> e = i.next();
            String key = e.getKey();
            String value = e.getValue();
            sb.append(""+key+"");
            sb.append('=');
            sb.append(""+value+"");
            if (! i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }

}
