package com.jeanboy.arch.data.net.service;

import com.jeanboy.arch.data.net.entity.AccessTokenEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by jeanboy on 2018/4/28.
 */
public interface AuthService {

    String BASE_URL = "https://github.com";

    /**
     * 获取 access token
     * POST https://github.com/login/oauth/access_token
     * {
     * client_id: xxx,
     * client_secret: xxx,
     * code: xxx,
     * }
     */
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessTokenEntity> getAccessToken(@Field("client_id") String clientID,
                                           @Field("client_secret") String clientSecret,
                                           @Field("code") String code);
}
