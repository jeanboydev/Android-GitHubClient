package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.app.github.config.AppSettings;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.repository.ReposRepository;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class RepositoryInfoViewModel extends ViewModel {

    private ReposRepository reposRepository = new ReposRepository();

    @Inject
    public RepositoryInfoViewModel() {
    }

    public LiveData<RepositoryEntity> getReposInfo(String username, String repos) {
        return reposRepository.getReposInfo(AppSettings.getAccessToken(), username, repos);
    }

    public LiveData<String> getReadMeHTML(String username, String repos) {
        String url = "https://api.github.com/repos/" + username + "/" + repos + "/readme";
        return reposRepository.getReadMeHTML(url);
    }
}