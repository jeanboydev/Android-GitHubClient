package com.jeanboy.app.github.di.modules;

import android.arch.lifecycle.ViewModelProviders;

import com.jeanboy.app.github.ui.fragment.HomeFragment;
import com.jeanboy.app.github.ui.vm.MainHomeViewModel;

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

}