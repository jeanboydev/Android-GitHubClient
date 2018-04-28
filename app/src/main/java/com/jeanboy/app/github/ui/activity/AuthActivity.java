package com.jeanboy.app.github.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.jeanboy.app.github.R;
import com.jeanboy.app.github.config.AppConfig;
import com.jeanboy.app.github.di.BaseDiActivity;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.AccessTokenEntity;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.jeanboy.arch.data.repository.GitHubRepository;

public class AuthActivity extends BaseDiActivity {

    private GitHubRepository gitHubRepository = new GitHubRepository();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    public void toAuth(View view) {
        getGitHubAccessToken();
    }

    public void getGitHubAccessToken() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "https://github.com/login/oauth/authorize" + "?client_id=" + AppConfig.CLIENT_ID
                        + "&scope=repo" + "&redirect_uri=" + AppConfig.REDIRECT_URI
        ));

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(AppConfig.REDIRECT_URI)) {
            String code = uri.getQueryParameter("code");
            gitHubRepository.getAccessToken(AppConfig.CLIENT_ID, AppConfig.CLIENT_SECRET, code,
                    new RequestCallback<ResponseData<AccessTokenEntity>>() {
                        @Override
                        public void onSuccess(ResponseData<AccessTokenEntity> response) {
                            AccessTokenEntity responseBody = response.getBody();
                            Log.d(TAG, "====" + JSON.toJSONString(responseBody));
                            getUserInfo(responseBody.getAccessToken());
                        }

                        @Override
                        public void onError(int code, String msg) {
                            Log.d(TAG, "====" + msg);
                        }
                    });
        }
    }

    private void getUserInfo(String accessToken) {
        gitHubRepository.getUserInfo(accessToken, new RequestCallback<ResponseData<UserInfoEntity>>() {
            @Override
            public void onSuccess(ResponseData<UserInfoEntity> response) {
                UserInfoEntity responseBody = response.getBody();
                Log.d(TAG, "====" + JSON.toJSONString(responseBody));
            }

            @Override
            public void onError(int code, String msg) {
                Log.d(TAG, "====" + msg);
            }
        });
    }
}
