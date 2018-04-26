package com.jeanboy.app.github.ui.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jeanboy.app.github.R;
import com.jeanboy.app.github.di.BaseDiActivity;
import com.jeanboy.app.github.ui.vm.UserViewModel;
import com.jeanboy.arch.data.cache.database.model.UserModel;

import javax.inject.Inject;


public class MainActivity extends BaseDiActivity {

    @Inject
    UserViewModel userViewModel;
    private LiveData<UserModel> userModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {
        userModel = userViewModel.getUserInfo(1);
        userModel.observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(@Nullable UserModel userModel) {

            }
        });
    }

}
