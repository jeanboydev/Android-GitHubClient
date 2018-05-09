package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jeanboy.arch.data.cache.database.model.ReceivedEventModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.ReceivedEventEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.EventService;
import com.jeanboy.arch.data.repository.mapper.ReceivedEventMapper;

import java.util.List;

import retrofit2.Call;

/**
 * Created by jeanboy on 2018/5/7.
 */
public class EventRepository {

    private AppDatabase database;
    private EventService eventService;

    public EventRepository() {
        database = DBManager.getInstance().getDatabase();
        eventService = NetManager.getInstance().create(EventService.BASE_URL, EventService.class);
    }

    public LiveData<List<ReceivedEventModel>> getReceivedEvents(String accessToken, String username, int page) {
        MutableLiveData<List<ReceivedEventModel>> liveData = new MutableLiveData<>();
        Call<List<ReceivedEventEntity>> call = eventService.getReceivedEvents(accessToken, username, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<ReceivedEventEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<ReceivedEventEntity>> response) {
                        List<ReceivedEventEntity> body = response.getBody();
                        List<ReceivedEventModel> receivedEventList = new ReceivedEventMapper().transform(body);
                        liveData.setValue(receivedEventList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }
}
