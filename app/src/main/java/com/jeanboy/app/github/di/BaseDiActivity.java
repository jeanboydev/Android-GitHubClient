package com.jeanboy.app.github.di;

import android.support.v4.app.Fragment;

import com.jeanboy.app.github.base.BaseBindActivity;
import com.jeanboy.arch.base.wrapper.DiWrapper;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by jeanboy on 2018/4/25.
 */
public abstract class BaseDiActivity extends BaseBindActivity implements DiWrapper, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onSetContentView() {
        onInject();
        super.onSetContentView();
    }

    @Override
    public void onInject() {
        AndroidInjection.inject(this);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}