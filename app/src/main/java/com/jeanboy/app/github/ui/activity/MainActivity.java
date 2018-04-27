package com.jeanboy.app.github.ui.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jeanboy.app.github.R;
import com.jeanboy.app.github.di.BaseDiActivity;
import com.jeanboy.app.github.ui.vm.UserViewModel;
import com.jeanboy.arch.data.cache.database.model.UserModel;

import javax.inject.Inject;

import butterknife.BindView;


public class MainActivity extends BaseDiActivity {

    @BindView(R.id.tv_data)
    TextView tv_data;

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
                if (userModel == null) return;
                Log.d(TAG, "=====onChanged=====");
                tv_data.setText(JSON.toJSONString(userModel));
            }
        });
    }

    public void toSave(View view) {
        UserModel userModel = new UserModel();
        userModel.setUserNick("张三" + System.currentTimeMillis());
        userModel.setUserName("test" + System.currentTimeMillis());
        userModel.setCreateTime(System.currentTimeMillis());
        userViewModel.save(userModel);
    }

    public void toLoad(View view) {
        Log.d(TAG, "=====toLoad=====");

    }

    public void toUpdate(View view) {
        UserModel userModel = new UserModel();
        userModel.setId(1);
        userModel.setUserNick("张三new");
        userModel.setUserName("test new!!!");
        userModel.setCreateTime(System.currentTimeMillis());
        userViewModel.save(userModel);

    }
}
