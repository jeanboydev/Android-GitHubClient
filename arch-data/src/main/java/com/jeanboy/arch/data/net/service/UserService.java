package com.jeanboy.arch.data.net.service;


import com.jeanboy.arch.data.net.entity.UserInfoEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    /**
     * 获取粉丝
     * GET https://api.github.com/users/{username}/followers?page=2
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/followers")
    Call<String> getFollowers(@Header("Authorization") String accessToken,
                              @Path("username") long username,
                              @Query("page") int page);

    /**
     * 获取关注的人
     * GET https://api.github.com/users/{username}/following?page=2
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/following")
    Call<String> getFollowing(@Header("Authorization") String accessToken,
                              @Path("username") long username,
                              @Query("page") int page);

    /**
     * 获取 Star 的项目
     * GET https://api.github.com/users/{username}/starred?page=2
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/starred")
    Call<String> getStarred(@Header("Authorization") String accessToken,
                            @Path("username") long username,
                            @Query("page") int page);

    /**
     * 获取订阅的项目
     * GET https://api.github.com/users/{username}/subscriptions?page=2
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/subscriptions")
    Call<String> getSubscriptions(@Header("Authorization") String accessToken,
                                  @Path("username") long username,
                                  @Query("page") int page);

    /**
     * 获取所在的组织
     * GET https://api.github.com/users/{username}/orgs?page=2
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/orgs")
    Call<String> getOrganizations(@Header("Authorization") String accessToken,
                                  @Path("username") long username,
                                  @Query("page") int page);


    /**
     * 获取项目列表
     * GET https://api.github.com/users/{username}/repos?page=2
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/repos")
    Call<String> getRepos(@Header("Authorization") String accessToken,
                          @Path("username") long username,
                          @Query("page") int page);
}
