package com.jeanboy.app.github.di.modules;

import android.arch.lifecycle.ViewModelProviders;

import com.jeanboy.app.github.ui.activity.AuthActivity;
import com.jeanboy.app.github.ui.activity.MainActivity;
import com.jeanboy.app.github.ui.vm.AuthViewModel;
import com.jeanboy.app.github.ui.vm.MainViewModel;
import com.jeanboy.app.github.ui.vm.TestApiViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jeanboy on 2018/4/25.
 */
@Module
public class ViewModelActivityModule {

    @Provides
    MainViewModel provideUserViewModel(MainActivity activity) {
        return ViewModelProviders.of(activity).get(MainViewModel.class);
    }

    @Provides
    AuthViewModel provideTokenViewModel(AuthActivity activity) {
        return ViewModelProviders.of(activity).get(AuthViewModel.class);
    }

    @Provides
    TestApiViewModel provideTestApiViewModel(MainActivity activity) {
        return ViewModelProviders.of(activity).get(TestApiViewModel.class);
    }

}