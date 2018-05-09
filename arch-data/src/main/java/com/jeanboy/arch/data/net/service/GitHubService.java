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
public interface GitHubService {

    String BASE_URL = "https://api.github.com";

    /**
     * 跟随者
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
     * 跟随的人
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
     * 组织
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
     * 项目列表
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

    /**
     * GET https://api.github.com/users/{username}/events?page=2
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/events")
    Call<String> getEvents(@Header("Authorization") String accessToken,
                           @Path("username") long username,
                           @Query("page") int page);

}
