package com.jeanboy.arch.data.net.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jeanboy on 2018/4/28.
 */
public interface GitHubWebService {

    String BASE_URL = "https://github.com";

    /**
     * 获取 GitHub 的头条
     * https://github.com/topics
     *
     * @param forceNetWork
     * @return
     */
    @GET("topics")
    Call<String> getTopics(@Header("forceNetWork") boolean forceNetWork);

    /**
     * 获取热门项目
     * https://github.com/trending
     *
     * @return
     */
    @GET("trending")
    @Headers("Cache-Control: public, max-age=86400")
    Call<String> getTrending();

    /**
     * 筛选热门项目
     * https://github.com/trending/java
     *
     * @param language
     * @param since
     * @return
     */
    @GET("trending/{language}")
    @Headers({
            "Cache-Control: public, max-age=86400",
            "forceNetWork: false"
    })
    Call<String> getTrending(@Path(value = "language", encoded = true) String language,
                             @Query("since") String since);


}
