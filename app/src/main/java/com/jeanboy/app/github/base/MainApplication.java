package com.jeanboy.app.github.base;

import com.facebook.stetho.Stetho;
import com.jeanboy.app.github.di.BaseDiApplication;
import com.jeanboy.arch.data.cache.database.model.TokenModel;
import com.jeanboy.arch.data.cache.manager.DBManager;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class MainApplication extends BaseDiApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        DBManager.getInstance().build(this, "github.db");
        Stetho.initializeWithDefaults(this);
    }
}
