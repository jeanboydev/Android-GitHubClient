package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.ReposService;

import retrofit2.Call;

/**
 * Created by jeanboy on 2018/5/7.
 */
public class ReposRepository {

    private AppDatabase database;
    private ReposService reposService;

    public ReposRepository() {
        database = DBManager.getInstance().getDatabase();
        reposService = NetManager.getInstance().create(ReposService.BASE_URL, ReposService.class);
    }

    public LiveData<RepositoryEntity> getReposInfo(String name) {
        MutableLiveData<RepositoryEntity> liveData = new MutableLiveData<>();
        Call<RepositoryEntity> call = reposService.getReposInfo(name);
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
}
