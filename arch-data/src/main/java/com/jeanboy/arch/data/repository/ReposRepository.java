package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.FileService;
import com.jeanboy.arch.data.net.service.ReposService;
import com.jeanboy.arch.data.repository.mapper.RepositoryMapper;

import java.util.List;

import retrofit2.Call;

/**
 * Created by jeanboy on 2018/5/7.
 */
public class ReposRepository {

    private AppDatabase database;
    private ReposService reposService;
    private FileService fileService;

    public ReposRepository() {
        database = DBManager.getInstance().getDatabase();
        reposService = NetManager.getInstance().createForJSON(ReposService.BASE_URL, ReposService.class);
        fileService = NetManager.getInstance().create(FileService.BASE_URL, FileService.class);
    }

    public LiveData<RepositoryEntity> getReposInfo(String accessToken, String username, String repos) {
        MutableLiveData<RepositoryEntity> liveData = new MutableLiveData<>();
        Call<RepositoryEntity> call = reposService.getReposInfo("token " + accessToken, username, repos);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<RepositoryEntity>>() {
                    @Override
                    public void onSuccess(ResponseData<RepositoryEntity> response) {
                        RepositoryEntity body = response.getBody();
                        liveData.setValue(body);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<String> getReadMeHTML(String url) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        Call<String> call = fileService.getFileAsHtmlStream(false, url);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<String>>() {
                    @Override
                    public void onSuccess(ResponseData<String> response) {
                        Log.e("==========","====onSuccess====");
                        String body = response.getBody();
                        liveData.setValue(body);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }
}
