package com.jeanboy.arch.data.net.service;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface FileService {

    String BASE_URL = "https://api.github.com";

    @GET
    @Headers("Accept: application/vnd.github.html")
    Call<String> getFileAsHtmlStream(@Header("forceNetWork") boolean forceNetWork, @Url String url);

    @GET
    @Headers("Accept: application/vnd.github.VERSION.raw")
    Call<String> getFileAsStream(@Header("forceNetWork") boolean forceNetWork, @Url String url);

}
