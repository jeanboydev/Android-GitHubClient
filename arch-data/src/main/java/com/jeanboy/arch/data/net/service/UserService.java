package com.jeanboy.arch.data.net.service;


import com.jeanboy.arch.data.net.entity.UserEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jeanboy on 2017/7/27.
 */

public interface UserService {

    String BASE_URL = "http://www.xxx.com";

    /**
     * http://www.xxx.com
     * POST {
     * username:xxx,
     * password:xxx
     * }
     *
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("/user")
    Call<UserEntity> getToken(@Field("username") String username, @Field("password") String password);

    /**
     * http://www.xxx.com/user?refreshToken=xxx
     *
     * @param refreshToken
     * @return
     */
    @GET("/user")
    Call<UserEntity> refreshToken(@Query("refreshToken") String refreshToken);

    /**
     * http://www.xxx.com/user/id
     * GET
     * Header {
     * Authorization : accessToken
     * }
     *
     * @param accessToken
     * @param userId
     * @return
     */
    @GET("/user/{id}")
    Call<UserEntity> getInfo(@Header("Authorization") String accessToken, @Path("id") String userId);

    /**
     * http://www.xxx.com/user/id/friend?skip=xxx&limit=xxx
     * GET
     * Header {
     * Authorization : accessToken
     * }
     *
     * @param accessToken
     * @param userId
     * @param skip
     * @param limit
     * @return
     */
    @GET("/user/{id}/friend")
    Call<List<UserEntity>> getFriendList(@Header("Authorization") String accessToken, @Path("id") String userId, @Query("skip") int
            skip,
                                         @Query("limit") int limit);
}
