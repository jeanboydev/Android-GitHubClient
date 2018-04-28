package com.jeanboy.arch.base.wrapper;

/**
 * Created by jeanboy on 2017/8/2.
 * <p>
 * 用于初始化butterknife
 */

public interface BindWrapper<T> {

    /**
     * activity中：onSetContentView()后调用
     * fragment中：onFragmentViewCreated()后调用
     */
    void onBind(T target);

    /**
     * activity中：onDestroy()后调用
     * fragment中：onDestroyView()后调用
     */
    void onUnbind();
}
