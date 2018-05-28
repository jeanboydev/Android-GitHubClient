package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.repository.GitHubWebRepository;
import com.jeanboy.arch.data.repository.params.TrendingParams;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2018/5/7.
 */
public class MainTrendingViewModel extends ViewModel {

    @Inject
    public MainTrendingViewModel() {
    }

    private GitHubWebRepository gitHubWebRepository = new GitHubWebRepository();

    public LiveData<List<RepositoryEntity>> request(TrendingParams params) {
        return gitHubWebRepository.getTrending(params);
    }
}
