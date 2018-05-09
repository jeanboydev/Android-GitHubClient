package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.app.github.config.AppSettings;
import com.jeanboy.arch.data.cache.database.model.ReceivedEventModel;
import com.jeanboy.arch.data.repository.EventRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2018/5/7.
 */
public class MainHomeViewModel extends ViewModel {

    @Inject
    public MainHomeViewModel() {
    }

    private LiveData<List<ReceivedEventModel>> receivedEventList;

    private EventRepository eventRepository = new EventRepository();

    public LiveData<List<ReceivedEventModel>> request(int page) {
        return eventRepository.getReceivedEvents(AppSettings.getAccessToken(), AppSettings.getUsername(), page);
    }
}
