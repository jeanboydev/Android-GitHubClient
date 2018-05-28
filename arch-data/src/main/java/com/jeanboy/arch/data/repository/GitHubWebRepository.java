package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.TopicEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.GitHubWebService;
import com.jeanboy.arch.data.repository.params.TrendingParams;
import com.jeanboy.arch.data.repository.util.TopicUtil;
import com.jeanboy.arch.data.repository.util.TrendingUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class GitHubWebRepository {

    private AppDatabase database;
    private GitHubWebService gitHubWebService;

    public GitHubWebRepository() {
        database = DBManager.getInstance().getDatabase();
        gitHubWebService = NetManager.getInstance().create(GitHubWebService.BASE_URL, GitHubWebService.class);
    }

    public LiveData<List<TopicEntity>> getTopics() {
        MutableLiveData<List<TopicEntity>> liveData = new MutableLiveData<>();
        Call<String> call = gitHubWebService.getTopics(false);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<String>>() {
                    @Override
                    public void onSuccess(ResponseData<String> response) {
                        String body = response.getBody();
                        Document document = Jsoup.parse(body, GitHubWebService.BASE_URL);
                        List<TopicEntity> dataList = new ArrayList<>();
                        dataList.addAll(TopicUtil.getTopTopics(document));
                        dataList.addAll(TopicUtil.getItemTopics(document));
                        liveData.setValue(dataList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<RepositoryEntity>> getTrending(TrendingParams params) {
        MutableLiveData<List<RepositoryEntity>> liveData = new MutableLiveData<>();
        Call<String> call = params == null ? gitHubWebService.getTrending() :
                gitHubWebService.getTrending(params.getLanguage().getValue(), params.getPeriod().name());
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<String>>() {
                    @Override
                    public void onSuccess(ResponseData<String> response) {
                        String body = response.getBody();
                        Document document = Jsoup.parse(body, GitHubWebService.BASE_URL);
                        List<RepositoryEntity> dataList = TrendingUtil.getTrendingRepos(document);
                        liveData.setValue(dataList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }
}
