package com.jeanboy.app.github.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.jeanboy.app.github.R;
import com.jeanboy.app.github.config.AppSettings;
import com.jeanboy.app.github.di.BaseDiActivity;
import com.jeanboy.arch.base.helper.ToolbarHelper;

import butterknife.OnClick;

public class SettingsActivity extends BaseDiActivity {


    public static void startBy(Activity context) {
        startActivity(context, SettingsActivity.class, null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        ToolbarHelper.setToolBarTitle(getToolbar(), R.string.title_settings);
        ToolbarHelper.setToolbarHomeAsUp(this);

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.item_sign_out)
    public void toSignOut() {
        AppSettings.signOut();
        AuthActivity.startBy(SettingsActivity.this);
        SettingsActivity.this.finish();
    }
}
