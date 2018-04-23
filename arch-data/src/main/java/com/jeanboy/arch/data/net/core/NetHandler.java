package com.jeanboy.arch.data.net.core;

/**
 * Created by jeanboy on 2017/2/10.
 */

public interface NetHandler<P, B> {

    void request(RequestParams<P> requestValues, RequestCallback<ResponseData<B>> callback);

    void requestSync(RequestParams<P> requestValues, RequestCallback<ResponseData<B>> callback);

}
