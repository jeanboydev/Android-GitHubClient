package com.jeanboy.arch.data.net.service;

import com.jeanboy.arch.data.net.entity.ReceivedEventEntity;
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
}
