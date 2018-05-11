package com.jeanboy.arch.data.net.manager;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jeanboy.arch.data.net.core.NetHandler;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by jeanboy on 2017/5/17.
 */

public class NetManager {

    private static OkHttpClient client;
    private final static int CONNECT_TIMEOUT = 30;
    private final static boolean DEBUG = true;

    private static NetManager instance;

    public static NetManager getInstance() {
        if (instance == null) {
            synchronized (NetManager.class) {
                if (instance == null) {
                    instance = new NetManager();
                }
            }
        }
        return instance;
    }

    private NetManager() {
    }

    /*------------------------ 配置 Retrofit ------------------------------*/

    private Retrofit getWithGSON(String baseUrl) {
        return get(baseUrl, GsonConverterFactory.create());
    }

    private Retrofit getWithXml(String baseUrl) {
        return get(baseUrl, SimpleXmlConverterFactory.create());
    }

    private Retrofit get(String baseUrl, Converter.Factory factory) {
        if (client == null) {
            client = getOkHttpClient();
        }

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.createForJSON())//Rxjava
                .client(client).build();
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .retryOnConnectionFailure(true)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }

    private <P, T> void request(NetHandler<P, T> handler,
                                RequestParams<P> request, RequestCallback<ResponseData<T>> callback) {
        handler.request(request, callback);
    }

    private <P, T> void requestSync(NetHandler<P, T> handler,
                                    RequestParams<P> request, RequestCallback<ResponseData<T>> callback) {
        handler.requestSync(request, callback);
    }

    /*------------------------ 配置 Retrofit 结束 ------------------------------*/

    public <T> void request(RequestParams<Call<T>> request, RequestCallback<ResponseData<T>> callback) {
        request(new OkHttpHandler<>(), request, callback);
    }

    public <T> void requestSync(RequestParams<Call<T>> request, RequestCallback<ResponseData<T>> callback) {
        requestSync(new OkHttpHandler<>(), request, callback);
    }

    public <T> T create(String baseUrl, Class<T> clazz) {
        return getWithGSON(baseUrl).create(clazz);
    }

    public <T> T createForXml(String baseUrl, Class<T> clazz) {
        return getWithXml(baseUrl).create(clazz);
    }
}
