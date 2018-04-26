package com.jeanboy.app.github.di.component;


import com.jeanboy.app.github.di.BaseDiApplication;
import com.jeanboy.app.github.di.modules.ActivityBuilderModule;
import com.jeanboy.app.github.di.modules.AppModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by jeanboy on 2018/4/25.
 */
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    void inject(BaseDiApplication application);
}
