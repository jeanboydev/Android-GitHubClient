package com.jeanboy.arch.data.repository.handler;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jeanboy.arch.data.cache.manager.DataExecutors;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.manager.NetManager;

import retrofit2.Call;

public abstract class RepositoryHandler<ResponseType, ResultType> {

    private final static String TAG = RepositoryHandler.class.getSimpleName();

    private MediatorLiveData<ResultType> liveData = new MediatorLiveData<>();
    private MediatorLiveData<ResultType> watcher = new MediatorLiveData<>();

    public RepositoryHandler() {
        Log.d(TAG, "== 开始读取数据库缓存 ==>>");
        LiveData<ResultType> roomData = loadCache();//读取缓存数据

        watcher.addSource(roomData, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType resultType) {
                watcher.removeSource(roomData);
                Log.d(TAG, "== 缓存已经读取 ==>>");
                if (RepositoryHandler.this.shouldFetch(resultType)) {
                    RepositoryHandler.this.loadRemote();
                    return;
                }
                Log.d(TAG, "<<== 缓存未失效，返回数据 ==");
                liveData.setValue(resultType);
            }
        });
    }

    /**
     * 读取缓存数据
     */
    private LiveData<ResultType> loadCache() {
        return loadFromRoom();
    }

    /**
     * 请求 API 数据
     */
    private void loadRemote() {
        Call<ResponseType> fromNetwork = fetchFromNetwork();
        if (fromNetwork == null) {
            Log.w(TAG, "== 缓存已经失效，获取远程数据未实现 ==>>");
            return;
        }
        Log.d(TAG, "== 缓存已经失效，开始获取远程数据 ==>>");
        NetManager.getInstance().request(new RequestParams<>(fromNetwork),
                new RequestCallback<ResponseData<ResponseType>>() {
                    @Override
                    public void onSuccess(ResponseData<ResponseType> response) {
                        Log.d(TAG, "<<== 获取远程数据成功 ==");
                        ResponseType responseType = response.getBody();
                        ResultType resultType = toMapper(responseType);
                        liveData.setValue(resultType);
                        if (resultType == null) return;

                        Log.d(TAG, "<<== 数据不为空，缓存到数据库 ==");
                        saveToCache(resultType);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.w(TAG, "<<== 获取远程数据失败 ==");
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
    private ResultType toMapper(ResponseType responseType) {
        return onMapper().transform(responseType);
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
     * 是否需要从远程获取数据，缓存数据失效设为 true
     *
     * @param resultType
     * @return
     */
    protected abstract boolean shouldFetch(@Nullable ResultType resultType);

    /**
     * 获取远程数据 API
     *
     * @return
     */
    protected abstract Call<ResponseType> fetchFromNetwork();

    /**
     * 数据转换
     *
     * @return
     */
    protected abstract MapperHandler<ResponseType, ResultType> onMapper();
}