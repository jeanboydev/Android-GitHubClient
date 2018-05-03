package com.jeanboy.app.github.ui.activity;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.jeanboy.app.github.R;
import com.jeanboy.app.github.config.AppConfig;
import com.jeanboy.app.github.config.AppSettings;
import com.jeanboy.app.github.di.BaseDiActivity;
import com.jeanboy.app.github.ui.vm.AuthViewModel;
import com.jeanboy.arch.data.cache.database.model.AccessTokenModel;
import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.repository.params.TokenParams;

import javax.inject.Inject;

public class AuthActivity extends BaseDiActivity {

    @Inject
    AuthViewModel authViewModel;

    public static void startBy(Activity context) {
        startActivity(context, AuthActivity.class, null);
    }

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
            TokenParams tokenParams = new TokenParams(AppConfig.CLIENT_ID, AppConfig.CLIENT_SECRET, code);
            LiveData<AccessTokenModel> accessTokenLiveData = authViewModel.requestToken(tokenParams);
            accessTokenLiveData.observe(this, new Observer<AccessTokenModel>() {
                @Override
                public void onChanged(@Nullable AccessTokenModel accessTokenModel) {
                    Log.d(TAG, "==onChanged==" + JSON.toJSONString(accessTokenModel));

                    if (accessTokenModel == null) return;
                    getUserInfo(accessTokenModel.getAccessToken());
                }
            });
        }
    }

    private void getUserInfo(String accessToken) {
        LiveData<UserInfoModel> userInfoLiveData = authViewModel.getUserInfo(AppSettings.getUserId(), accessToken);
        userInfoLiveData.observe(this, new Observer<UserInfoModel>() {
            @Override
            public void onChanged(@Nullable UserInfoModel userInfoModel) {
                Log.d(TAG, "==getUserInfo==onChanged==" + JSON.toJSONString(userInfoModel));

                if (userInfoModel == null) return;
                AppSettings.setUserId(userInfoModel.getId());
            }
        });
    }
}
