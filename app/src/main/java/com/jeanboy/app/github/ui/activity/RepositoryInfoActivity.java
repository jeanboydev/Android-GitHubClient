package com.jeanboy.app.github.ui.activity;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.jeanboy.app.github.R;
import com.jeanboy.app.github.di.BaseDiActivity;
import com.jeanboy.app.github.ui.vm.RepositoryInfoViewModel;
import com.jeanboy.arch.base.ExtrasCallback;
import com.jeanboy.arch.base.helper.ToolbarHelper;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;

import javax.inject.Inject;

public class RepositoryInfoActivity extends BaseDiActivity {


    private final static String KEY_USERNAME = "username";
    private final static String KEY_REPOS = "repos";

    private String username;
    private String repos;

    public static void startBy(Activity context, String username, String repos) {
        startActivity(context, RepositoryInfoActivity.class, new ExtrasCallback() {
            @Override
            public void onPutExtras(Bundle bundle) {
                bundle.putString(KEY_USERNAME, username);
                bundle.putString(KEY_REPOS, repos);
            }
        });
    }

    @Inject
    RepositoryInfoViewModel repositoryInfoViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_repository_info;
    }

    @Override
    protected void setupArguments(Bundle args) {
        super.setupArguments(args);
        username = args.getString(KEY_USERNAME);
        repos = args.getString(KEY_REPOS);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        String title = getResources().getString(R.string.title_repos, username, repos);
        ToolbarHelper.setToolBarTitle(getToolbar(), title);
        ToolbarHelper.setToolbarHomeAsUp(this);
        ToolbarHelper.setStatusBarTranslucent(this);
    }

    @Override
    protected void initData() {
        LiveData<RepositoryEntity> reposInfo = repositoryInfoViewModel.getReposInfo(username, repos);
        reposInfo.observe(this, new Observer<RepositoryEntity>() {
            @Override
            public void onChanged(@Nullable RepositoryEntity repositoryEntity) {
                if (repositoryEntity == null) return;
                refreshView(repositoryEntity);
            }
        });
    }

    private void refreshView(RepositoryEntity repositoryEntity) {
        Log.d(TAG, JSON.toJSONString(repositoryEntity));
    }
}
