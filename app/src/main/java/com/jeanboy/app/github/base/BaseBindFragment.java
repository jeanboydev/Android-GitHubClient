package com.jeanboy.app.github.base;

import android.os.Bundle;
import android.view.View;


import com.jeanboy.arch.base.BaseFragment;
import com.jeanboy.arch.base.wrapper.BindWrapper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jeanboy on 2018/4/25.
 */
public abstract class BaseBindFragment extends BaseFragment implements BindWrapper {

    private Unbinder unbinder;

    @Override
    protected void onFragmentCreate() {
    }

    @Override
    protected void onFragmentViewCreated(View view, Bundle savedInstanceState) {
        onBind(view);
    }

    @Override
    public void onDestroyView() {
        onUnbind();
        super.onDestroyView();
    }

    @Override
    public void onBind(Object target) {
        unbinder = ButterKnife.bind((View) target);
    }

    @Override
    public void onUnbind() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
