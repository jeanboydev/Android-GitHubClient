package com.jeanboy.app.github.di.modules;

import com.jeanboy.app.github.di.component.ActivityComponent;
import com.jeanboy.app.github.ui.activity.MainActivity;
import com.jeanboy.app.github.ui.fragment.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jeanboy on 2018/4/25.
 */
@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();
}