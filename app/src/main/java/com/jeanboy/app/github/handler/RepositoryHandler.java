package com.jeanboy.app.github.handler;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.jeanboy.app.github.data.DataExecutors;
import com.jeanboy.app.github.net.RequestCallback;
import com.jeanboy.app.github.net.RequestParams;
import com.jeanboy.app.github.net.ResponseData;
import com.jeanboy.app.github.net.manager.OkHttpManager;

import retrofit2.Call;

public abstract class RepositoryHandler<ResponseType, ResultType> {

    private MutableLiveData<ResultType> liveData = new MutableLiveData<>();
    private MediatorLiveData<ResultType> watcher = new MediatorLiveData<>();

    public RepositoryHandler() {
        loadCache();//读取缓存数据
        watcher.addSource(liveData, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType resultType) {
                //当读取到缓存数据
                watcher.removeSource(liveData);
                if (shouldFetch(resultType)) {
                    loadRemote();
                }
            }
        });
    }

    /**
     * 读取缓存数据
     */
    private void loadCache() {
        DataExecutors.getInstance().load(new Runnable() {
            @Override
            public void run() {
                LiveData<ResultType> fromRoom = loadFromRoom();
                liveData.setValue(fromRoom == null ? null : fromRoom.getValue());
            }
        });
    }

    /**
     * 请求 API 数据
     */
    private void loadRemote() {
        Call<ResponseType> fromNetwork = fetchFromNetwork();
        if (fromNetwork == null) {
            return;
        }

        OkHttpManager.getInstance().doBack(new RequestParams<>(fromNetwork),
                new RequestCallback<ResponseData<ResponseType>>() {
                    @Override
                    public void onSuccess(ResponseData<ResponseType> response) {
                        ResponseType responseType = response.getBody();
                        ResultType resultType = onMapper(responseType);
                        liveData.setValue(resultType);
                        if (resultType == null) return;
                        saveToCache(resultType);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
    }

    /**
     * 保存数据到缓存
     *
     * @param resultType
     */
    private void saveToCache(final ResultType resultType) {
        DataExecutors.getInstance().put(new Runnable() {
            @Override
            public void run() {
                updateToRoom(resultType);
            }
        });
    }

    public LiveData<ResultType> asLiveData() {
        return liveData;
    }

    /**
     *
     * @return
     */
    protected LiveData<ResultType> loadFromRoom() {
        return null;
    }

    /**
     *
     * @param resultType
     */
    protected void updateToRoom(ResultType resultType) {
    }

    /**
     *
     * @param cache
     * @return
     */
    protected abstract boolean shouldFetch(@Nullable ResultType cache);

    /**
     *
     * @return
     */
    protected abstract Call<ResponseType> fetchFromNetwork();

    /**
     *
     * @param responseType
     * @return
     */
    protected abstract ResultType onMapper(ResponseType responseType);
}