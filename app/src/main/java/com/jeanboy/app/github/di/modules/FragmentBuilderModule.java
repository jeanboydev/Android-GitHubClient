package com.jeanboy.app.github.di.modules;

import com.jeanboy.app.github.di.component.FragmentComponent;
import com.jeanboy.app.github.ui.fragment.HomeFragment;
import com.jeanboy.app.github.ui.fragment.MineFragment;
import com.jeanboy.app.github.ui.fragment.ProjectFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jeanboy on 2018/4/25.
 */
@Module(subcomponents = FragmentComponent.class)
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract HomeFragment contributeMainFragment();

    @ContributesAndroidInjector
    abstract ProjectFragment contributeProjectFragment();

    @ContributesAndroidInjector
    abstract MineFragment contributeMineFragment();
}