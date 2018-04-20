package com.jeanboy.app.github.net;

/**
 * Created by jeanboy on 2016/7/13.
 */
public interface RequestCallback<R> {

    void onSuccess(R response);

    void onError(int code, String msg);
}
