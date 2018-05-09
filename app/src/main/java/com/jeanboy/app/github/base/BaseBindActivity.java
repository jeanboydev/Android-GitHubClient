package com.jeanboy.app.github.base;

import com.jeanboy.arch.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jeanboy on 2018/4/25.
 */
public abstract class BaseBindActivity extends BaseActivity {

    private Unbinder unbinder;

    @Override
    protected void onSetContentView() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
