package com.jeanboy.app.github.di.component;

import com.jeanboy.app.github.di.BaseDiFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by jeanboy on 2018/4/25.
 */
@Subcomponent(modules = {AndroidInjectionModule.class})
public interface FragmentComponent extends AndroidInjector<BaseDiFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseDiFragment> {
    }
}
