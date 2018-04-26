package com.jeanboy.app.github.di.modules;

import android.arch.lifecycle.ViewModelProviders;

import com.jeanboy.app.github.ui.activity.MainActivity;
import com.jeanboy.app.github.ui.vm.UserViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jeanboy on 2018/4/25.
 */
@Module
public class MainActivityModule {

    @Provides
    UserViewModel provideUserViewModel(MainActivity activity) {
        return ViewModelProviders.of(activity).get(UserViewModel.class);
    }
}