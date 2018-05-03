package com.jeanboy.app.github.di.component;

import com.jeanboy.app.github.di.BaseDiActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by jeanboy on 2018/4/25.
 */
@Subcomponent(modules = {AndroidInjectionModule.class})
public interface ActivityComponent extends AndroidInjector<BaseDiActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseDiActivity> {
    }
}