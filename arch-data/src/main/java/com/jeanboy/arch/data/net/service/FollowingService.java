package com.jeanboy.arch.data.net.service;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by 乔晓松 on 2018/5/11 11:31
 */
public interface FollowingService {

    String BASE_URL = "https://api.github.com";

    /**
     * 查看我是否关注了该用户
     * GET https://api.github.com/user/following/{username}
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("user/following/{username}")
    Call<Boolean> checkFollowing(@Header("Authorization") String accessToken,
                                 @Path("username") String username);

    /**
     * 检查一个用户是否关注其他用户
     * GET https://api.github.com/users/{username}/following/{targetUsername}
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/following/{targetUsername}")
    Call<Boolean> checkFollowing(@Header("Authorization") String accessToken,
                                 @Path("username") String username,
                                 @Path("targetUsername") String targetUsername);

    /**
     * 取消关注
     * GET https://api.github.com/user/following/{username}
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @DELETE("user/following/{username}")
    Call<Boolean> unfollowUser(@Header("Authorization") String accessToken,
                                 @Path("username") String username);


    /**
     * 添加关注
     * GET https://api.github.com/user/following/{username}
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @PUT("user/following/{username}")
    Call<Boolean> followUser(@Header("Authorization") String accessToken,
                               @Path("username") String username);

}
