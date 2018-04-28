package com.jeanboy.arch.data.repository;

import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.AccessTokenEntity;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.GitHubAuthService;
import com.jeanboy.arch.data.net.service.GitHubService;

import retrofit2.Call;

/**
 * Created by jeanboy on 2018/4/28.
 */
public class GitHubRepository {

    private AppDatabase database;
    private GitHubAuthService gitHubAuthService;
    private GitHubService gitHubService;

    public GitHubRepository() {
        database = DBManager.getInstance().getDatabase();
        gitHubAuthService = NetManager.getInstance().create(GitHubAuthService.BASE_URL, GitHubAuthService.class);
        gitHubService = NetManager.getInstance().create(GitHubService.BASE_URL, GitHubService.class);
    }

    public void getAccessToken(String clientID,
                               String clientSecret,
                               String code, RequestCallback<ResponseData<AccessTokenEntity>> callback) {
        Call<AccessTokenEntity> service = gitHubAuthService.getAccessToken(clientID, clientSecret, code);
        NetManager.getInstance().request(new RequestParams<>(service), callback);
    }

    public void getUserInfo(String accessToken, RequestCallback<ResponseData<UserInfoEntity>> callback) {
        Call<UserInfoEntity> service = gitHubService.getUserInfo("token " + accessToken);
        NetManager.getInstance().request(new RequestParams<>(service), callback);
    }
}
