package com.jeanboy.app.github.di;

import android.content.Context;

import com.jeanboy.app.github.base.BaseBindFragment;
import com.jeanboy.arch.base.wrapper.DiWrapper;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by jeanboy on 2018/4/25.
 */
public abstract class BaseDiFragment extends BaseBindFragment implements DiWrapper {


    @Override
    public void onAttach(Context context) {
        onInject();
        super.onAttach(context);
    }

    @Override
    public void onInject() {
        AndroidSupportInjection.inject(this);
    }
}
