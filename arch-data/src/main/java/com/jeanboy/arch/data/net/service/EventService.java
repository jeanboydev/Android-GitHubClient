package com.jeanboy.arch.data.net.service;

import com.jeanboy.arch.data.net.entity.ReceivedEventEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jeanboy on 2018/5/7.
 */
public interface EventService {

    String BASE_URL = "https://api.github.com";

    /**
     * 动态列表
     * GET https://api.github.com/users/{username}/received_events?page=2
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/received_events")
    Call<List<ReceivedEventEntity>> getReceivedEvents(@Header("Authorization") String accessToken,
                                                      @Path("username") String username,
                                                      @Query("page") int page);
}
