package com.atguigu.p2p0224.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/22.
 */

public class HttpUtils {

    private AsyncHttpClient httpClient ;

    private HttpUtils(){
        httpClient = new AsyncHttpClient();
    }

    private static HttpUtils httpUtils = new HttpUtils();
    public static HttpUtils getInstance(){
        return httpUtils;
    }


    private OnHttpClientListener httpClientListener;

    /**
     * get联网请求
     * @param url
     * @param httpClientListener
     */
    public void get(String url, final OnHttpClientListener httpClientListener){
        this.httpClientListener = httpClientListener;
        httpClient.get(url,handler);
    }

    AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler(){
        @Override
        public void onSuccess(int statusCode, String content) {
            super.onSuccess(statusCode, content);

            if (httpClientListener != null){
                httpClientListener.onSuccess(content);
            }
        }

        @Override
        public void onFailure(Throwable error, String content) {
            super.onFailure(error, content);
            if (httpClientListener != null){
                httpClientListener.onFailure(content);
            }
        }

    };

    /**
     * post连网请求
     */
    public void post(String url, Map<String,String> map,
                     final OnHttpClientListener onHttpClientListener){

        this.httpClientListener = onHttpClientListener;

        RequestParams params = new RequestParams();

        httpClient.post(url,params,handler);
    }





    public interface OnHttpClientListener{
        void onSuccess(String json);
        void onFailure(String message);
    }

}
