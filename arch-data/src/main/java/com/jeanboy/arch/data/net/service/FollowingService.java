package com.jeanboy.arch.data.net.service;

import com.jeanboy.arch.data.net.entity.UserInfoEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 乔晓松 on 2018/5/11 11:31
 */
public interface FollowingService {

    String BASE_URL = "https://api.github.com";

    /**
     *
     * <p>
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
     *
     * 取消关注
     * DELETE https://api.github.com/user/following/{username}
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @DELETE("user/following/{username}")
    Call<Boolean> unfollowUser(@Header("Authorization") String accessToken,
                               @Path("username") String username);


    /**
     *
     * 添加关注
     * PUT https://api.github.com/user/following/{username}
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @PUT("user/following/{username}")
    Call<Boolean> followUser(@Header("Authorization") String accessToken,
                             @Path("username") String username);

    /**
     * 获取指定用户关注的人
     * PUT https://api.github.com/users/{username}/following
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/following")
    Call<List<UserInfoEntity>> getFollowing(@Header("Authorization") String accessToken,
                                            @Path("username") String username,
                                            @Query("page") int page);

    /**
     * 获取指定用户的粉丝
     * PUT https://api.github.com/users/{username}/followers
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/followers")
    Call<List<UserInfoEntity>> getFollowers(@Header("Authorization") String accessToken,
                                            @Path("username") String username,
                                            @Query("page") int page);

}
