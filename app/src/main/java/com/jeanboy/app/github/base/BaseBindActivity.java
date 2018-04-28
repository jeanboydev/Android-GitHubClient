package com.jeanboy.app.github.base;

import android.app.Activity;


import com.jeanboy.arch.base.BaseActivity;
import com.jeanboy.arch.base.wrapper.BindWrapper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jeanboy on 2018/4/25.
 */
public abstract class BaseBindActivity extends BaseActivity implements BindWrapper<Activity> {

    private Unbinder unbinder;

    @Override
    protected void onSetContentView() {
        onBind(this);
    }

    @Override
    protected void onDestroy() {
        onUnbind();
        super.onDestroy();
    }

    @Override
    public void onBind(Activity target) {
        unbinder = ButterKnife.bind(target);
    }

    @Override
    public void onUnbind() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
