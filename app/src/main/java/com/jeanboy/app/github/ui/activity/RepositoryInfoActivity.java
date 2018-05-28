package com.jeanboy.app.github.ui.activity;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.jeanboy.app.github.R;
import com.jeanboy.app.github.di.BaseDiActivity;
import com.jeanboy.app.github.ui.vm.RepositoryInfoViewModel;
import com.jeanboy.arch.base.ExtrasCallback;
import com.jeanboy.arch.base.helper.ToolbarHelper;
import com.jeanboy.arch.base.util.DateUtil;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.zzhoujay.richtext.RichText;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;

public class RepositoryInfoActivity extends BaseDiActivity {


    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_repos_name)
    TextView tv_repos_name;
    @BindView(R.id.tv_repos_desc)
    TextView tv_repos_desc;
    @BindView(R.id.tv_language)
    TextView tv_language;
    @BindView(R.id.tv_update_time)
    TextView tv_update_time;

    @BindView(R.id.tv_stars_count)
    TextView tv_stars_count;
    @BindView(R.id.tv_forks_count)
    TextView tv_forks_count;
    @BindView(R.id.tv_subscribers_count)
    TextView tv_subscribers_count;
    @BindView(R.id.tv_issues_count)
    TextView tv_issues_count;

    @BindView(R.id.tv_default_branch)
    TextView tv_default_branch;
    @BindView(R.id.tv_commit_desc)
    TextView tv_commit_desc;

    @BindView(R.id.tv_readme)
    TextView tv_readme;


    @Inject
    RepositoryInfoViewModel repositoryInfoViewModel;

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
        String title = username + "/" + repos;
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
                String branch = repositoryEntity.getDefault_branch();
                loadReadMe(branch);
            }
        });
    }

    private void loadReadMe(String branch) {
        final String baseUrl = "https://github.com/" + username + "/" + repos
                + "/blob/" + branch + "/" + "README.md";
        LiveData<String> readMeHTML = repositoryInfoViewModel.getReadMeHTML(username, repos);
        readMeHTML.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s == null) return;
                Log.e(TAG, "============onChanged=======tv_readme====");
                RichText.fromHtml(s).into(tv_readme);
            }
        });
    }

    private void refreshView(RepositoryEntity repositoryEntity) {
        Log.d(TAG, JSON.toJSONString(repositoryEntity));
        UserInfoEntity owner = repositoryEntity.getOwner();
        if (owner != null) {
            String username = owner.getLogin();
            tv_username.setText(username);
            String avatarUrl = owner.getAvatar_url();
            Glide.with(this).load(avatarUrl).into(iv_avatar);
        }
        tv_repos_name.setText(repositoryEntity.getFull_name());
        tv_repos_desc.setText(repositoryEntity.getDescription());
        tv_language.setText(repositoryEntity.getLanguage());
        String updateAt = repositoryEntity.getUpdated_at();
        Date updateDate = DateUtil.formatUTC(updateAt);
        String formatUpdate = DateUtil.format(updateDate.getTime(), DateUtil.FORMAT_DATE_24_FULL);
        tv_update_time.setText(formatUpdate);

        tv_stars_count.setText(String.valueOf(repositoryEntity.getStargazers_count()));
        tv_forks_count.setText(String.valueOf(repositoryEntity.getForks_count()));
        tv_subscribers_count.setText(String.valueOf(repositoryEntity.getSubscribers_count()));
        tv_issues_count.setText(String.valueOf(repositoryEntity.getOpen_issues_count()));

        tv_default_branch.setText(repositoryEntity.getDefault_branch());
        String pushAt = repositoryEntity.getPushed_at();
        Date pushDate = DateUtil.formatUTC(pushAt);
        String formatPush = DateUtil.format(pushDate.getTime(), DateUtil.FORMAT_DATE_24_FULL);
        tv_commit_desc.setText(getResources().getString(R.string.repos_latest_commit, formatPush));
    }
}
