package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.app.github.config.AppSettings;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.repository.ReposRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class UserRepositoryListViewModel extends ViewModel {

    private ReposRepository reposRepository = new ReposRepository();

    @Inject
    public UserRepositoryListViewModel() {
    }

    public LiveData<List<RepositoryEntity>> getReposList(String username, int page) {
        return reposRepository.getReposList(AppSettings.getAccessToken(), username, page);
    }
}