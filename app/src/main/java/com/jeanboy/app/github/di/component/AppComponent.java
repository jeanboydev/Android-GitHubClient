package com.jeanboy.app.github.di.component;


import com.jeanboy.app.github.di.BaseDiApplication;
import com.jeanboy.app.github.di.modules.AppModule;
import com.jeanboy.app.github.di.modules.MainModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by jeanboy on 2018/4/25.
 */
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        MainModule.class})
public interface AppComponent extends AndroidInjector<BaseDiApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseDiApplication> {
    }
}
