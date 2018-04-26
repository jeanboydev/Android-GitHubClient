package com.jeanboy.app.github.di.modules;

import com.jeanboy.app.github.di.component.ActivityComponent;
import com.jeanboy.app.github.ui.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jeanboy on 2018/4/25.
 */
@Module(subcomponents = ActivityComponent.class)
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity contributeMainActivity();
}