package com.jeanboy.app.github.ui.fragment;


import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.github.R;
import com.jeanboy.app.github.di.BaseDiFragment;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class MineFragment extends BaseDiFragment {

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

    }

    @Override
    protected void initData() {

    }

}
