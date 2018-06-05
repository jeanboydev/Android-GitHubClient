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

    public MutableLiveData<List<RepositoryEntity>> getReposList(String accessToken, String username, int page) {
        MutableLiveData<List<RepositoryEntity>> liveData = new MutableLiveData<>();
        Call<List<RepositoryEntity>> call = reposService.getReposList("token " + accessToken, username, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<RepositoryEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<RepositoryEntity>> response) {
                        List<RepositoryEntity> body = response.getBody();
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
                        Log.e("==========", "====onSuccess====");
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

    public LiveData<List<RepositoryModel>> getStarredRepos(String accessToken, String username, int page, String sort, String direction) {
        Log.d("getStarredRepos", "accessToken: " + accessToken);
        MutableLiveData<List<RepositoryModel>> liveData = new MutableLiveData<>();
        Call<List<RepositoryEntity>> call = reposService.getStarredRepos(accessToken, username, page, sort, direction);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<RepositoryEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<RepositoryEntity>> response) {
                        List<RepositoryEntity> body = response.getBody();
                        List<RepositoryModel> repositoryModelList = new RepositoryMapper().transform(body);
                        liveData.setValue(repositoryModelList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<RepositoryModel>> getUserRepos(String accessToken, int page, String type, String sort, String direction) {
        MutableLiveData<List<RepositoryModel>> liveData = new MutableLiveData<>();
        Call<List<RepositoryEntity>> call = reposService.getUserRepos(accessToken, page, type, sort, direction);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<RepositoryEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<RepositoryEntity>> response) {
                        List<RepositoryEntity> body = response.getBody();
                        List<RepositoryModel> repositoryModelList = new RepositoryMapper().transform(body);
                        liveData.setValue(repositoryModelList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getUserRepos", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }
}
