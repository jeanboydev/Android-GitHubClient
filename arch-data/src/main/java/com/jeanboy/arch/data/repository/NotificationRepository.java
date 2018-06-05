package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jeanboy.arch.data.cache.database.model.notifications.NotificationModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.notifications.NotificationEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.NotificationService;
import com.jeanboy.arch.data.repository.mapper.NotificationMapper;
import com.jeanboy.arch.data.repository.params.MarkNotificationReadRequestParams;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by 乔晓松 on 2018/6/1 18:34
 */
public class NotificationRepository {

    private AppDatabase database;
    private NotificationService notificationService;

    public NotificationRepository() {
        database = DBManager.getInstance().getDatabase();
        notificationService = NetManager.getInstance().createForJSON(NotificationService.BASE_URL, NotificationService.class);
    }

    public LiveData<List<NotificationModel>> getMyNotifications(String token, boolean all, boolean participating) {
        MutableLiveData<List<NotificationModel>> liveData = new MutableLiveData<>();
        Call<List<NotificationEntity>> call = notificationService.getMyNotifications("token " + token, all, participating);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<NotificationEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<NotificationEntity>> response) {
                        List<NotificationEntity> body = response.getBody();
                        List<NotificationModel> notificationModels = new NotificationMapper().transform(body);
                        liveData.setValue(notificationModels);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getMyNotifications", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<Boolean> markNotificationAsRead(String token, String threadId) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Response<ResponseBody>> call = notificationService.markNotificationAsRead("token " + token, threadId);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Response<ResponseBody>>>() {
                    @Override
                    public void onSuccess(ResponseData<Response<ResponseBody>> response) {
                        Response<ResponseBody> body = response.getBody();
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("markNotificationAsRead", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

    public LiveData<Boolean> markAllNotificationAsRead(String token) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Response<ResponseBody>> call = notificationService.markAllNotificationAsRead("token " + token, new MarkNotificationReadRequestParams());
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Response<ResponseBody>>>() {
                    @Override
                    public void onSuccess(ResponseData<Response<ResponseBody>> response) {
                        Response<ResponseBody> body = response.getBody();
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("markAllNotifyAsRead", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

    public LiveData<Boolean> markRepoNotificationAsRead(String token, String owner, String repo) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Response<ResponseBody>> call = notificationService.markRepoNotificationAsRead("token " + token, new MarkNotificationReadRequestParams(), owner, repo);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Response<ResponseBody>>>() {
                    @Override
                    public void onSuccess(ResponseData<Response<ResponseBody>> response) {
                        Response<ResponseBody> body = response.getBody();
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("markRepoNotifyAsRead", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }
}
