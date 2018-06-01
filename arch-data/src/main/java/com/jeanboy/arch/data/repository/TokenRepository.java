package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jeanboy.arch.data.cache.database.model.AccessTokenModel;
import com.jeanboy.arch.data.cache.database.model.AuthTokenModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.AccessTokenEntity;
import com.jeanboy.arch.data.net.entity.AuthTokenEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.AuthService;
import com.jeanboy.arch.data.repository.handler.MapperHandler;
import com.jeanboy.arch.data.repository.handler.RepositoryHandler;
import com.jeanboy.arch.data.repository.mapper.AccessTokenMapper;
import com.jeanboy.arch.data.repository.mapper.AuthTokenMapper;
import com.jeanboy.arch.data.repository.params.AuthParams;
import com.jeanboy.arch.data.repository.params.TokenParams;

import okhttp3.Credentials;
import retrofit2.Call;

/**
 * Created by jeanboy on 2018/5/2.
 */
public class TokenRepository {

    private AppDatabase database;
    private AuthService authService;

    public TokenRepository() {
        database = DBManager.getInstance().getDatabase();
        authService = NetManager.getInstance().createForJSON(AuthService.BASE_URL, AuthService.class);
    }

    public AccessTokenModel getAccessToken() {
        return database.accessTokenDao().get();
    }

    public LiveData<AccessTokenModel> getAccessToken(TokenParams params) {
        return new RepositoryHandler<AccessTokenEntity, AccessTokenModel>() {

            @Override
            protected LiveData<AccessTokenModel> loadFromRoom() {
                return database.accessTokenDao().getLive();
            }

            @Override
            protected void updateToRoom(AccessTokenModel accessTokenModel) {
                database.accessTokenDao().insert(accessTokenModel);
            }

            @Override
            protected boolean shouldFetch(@Nullable AccessTokenModel accessTokenModel) {
                return accessTokenModel == null;
            }

            @Override
            protected Call<AccessTokenEntity> fetchFromNetwork() {
                if (params == null) return null;
                return authService.getAccessToken(params.getClientID(), params.getClientSecret(),
                        params.getCode());
            }

            @Override
            protected MapperHandler<AccessTokenEntity, AccessTokenModel> onMapper() {
                return new AccessTokenMapper();
            }
        }.asLiveData();
    }

    /**
     * 使用账号密码登录
     *
     * @param account 账号
     * @param password 密码
     * @param params 参数
     * @return
     */
    public LiveData<AuthTokenModel> authorizations(String account, String password, AuthParams params) {
        MutableLiveData<AuthTokenModel> liveData = new MutableLiveData<>();
        String token = Credentials.basic(account, password);
        String auth = token.startsWith("Basic") ? token : "token " + token;
        Log.d("authorizations: ", "auth:" + auth);
        String url = "https://api.github.com/authorizations";
        Call<AuthTokenEntity> call = authService.authorizations(url, params, auth);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<AuthTokenEntity>>() {
                    @Override
                    public void onSuccess(ResponseData<AuthTokenEntity> response) {
                        AuthTokenEntity body = response.getBody();
                        AuthTokenModel authTokenModel = new AuthTokenMapper().transform(body);
                        liveData.setValue(authTokenModel);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("authorizations", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

}
