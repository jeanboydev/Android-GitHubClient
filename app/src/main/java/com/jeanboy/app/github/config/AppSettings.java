package com.jeanboy.app.github.config;

import com.jeanboy.app.github.base.MainApplication;
import com.jeanboy.arch.base.helper.SharedPreferencesHelper;

/**
 * Created by jeanboy on 2018/4/27.
 */
public class AppSettings {

    private final static String USER_ID = "user_id";
    private final static String USERNAME = "username";
    private final static String ACCESS_TOKEN = "access_token";

    public static long getUserId() {
        return SharedPreferencesHelper.getLong(MainApplication.getInstance(), USER_ID, 0);
    }

    public static void setUserId(long userId) {
        SharedPreferencesHelper.putLong(MainApplication.getInstance(), USER_ID, userId);
    }

    public static String getUsername() {
        return SharedPreferencesHelper.getString(MainApplication.getInstance(), USERNAME);
    }

    public static void setUsername(String username) {
        SharedPreferencesHelper.putString(MainApplication.getInstance(), USERNAME, username);
    }

    public static String getAccessToken() {
        return SharedPreferencesHelper.getString(MainApplication.getInstance(), ACCESS_TOKEN);
    }

    public static void setAccessToken(String accessToken) {
        SharedPreferencesHelper.putString(MainApplication.getInstance(), ACCESS_TOKEN, accessToken);
    }

    public static void signOut(){
        AppSettings.setAccessToken(null);
        AppSettings.setUserId(0);
        AppSettings.setUsername(null);
    }
}
