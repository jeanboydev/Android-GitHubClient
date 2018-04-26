package com.jeanboy.app.github.di;

import android.app.Activity;
import android.app.Application;


import com.jeanboy.app.github.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class BaseDiApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    private static BaseDiApplication instance;

    public static BaseDiApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.create().inject(this);

        instance = this;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
