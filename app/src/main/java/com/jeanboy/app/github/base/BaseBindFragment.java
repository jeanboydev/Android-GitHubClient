package com.jeanboy.app.github.base;

import android.os.Bundle;
import android.view.View;

import com.jeanboy.arch.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jeanboy on 2018/4/25.
 */
public abstract class BaseBindFragment extends BaseFragment {

    private Unbinder unbinder;

    @Override
    protected void onFragmentCreate() {
    }

    @Override
    protected void onFragmentViewCreated(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }
}
