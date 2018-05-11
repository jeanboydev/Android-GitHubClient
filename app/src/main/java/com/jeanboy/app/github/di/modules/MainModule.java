package com.jeanboy.app.github.di.modules;


import com.jeanboy.app.github.ui.activity.AuthActivity;
import com.jeanboy.app.github.ui.activity.MainActivity;
import com.jeanboy.app.github.ui.activity.SplashActivity;
import com.jeanboy.app.github.ui.fragment.HomeFragment;
import com.jeanboy.app.github.ui.fragment.MineFragment;
import com.jeanboy.app.github.ui.fragment.ProjectFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {

    @ContributesAndroidInjector
    abstract SplashActivity contributeSplashActivity();

    @ContributesAndroidInjector(modules = {ViewModelActivityModule.class})
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {ViewModelActivityModule.class})
    abstract AuthActivity contributeAuthActivity();

    /*---- fragment ----*/
    @ContributesAndroidInjector(modules = {ViewModelFragmentModule.class})
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract ProjectFragment contributeProjectFragment();

    @ContributesAndroidInjector(modules = {ViewModelFragmentModule.class})
    abstract MineFragment contributeMineFragment();

}
