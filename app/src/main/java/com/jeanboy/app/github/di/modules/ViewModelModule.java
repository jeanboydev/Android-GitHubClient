package com.jeanboy.app.github.di.modules;

import com.jeanboy.arch.data.repository.UserRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jeanboy on 2018/4/25.
 */
@Module
public class ViewModelModule {

    @Provides
    UserRepository provideUserRepository() {
        return new UserRepository();
    }
}
