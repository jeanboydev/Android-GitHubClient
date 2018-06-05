package com.jeanboy.app.github.di.modules;


import com.jeanboy.app.github.ui.activity.AuthActivity;
import com.jeanboy.app.github.ui.activity.MainActivity;
import com.jeanboy.app.github.ui.activity.RepositoryInfoActivity;
import com.jeanboy.app.github.ui.activity.SettingsActivity;
import com.jeanboy.app.github.ui.activity.SplashActivity;
import com.jeanboy.app.github.ui.activity.UserFollowersListActivity;
import com.jeanboy.app.github.ui.activity.UserFollowingListActivity;
import com.jeanboy.app.github.ui.activity.UserRepositoryListActivity;
import com.jeanboy.app.github.ui.activity.UserStarsListActivity;
import com.jeanboy.app.github.ui.fragment.HomeFragment;
import com.jeanboy.app.github.ui.fragment.MineFragment;
import com.jeanboy.app.github.ui.fragment.TrendingFragment;

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

    @ContributesAndroidInjector(modules = {ViewModelActivityModule.class})
    abstract SettingsActivity contributeSettingsActivity();

    @ContributesAndroidInjector(modules = {ViewModelActivityModule.class})
    abstract RepositoryInfoActivity contributeRepositoryInfoActivity();

    @ContributesAndroidInjector(modules = {ViewModelActivityModule.class})
    abstract UserRepositoryListActivity contributeUserRepositoryListActivity();

    @ContributesAndroidInjector(modules = {ViewModelActivityModule.class})
    abstract UserStarsListActivity contributeUserStarsListActivity();

    @ContributesAndroidInjector(modules = {ViewModelActivityModule.class})
    abstract UserFollowersListActivity contributeUserFollowersListActivity();

    @ContributesAndroidInjector(modules = {ViewModelActivityModule.class})
    abstract UserFollowingListActivity contributeUserFollowingListActivity();

    /*---- fragment ----*/
    @ContributesAndroidInjector(modules = {ViewModelFragmentModule.class})
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract TrendingFragment contributeTrendingFragment();

    @ContributesAndroidInjector(modules = {ViewModelFragmentModule.class})
    abstract MineFragment contributeMineFragment();

}
