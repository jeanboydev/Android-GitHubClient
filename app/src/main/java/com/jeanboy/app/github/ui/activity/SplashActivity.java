package com.jeanboy.app.github.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

import com.jeanboy.app.github.R;
import com.jeanboy.app.github.config.AppSettings;
import com.jeanboy.app.github.di.BaseDiActivity;
import com.jeanboy.arch.base.helper.AppHelper;

import butterknife.BindView;

public class SplashActivity extends BaseDiActivity {

    @BindView(R.id.tv_version)
    TextView tv_version;

    private static final int DELAY_MILLIS = 1500;
    private final Handler mHideHandler = new Handler();
    private final Runnable mJumpRunnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            if (AppSettings.getUserId() == 0 || AppSettings.getAccessToken() == null) {
                AuthActivity.startBy(SplashActivity.this);
                SplashActivity.this.finish();
                return;
            }
            MainActivity.startBy(SplashActivity.this);
            SplashActivity.this.finish();
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        tv_version.setText(AppHelper.getVersionName(this));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        delayedHide(DELAY_MILLIS);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mJumpRunnable);
        mHideHandler.postDelayed(mJumpRunnable, delayMillis);
    }
}
