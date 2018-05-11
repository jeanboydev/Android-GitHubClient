package com.jeanboy.app.github.di.modules;

import android.arch.lifecycle.ViewModelProviders;

import com.jeanboy.app.github.ui.fragment.HomeFragment;
import com.jeanboy.app.github.ui.fragment.MineFragment;
import com.jeanboy.app.github.ui.vm.MainHomeViewModel;
import com.jeanboy.app.github.ui.vm.MineViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jeanboy on 2018/4/25.
 */
@Module
public class ViewModelFragmentModule {

    @Provides
    MainHomeViewModel provideMainHomeViewModel(HomeFragment fragment) {
        return ViewModelProviders.of(fragment).get(MainHomeViewModel.class);
    }

    @Provides
    MineViewModel provideMineViewModel(MineFragment fragment) {
        return ViewModelProviders.of(fragment).get(MineViewModel.class);
    }
}