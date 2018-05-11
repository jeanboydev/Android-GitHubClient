package com.jeanboy.app.github.ui.fragment;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jeanboy.app.github.R;
import com.jeanboy.app.github.config.AppSettings;
import com.jeanboy.app.github.di.BaseDiFragment;
import com.jeanboy.app.github.ui.activity.SettingsActivity;
import com.jeanboy.app.github.ui.vm.MineViewModel;
import com.jeanboy.arch.base.helper.ToolbarHelper;
import com.jeanboy.arch.data.cache.database.model.UserInfoModel;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class MineFragment extends BaseDiFragment {

    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_home_page)
    TextView tv_home_page;
    @BindView(R.id.tv_description)
    TextView tv_description;

    @BindView(R.id.tv_repositories_count)
    TextView tv_repositories_count;
    @BindView(R.id.tv_stars_count)
    TextView tv_stars_count;
    @BindView(R.id.tv_followers_count)
    TextView tv_followers_count;
    @BindView(R.id.tv_following_count)
    TextView tv_following_count;


    @Inject
    MineViewModel mineViewModel;

    @Inject
    public MineFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void setupArguments(Bundle args) {

    }

    @Override
    protected void setupView(View view, Bundle savedInstanceState) {
        ToolbarHelper.setToolBarTitle(getToolbar(), R.string.title_mine);
    }

    @Override
    protected void initData() {
        LiveData<UserInfoModel> userInfo = mineViewModel.getUserInfo(AppSettings.getUserId());
        userInfo.observe(this, new Observer<UserInfoModel>() {
            @Override
            public void onChanged(@Nullable UserInfoModel userInfoModel) {
                if (userInfoModel == null) return;
                refreshView(userInfoModel);
            }
        });
    }

    @OnClick({R.id.item_settings})
    public void toSettings() {
        SettingsActivity.startBy(getActivity());
    }

    private void refreshView(UserInfoModel userInfoModel) {
        String avatarUrl = userInfoModel.getAvatarUrl();
        Glide.with(Objects.requireNonNull(getActivity())).load(avatarUrl).into(iv_avatar);

        tv_username.setText(userInfoModel.getLogin());
        tv_nickname.setText(userInfoModel.getName());
        tv_email.setText(userInfoModel.getEmail());
        tv_home_page.setText(userInfoModel.getBlog());
        tv_description.setText(userInfoModel.getBio());
        tv_repositories_count.setText(String.valueOf(userInfoModel.getPublicRepos()));
//        tv_stars_count.setText(String.valueOf(userInfoModel.getPublicRepos()));
        tv_followers_count.setText(String.valueOf(userInfoModel.getFollowers()));
        tv_following_count.setText(String.valueOf(userInfoModel.getFollowing()));
    }

}
