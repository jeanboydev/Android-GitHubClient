package com.jeanboy.arch.data.net.service;


import com.jeanboy.arch.data.net.entity.UserInfoEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by jeanboy on 2018/5/3.
 */

public interface UserService {

    String BASE_URL = "https://api.github.com";

    /**
     * 获取用户个人公开信息
     * GET https://api.github.com/user
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("user")
    Call<UserInfoEntity> getUserInfo(@Header("Authorization") String accessToken);

    /**
     * 获取指定用户公开信息
     * GET https://api.github.com/users/{username}
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}")
    Call<UserInfoEntity> getUserInfo(@Header("Authorization") String accessToken,
                                     @Path("username") String username);

}
