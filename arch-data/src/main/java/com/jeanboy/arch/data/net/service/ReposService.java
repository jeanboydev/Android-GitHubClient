package com.jeanboy.arch.data.net.service;

import com.jeanboy.arch.data.net.entity.RepositoryEntity;

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
public interface ReposService {

    String BASE_URL = "https://api.github.com";

    /**
     * 项目详情
     * GET https://api.github.com/repos/{name}
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("repos/{username}/{repos}")
    Call<RepositoryEntity> getReposInfo(@Header("Authorization") String accessToken,
                                        @Path("username") String username,
                                        @Path("repos") String repos);

    /**
     * 获取指定用户的所有仓库源
     * GET https://api.github.com/users/jeanboydev/repos
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/repos")
    Call<List<RepositoryEntity>> getRepos(@Header("Authorization") String accessToken,
                                          @Path("username") String username,
                                          @Query("page") int page);

    /**
     * 获取指定用户的Started的仓库
     * GET https://api.github.com/users/jeanboydev/starred
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     * <p>
     * sort: created, updated, pushed, full_name
     * direction: desc, asc
     * <p>
     * 每页30条数据
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/starred")
    Call<List<RepositoryEntity>> getStarredRepos(@Header("Authorization") String accessToken,
                                                 @Path("username") String username,
                                                 @Query("page") int page,
                                                 @Query("sort") String sort,
                                                 @Query("direction") String direction);

    /**
     * 获取用户自己的仓库源
     * GET https://api.github.com/user/repos
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     * <p>
     * type：all, owner, public, private, member
     * sort: created, updated, pushed, full_name
     * direction: desc, asc
     * <p>
     * 每页30条数据
     */
    @Headers("Accept: application/json")
    @GET("user/repos")
    Call<List<RepositoryEntity>> getUserRepos(@Header("Authorization") String accessToken,
                                              @Query("page") int page,
                                              @Query("type") String type,
                                              @Query("sort") String sort,
                                              @Query("direction") String direction);

}
