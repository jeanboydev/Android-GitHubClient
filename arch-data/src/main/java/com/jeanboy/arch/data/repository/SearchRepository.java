package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jeanboy.arch.data.cache.database.model.IssueModel;
import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.IssueEntity;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.SearchWrapperEntity;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.SearchService;
import com.jeanboy.arch.data.repository.mapper.IssueMapper;
import com.jeanboy.arch.data.repository.mapper.RepositoryMapper;
import com.jeanboy.arch.data.repository.mapper.UserInfoMapper;

import java.util.List;

import retrofit2.Call;

/**
 * Created by 乔晓松 on 2018/6/1 14:44
 */
public class SearchRepository {

    private AppDatabase database;
    private SearchService searchService;

    public SearchRepository() {
        database = DBManager.getInstance().getDatabase();
        searchService = NetManager.getInstance().createForJSON(SearchService.BASE_URL, SearchService.class);
    }

    /**
     * 搜索用户
     *
     * @param accessToken
     * @param query
     * @param sort
     * @param order
     * @param page
     * @return
     */
    public LiveData<List<UserInfoModel>> searchUsers(String accessToken, String query, String sort, String order, int page) {
        MutableLiveData<List<UserInfoModel>> liveData = new MutableLiveData<>();
        Call<SearchWrapperEntity<UserInfoEntity>> call = searchService.searchUsers("token " + accessToken, query, sort, order, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<SearchWrapperEntity<UserInfoEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<SearchWrapperEntity<UserInfoEntity>> response) {
                        SearchWrapperEntity<UserInfoEntity> body = response.getBody();
                        List<UserInfoModel> userInfoModelList = new UserInfoMapper().transform(body.getItems());
                        liveData.setValue(userInfoModelList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("SearchRe searchUsers", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    /**
     * 搜索仓库
     *
     * @param accessToken
     * @param query
     * @param sort
     * @param order
     * @param page
     * @return
     */
    public LiveData<List<RepositoryModel>> searchRepos(String accessToken, String query, String sort, String order, int page) {
        MutableLiveData<List<RepositoryModel>> liveData = new MutableLiveData<>();
        Call<SearchWrapperEntity<RepositoryEntity>> call = searchService.searchRepos("token " + accessToken, query, sort, order, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<SearchWrapperEntity<RepositoryEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<SearchWrapperEntity<RepositoryEntity>> response) {
                        SearchWrapperEntity<RepositoryEntity> body = response.getBody();
                        List<RepositoryModel> repositoryModelList = new RepositoryMapper().transform(body.getItems());
                        liveData.setValue(repositoryModelList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("SearchRe searchRepos", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<IssueModel>> searchIssues(String accessToken, String query, String sort, String order, int page) {
        MutableLiveData<List<IssueModel>> liveData = new MutableLiveData<>();
        Call<SearchWrapperEntity<IssueEntity>> call = searchService.searchIssues("token " + accessToken, query, sort, order, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<SearchWrapperEntity<IssueEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<SearchWrapperEntity<IssueEntity>> response) {
                        SearchWrapperEntity<IssueEntity> body = response.getBody();
                        List<IssueModel> repositoryModelList = new IssueMapper().transform(body.getItems());
                        liveData.setValue(repositoryModelList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("SearchRe searchIssues", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }
}
