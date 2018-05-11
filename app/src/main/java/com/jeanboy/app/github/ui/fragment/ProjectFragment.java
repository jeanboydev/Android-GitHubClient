package com.jeanboy.app.github.ui.fragment;


import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.github.R;
import com.jeanboy.app.github.di.BaseDiFragment;
import com.jeanboy.arch.base.helper.ToolbarHelper;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class ProjectFragment extends BaseDiFragment {

    @Inject
    public ProjectFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void setupView(View view, Bundle savedInstanceState) {
        ToolbarHelper.setToolBarTitle(getToolbar(), R.string.title_project);
    }

    @Override
    protected void initData() {

    }

}
