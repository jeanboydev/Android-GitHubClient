package com.jeanboy.app.github.config;

import com.jeanboy.app.github.base.MainApplication;
import com.jeanboy.arch.base.helper.SharedPreferencesHelper;

/**
 * Created by jeanboy on 2018/4/27.
 */
public class AppSettings {

    private final static String USER_ID = "user_id";

    public static long getUserId() {
        return SharedPreferencesHelper.getLong(MainApplication.getInstance(), USER_ID, 0);
    }

    public static void setUserId(long userId) {
        SharedPreferencesHelper.putLong(MainApplication.getInstance(), USER_ID, userId);
    }
}
