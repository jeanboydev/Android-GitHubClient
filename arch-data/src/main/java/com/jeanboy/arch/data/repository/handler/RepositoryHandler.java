package com.jeanboy.arch.data.repository.handler;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;


import com.jeanboy.arch.data.cache.manager.DataExecutors;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.manager.NetManager;

import retrofit2.Call;

public abstract class RepositoryHandler<ResponseType, ResultType> {

    private MutableLiveData<ResultType> liveData = new MutableLiveData<>();
    private MediatorLiveData<ResultType> watcher = new MediatorLiveData<>();

    private MapperHandler<ResponseType, ResultType> mapperHandler;

    public RepositoryHandler(MapperHandler<ResponseType, ResultType> mapper) {
        this.mapperHandler = mapper;
        loadCache();//读取缓存数据
        watcher.addSource(liveData, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType resultType) {
                //当读取到缓存数据
                watcher.removeSource(liveData);
                if (RepositoryHandler.this.shouldFetch(resultType)) {
                    RepositoryHandler.this.loadRemote();
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

        NetManager.getInstance().request(new RequestParams<>(fromNetwork),
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
    /**
     * 数据转换
     *
     * @param responseType
     * @return
     */
    private ResultType onMapper(ResponseType responseType) {
        if (mapperHandler != null) {
            return mapperHandler.transform(responseType);
        }
        return null;
    }

    public LiveData<ResultType> asLiveData() {
        return liveData;
    }

    /**
     * 从 Room 读取
     *
     * @return
     */
    protected LiveData<ResultType> loadFromRoom() {
        return null;
    }

    /**
     * 更新数据到 Room
     *
     * @param resultType
     */
    protected void updateToRoom(ResultType resultType) {
    }

    /**
     * 是否需要从远程获取数据，一般缓存数据失效设置为 true 即可
     *
     * @param cache
     * @return
     */
    protected abstract boolean shouldFetch(@Nullable ResultType cache);

    /**
     * 获取远程数据 API
     *
     * @return
     */
    protected abstract Call<ResponseType> fetchFromNetwork();
}