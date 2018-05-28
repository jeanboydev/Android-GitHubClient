package com.jeanboy.app.github.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.jeanboy.app.github.R;
import com.jeanboy.app.github.di.BaseDiActivity;
import com.jeanboy.app.github.ui.fragment.HomeFragment;
import com.jeanboy.app.github.ui.fragment.MineFragment;
import com.jeanboy.app.github.ui.fragment.TrendingFragment;
import com.jeanboy.app.github.ui.vm.TestApiViewModel;

import javax.inject.Inject;

import butterknife.BindView;


public class MainActivity extends BaseDiActivity {

    @BindView(R.id.bottom_menu)
    RadioGroup bottomMenu;

    @Inject
    HomeFragment homeFragment;
    @Inject
    TrendingFragment trendingFragment;
    @Inject
    MineFragment mineFragment;

    @Inject
    TestApiViewModel testApiViewModel;

    private Fragment[] fragments;
    private int currentTabIndex = 0;

    public static void startBy(Activity context) {
        startActivity(context, MainActivity.class, null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        fragments = new Fragment[]{homeFragment, trendingFragment, mineFragment};
        showFragment(R.id.fragment_container, fragments[currentTabIndex]);
        bottomMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int index = 0;
                int count = group.getChildCount();
                for (int i = 0; i < count; i++) {
                    if (checkedId == group.getChildAt(i).getId()) {
                        index = i;
                    }
                }

                switchTab(index);
            }
        });

        // test code start

//        new TestApi().test(this, testApiViewModel);


        // test code end

    }

    private void switchTab(int index) {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded())
                trx.add(R.id.fragment_container, fragments[index]);
            trx.show(fragments[index]).commitAllowingStateLoss();
        }
        currentTabIndex = index;
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
