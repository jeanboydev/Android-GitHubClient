package com.jeanboy.arch.data.net.service;

import com.jeanboy.arch.data.net.entity.received.OrganizationEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 乔晓松 on 2018/5/10 16:46
 */
public interface OrganizationService {

    String BASE_URL = "https://api.github.com";

    /**
     * 获取指定用户下的所有组织
     * GET https://api.github.com/users/jeanboydev/orgs
     * HEAD{
     * Authorization: token OAUTH-TOKEN
     * }
     */
    @Headers("Accept: application/json")
    @GET("users/{username}/orgs")
    Call<List<OrganizationEntity>> getRepos(@Header("Authorization") String accessToken,
                                            @Path("username") String username,
                                            @Query("page") int page);

}
