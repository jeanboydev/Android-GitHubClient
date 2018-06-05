package com.jeanboy.arch.data.net.service;

import com.jeanboy.arch.data.net.entity.notifications.NotificationEntity;
import com.jeanboy.arch.data.repository.params.MarkNotificationReadRequestParams;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 乔晓松 on 2018/6/1 18:30
 */
public interface NotificationService {

    String BASE_URL = "https://api.github.com";


    /**
     * 获取个人的通知消息
     * https://developer.github.com/v3/activity/notifications/#list-your-notifications
     *
     * @param accessToken
     * @param all
     * @param participating
     * @return
     */
    @GET("notifications")
    @Headers("Accept: application/json")
    Call<List<NotificationEntity>> getMyNotifications(@Header("Authorization") String accessToken,
                                                      @Query("all") boolean all,
                                                      @Query("participating") boolean participating);


    /**
     * 标记指定通知消息为已读
     *
     * @param accessToken
     * @param threadId
     * @return
     */
    @PATCH("notifications/threads/{threadId}")
    @Headers("Accept: application/json")
    Call<Response<ResponseBody>> markNotificationAsRead(@Header("Authorization") String accessToken,
                                                        @Path("threadId") String threadId);

    /**
     * 标记所有的通知消息为已读
     *
     * @param accessToken
     * @param body
     * @return
     */
    @PUT("notifications")
    @Headers("Accept: application/json")
    Call<Response<ResponseBody>> markAllNotificationAsRead(@Header("Authorization") String accessToken,
                                                           @Body MarkNotificationReadRequestParams body);


    /**
     * 标记指定用户和仓库的通知消息为已读
     *
     * @param accessToken
     * @param body
     * @param owner
     * @param repo
     * @return
     */
    @PUT("repos/{owner}/{repo}/notifications")
    @Headers("Accept: application/json")
    Call<Response<ResponseBody>> markRepoNotificationAsRead(@Header("Authorization") String accessToken,
                                                            @Body MarkNotificationReadRequestParams body,
                                                            @Path("owner") String owner,
                                                            @Path("repo") String repo);


}
