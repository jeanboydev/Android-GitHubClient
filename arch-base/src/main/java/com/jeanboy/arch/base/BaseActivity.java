package com.jeanboy.arch.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jeanboy.arch.base.helper.ToolbarHelper;

public abstract class BaseActivity extends AppCompatActivity {

    public String TAG = this.getClass().getSimpleName();

    private Toolbar toolbar;

    protected abstract int getLayoutId();

    protected void setupArguments(Bundle args) {

    }

    protected abstract void onSetContentView();

    protected abstract void setupView(Bundle savedInstanceState);

    protected abstract void initData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (getIntent() != null && getIntent().getExtras() != null) {
            setupArguments(getIntent().getExtras());
        }
        onSetContentView();
        setupToolbar();
        setupView(savedInstanceState);
        initData();
    }

    /**
     * 初始化Toolbar
     */
    private void setupToolbar() {
        if (toolbar == null) {
            toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle("");
                setSupportActionBar(toolbar);
            }
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ToolbarHelper.setStatusBarImmersiveWindowFocusChanged(this, hasFocus);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        super.onDestroy();
    }

    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.add(containerViewId, fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 显示 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void showFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.add(containerViewId, fragment);
        }
        fragmentTransaction.show(fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏 Fragment
     *
     * @param fragment
     */
    protected void hideFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded() && !fragment.isHidden()) {
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 启动当前 Activity
     *
     * @param by
     * @param target
     * @param callback
     */
    protected static void startActivity(Activity by, Class<? extends Activity> target,
                                        @Nullable ExtrasCallback callback) {
        Intent intent = new Intent(by, target);
        Bundle bundle = new Bundle();
        if (callback != null) {
            callback.onPutExtras(bundle);
        }
        intent.putExtras(bundle);
        by.startActivity(intent);
    }

}
