package com.jeanboy.arch.data.net.service;

import com.jeanboy.arch.data.net.entity.BranchEntity;
import com.jeanboy.arch.data.net.entity.FileEntity;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.RepositoryEventEntity;
import com.jeanboy.arch.data.net.entity.TagEntity;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by jeanboy on 2018/5/7.
 */
public interface ReposService {

    String BASE_URL = "https://api.github.com";

    /**
     * 获取仓库详情
     * GET https://api.github.com/repos/{name}
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("repos/{username}/{repos}")
    Call<RepositoryEntity> getReposInfo(
            @Header("Authorization") String accessToken,
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
    Call<List<RepositoryEntity>> getRepos(
            @Header("Authorization") String accessToken,
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
    Call<List<RepositoryEntity>> getStarredRepos(
            @Header("Authorization") String accessToken,
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
    Call<List<RepositoryEntity>> getUserRepos(
            @Header("Authorization") String accessToken,
            @Query("page") int page,
            @Query("type") String type,
            @Query("sort") String sort,
            @Query("direction") String direction);


    /**
     * 查看是否star该仓库
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Accept: application/json")
    @GET("user/starred/{owner}/{repo}")
    Call<Response<ResponseBody>> checkRepoStarred(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo);

    /**
     * Star指定的仓库
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Accept: application/json")
    @PUT("user/starred/{owner}/{repo}")
    Call<Response<ResponseBody>> starRepo(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo);

    /**
     * 取消Star指定的仓库
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Accept: application/json")
    @DELETE("user/starred/{owner}/{repo}")
    Call<Response<ResponseBody>> unstarRepo(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo);

    /**
     * 查看是否watch指定的仓库
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Accept: application/json")
    @GET("user/subscriptions/{owner}/{repo}")
    Call<Response<ResponseBody>> checkRepoWatched(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo);


    /**
     * Watch指定的仓库
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Accept: application/json")
    @PUT("user/subscriptions/{owner}/{repo}")
    Call<Response<ResponseBody>> watchRepo(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo);

    /**
     * 取消Watch指定的仓库
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Accept: application/json")
    @DELETE("user/subscriptions/{owner}/{repo}")
    Call<Response<ResponseBody>> unwatchRepo(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo);

    /**
     * 获取指定地址的文件内容,MARKDOWN的文件使用该请求方式
     *
     * @param accessToken
     * @param url
     * @return
     */
    @GET
    @Headers("Accept: application/vnd.github.html")
    Call<String> getFileAsHtmlStream(
            @Header("Authorization") String accessToken,
            @Url String url);

    /**
     * 获取指定地址的文件内容
     *
     * @param accessToken
     * @param url
     * @return
     */
    @GET
    @Headers("Accept: application/vnd.github.VERSION.raw")
    Call<String> getFileAsStream(
            @Header("Authorization") String accessToken,
            @Url String url);


    /**
     * 获取指定路径下的文件列表
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @param path
     * @param branch
     * @return
     */
    @Headers("Accept: application/json")
    @GET("repos/{owner}/{repo}/contents/{path}")
    Call<List<FileEntity>> getRepoFiles(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Path(value = "path", encoded = true) String path,
            @Query("ref") String branch);

    /**
     * 获取指定仓库的分支列表
     *
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Accept: application/json")
    @GET("repos/{owner}/{repo}/branches")
    Call<List<BranchEntity>> getBranches(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo
    );

    /**
     * 获取指定仓库的标签列表
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Accept: application/json")
    @GET("repos/{owner}/{repo}/tags")
    Call<List<TagEntity>> getTags(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo
    );

    /**
     * 获取指定仓库star的用户列表
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @param page
     * @return
     */
    @Headers("Accept: application/json")
    @GET("repos/{owner}/{repo}/stargazers")
    Call<List<UserInfoEntity>> getStargazers(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Query("page") int page
    );

    /**
     * 获取指定仓库watch的用户列表
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @param page
     * @return
     */
    @Headers("Accept: application/json")
    @GET("repos/{owner}/{repo}/subscribers")
    Call<List<UserInfoEntity>> getWatchers(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Query("page") int page
    );

    /**
     * fork指定的仓库
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @return
     */
    @Headers("Accept: application/json")
    @POST("repos/{owner}/{repo}/forks")
    Call<RepositoryEntity> createFork(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo
    );

    /**
     * 获取指定仓库的Fork列表
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @param page
     * @return
     */
    @Headers("Accept: application/json")
    @GET("repos/{owner}/{repo}/forks")
    Call<List<RepositoryEntity>> getForks(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Query("page") int page
    );

    /**
     * List public events for a network of repositories
     * <p>
     * https://api.github.com/networks/coolspan/IOSRadarView/events
     *
     * @param accessToken
     * @param owner
     * @param repo
     * @param page
     * @return
     */
    @Headers("Accept: application/json")
    @GET("networks/{owner}/{repo}/events")
    Call<List<RepositoryEventEntity>> getRepoEvent(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Query("page") int page
    );


}
