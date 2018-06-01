package com.jeanboy.arch.data.net.service;

import com.jeanboy.arch.data.net.entity.IssueEntity;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.SearchWrapperEntity;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by 乔晓松 on 2018/6/1 14:40
 */
public interface SearchService {

    String BASE_URL = "https://api.github.com";

    /**
     * 搜索用户
     * https://api.github.com/search/users?q=Ray&sort=followers&order=desc
     *
     * @param accessToken
     * @return
     */
    @GET("search/users")
    Call<SearchWrapperEntity<UserInfoEntity>> searchUsers(@Header("Authorization") String accessToken,
                                                          @Query(value = "q", encoded = true) String query,
                                                          @Query("sort") String sort,
                                                          @Query("order") String order,
                                                          @Query("page") int page);


    /**
     * 搜索仓库
     * https://api.github.com/search/users?q=filedownload&sort=followers&order=desc
     *
     * @param accessToken
     * @return
     */
    @GET("search/repositories")
    Call<SearchWrapperEntity<RepositoryEntity>> searchRepos(@Header("Authorization") String accessToken,
                                                            @Query(value = "q", encoded = true) String query,
                                                            @Query("sort") String sort,
                                                            @Query("order") String order,
                                                            @Query("page") int page);

    /**
     * 搜索Issues
     * https://api.github.com/search/issues?sort=created&page=1&q=user:ThirtyDegreesRay+state:open&order=desc
     *
     * @param accessToken
     * @param query
     * @param sort
     * @param order
     * @param page
     * @return
     */
    @GET("search/issues")
    @Headers("Accept: application/vnd.github.html,application/vnd.github.VERSION.raw")
    Call<SearchWrapperEntity<IssueEntity>> searchIssues(@Header("Authorization") String accessToken,
                                                        @Query(value = "q", encoded = true) String query,
                                                        @Query("sort") String sort,
                                                        @Query("order") String order,
                                                        @Query("page") int page);
}
