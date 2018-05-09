package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.app.github.config.AppSettings;
import com.jeanboy.arch.data.cache.database.model.ReceivedEventModel;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.repository.EventRepository;
import com.jeanboy.arch.data.repository.ReposRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2018/5/7.
 */
public class MainHomeViewModel extends ViewModel {

    @Inject
    public MainHomeViewModel() {
    }

    private EventRepository eventRepository = new EventRepository();
    private ReposRepository reposRepository = new ReposRepository();

    public LiveData<List<ReceivedEventModel>> request(int page) {
        return eventRepository.getReceivedEvents(AppSettings.getAccessToken(), AppSettings.getUsername(), page);
    }

    public LiveData<RepositoryEntity> getReposInfo(String username, String repos) {
        return reposRepository.getReposInfo(AppSettings.getAccessToken(), username, repos);
    }
}
