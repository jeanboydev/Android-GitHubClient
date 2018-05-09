package com.jeanboy.app.github.di;

import android.content.Context;

import com.jeanboy.app.github.base.BaseBindFragment;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by jeanboy on 2018/4/25.
 */
public abstract class BaseDiFragment extends BaseBindFragment {

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}
