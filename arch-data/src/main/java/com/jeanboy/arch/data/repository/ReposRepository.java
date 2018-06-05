package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jeanboy.arch.data.cache.database.model.BranchModel;
import com.jeanboy.arch.data.cache.database.model.FileModel;
import com.jeanboy.arch.data.cache.database.model.TagModel;
import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.BranchEntity;
import com.jeanboy.arch.data.net.entity.FileEntity;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.TagEntity;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.FileService;
import com.jeanboy.arch.data.net.service.ReposService;
import com.jeanboy.arch.data.repository.mapper.BranchMapper;
import com.jeanboy.arch.data.repository.mapper.FileMapper;
import com.jeanboy.arch.data.repository.mapper.RepositoryMapper;
import com.jeanboy.arch.data.repository.mapper.TagMapper;
import com.jeanboy.arch.data.repository.mapper.UserInfoMapper;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jeanboy on 2018/5/7.
 */
public class ReposRepository {

    private AppDatabase database;
    private ReposService reposService;
    private ReposService reposStringService;
    private FileService fileService;

    public ReposRepository() {
        database = DBManager.getInstance().getDatabase();
        this.reposStringService = NetManager.getInstance().createForString(ReposService.BASE_URL, ReposService.class);
        this.reposService = NetManager.getInstance().createForJSON(ReposService.BASE_URL, ReposService.class);
        fileService = NetManager.getInstance().create(FileService.BASE_URL, FileService.class);
    }

    public LiveData<RepositoryEntity> getReposInfo(String accessToken, String username, String repos) {
        MutableLiveData<RepositoryEntity> liveData = new MutableLiveData<>();
        Call<RepositoryEntity> call = reposService.getReposInfo("token " + accessToken, username, repos);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<RepositoryEntity>>() {
                    @Override
                    public void onSuccess(ResponseData<RepositoryEntity> response) {
                        RepositoryEntity body = response.getBody();
                        liveData.setValue(body);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public MutableLiveData<List<RepositoryEntity>> getReposList(String accessToken, String username, int page) {
        MutableLiveData<List<RepositoryEntity>> liveData = new MutableLiveData<>();
        Call<List<RepositoryEntity>> call = reposService.getReposList("token " + accessToken, username, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<RepositoryEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<RepositoryEntity>> response) {
                        List<RepositoryEntity> body = response.getBody();
                        liveData.setValue(body);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<String> getReadMeHTML(String url) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        Call<String> call = fileService.getFileAsHtmlStream(false, url);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<String>>() {
                    @Override
                    public void onSuccess(ResponseData<String> response) {
                        Log.e("==========", "====onSuccess====");
                        String body = response.getBody();
                        liveData.setValue(body);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<RepositoryModel>> getStarredRepos(String accessToken, String username, int page, String sort, String direction) {
        Log.d("getStarredRepos", "accessToken: " + accessToken);
        MutableLiveData<List<RepositoryModel>> liveData = new MutableLiveData<>();
        Call<List<RepositoryEntity>> call = reposService.getStarredRepos("token " + accessToken, username, page, sort, direction);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<RepositoryEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<RepositoryEntity>> response) {
                        List<RepositoryEntity> body = response.getBody();
                        List<RepositoryModel> repositoryModelList = new RepositoryMapper().transform(body);
                        liveData.setValue(repositoryModelList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<RepositoryModel>> getUserRepos(String accessToken, int page, String type, String sort, String direction) {
        MutableLiveData<List<RepositoryModel>> liveData = new MutableLiveData<>();
        Call<List<RepositoryEntity>> call = reposService.getUserRepos("token " + accessToken, page, type, sort, direction);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<RepositoryEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<RepositoryEntity>> response) {
                        List<RepositoryEntity> body = response.getBody();
                        List<RepositoryModel> repositoryModelList = new RepositoryMapper().transform(body);
                        liveData.setValue(repositoryModelList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getUserRepos", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<Boolean> checkRepoStarred(String accessToken, String owner, String repo) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Response<ResponseBody>> call = reposService.checkRepoStarred("token " + accessToken, owner, repo);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Response<ResponseBody>>>() {
                    @Override
                    public void onSuccess(ResponseData<Response<ResponseBody>> response) {
                        Response<ResponseBody> body = response.getBody();
//                        List<RepositoryModel> repositoryModelList = new RepositoryMapper().transform(body);
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("checkRepoStarred", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

    public LiveData<Boolean> starRepo(String accessToken, String owner, String repo) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Response<ResponseBody>> call = reposService.starRepo("token " + accessToken, owner, repo);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Response<ResponseBody>>>() {
                    @Override
                    public void onSuccess(ResponseData<Response<ResponseBody>> response) {
                        Response<ResponseBody> body = response.getBody();
//                        List<RepositoryModel> repositoryModelList = new RepositoryMapper().transform(body);
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("starRepo", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

    public LiveData<Boolean> unstarRepo(String accessToken, String owner, String repo) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Response<ResponseBody>> call = reposService.unstarRepo("token " + accessToken, owner, repo);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Response<ResponseBody>>>() {
                    @Override
                    public void onSuccess(ResponseData<Response<ResponseBody>> response) {
                        Response<ResponseBody> body = response.getBody();
//                        List<RepositoryModel> repositoryModelList = new RepositoryMapper().transform(body);
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("unstarRepo", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

    public LiveData<Boolean> checkRepoWatched(String accessToken, String owner, String repo) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Response<ResponseBody>> call = reposService.checkRepoWatched("token " + accessToken, owner, repo);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Response<ResponseBody>>>() {
                    @Override
                    public void onSuccess(ResponseData<Response<ResponseBody>> response) {
                        Response<ResponseBody> body = response.getBody();
//                        List<RepositoryModel> repositoryModelList = new RepositoryMapper().transform(body);
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("checkRepoWatched", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

    public LiveData<Boolean> watchRepo(String accessToken, String owner, String repo) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Response<ResponseBody>> call = reposService.watchRepo("token " + accessToken, owner, repo);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Response<ResponseBody>>>() {
                    @Override
                    public void onSuccess(ResponseData<Response<ResponseBody>> response) {
                        Response<ResponseBody> body = response.getBody();
//                        List<RepositoryModel> repositoryModelList = new RepositoryMapper().transform(body);
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("watchRepo", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

    public LiveData<Boolean> unwatchRepo(String accessToken, String owner, String repo) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Response<ResponseBody>> call = reposService.unwatchRepo("token " + accessToken, owner, repo);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Response<ResponseBody>>>() {
                    @Override
                    public void onSuccess(ResponseData<Response<ResponseBody>> response) {
                        Response<ResponseBody> body = response.getBody();
//                        List<RepositoryModel> repositoryModelList = new RepositoryMapper().transform(body);
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("unwatchRepo", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

    public LiveData<String> getFileAsHtmlStream(String accessToken, String url) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        Call<String> call = reposStringService.getFileAsHtmlStream("token " + accessToken, url);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<String>>() {
                    @Override
                    public void onSuccess(ResponseData<String> response) {
                        String body = response.getBody();
                        liveData.setValue(body);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getFileAsHtmlStream", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<String> getFileAsStream(String accessToken, String url) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        Call<String> call = reposStringService.getFileAsStream("token " + accessToken, url);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<String>>() {
                    @Override
                    public void onSuccess(ResponseData<String> response) {
                        String body = response.getBody();
                        liveData.setValue(body);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getFileAsStream", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<FileModel>> getRepoFiles(String accessToken, String owner, String repo, String path, String branch) {
        MutableLiveData<List<FileModel>> liveData = new MutableLiveData<>();
        Call<List<FileEntity>> call = reposService.getRepoFiles("token " + accessToken, owner, repo, path, branch);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<FileEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<FileEntity>> response) {
                        List<FileEntity> body = response.getBody();
                        List<FileModel> list = new FileMapper().transform(body);
                        liveData.setValue(list);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getRepoFiles", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<BranchModel>> getBranches(String accessToken, String owner, String repo) {
        MutableLiveData<List<BranchModel>> liveData = new MutableLiveData<>();
        Call<List<BranchEntity>> call = reposService.getBranches("token " + accessToken, owner, repo);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<BranchEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<BranchEntity>> response) {
                        List<BranchEntity> body = response.getBody();
                        List<BranchModel> list = new BranchMapper().transform(body);
                        liveData.setValue(list);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getBranches", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<TagModel>> getTags(String accessToken, String owner, String repo) {
        MutableLiveData<List<TagModel>> liveData = new MutableLiveData<>();
        Call<List<TagEntity>> call = reposService.getTags("token " + accessToken, owner, repo);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<TagEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<TagEntity>> response) {
                        List<TagEntity> body = response.getBody();
                        List<TagModel> list = new TagMapper().transform(body);
                        liveData.setValue(list);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getTags", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<UserInfoModel>> getStargazers(String accessToken, String owner, String repo, int page) {
        MutableLiveData<List<UserInfoModel>> liveData = new MutableLiveData<>();
        Call<List<UserInfoEntity>> call = reposService.getStargazers("token " + accessToken, owner, repo, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<UserInfoEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<UserInfoEntity>> response) {
                        List<UserInfoEntity> body = response.getBody();
                        List<UserInfoModel> list = new UserInfoMapper().transform(body);
                        liveData.setValue(list);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getStargazers", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<UserInfoModel>> getWatchers(String accessToken, String owner, String repo, int page) {
        MutableLiveData<List<UserInfoModel>> liveData = new MutableLiveData<>();
        Call<List<UserInfoEntity>> call = reposService.getWatchers("token " + accessToken, owner, repo, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<UserInfoEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<UserInfoEntity>> response) {
                        List<UserInfoEntity> body = response.getBody();
                        List<UserInfoModel> list = new UserInfoMapper().transform(body);
                        liveData.setValue(list);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getWatchers", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<RepositoryModel> createFork(String accessToken, String owner, String repo) {
        MutableLiveData<RepositoryModel> liveData = new MutableLiveData<>();
        Call<RepositoryEntity> call = reposService.createFork("token " + accessToken, owner, repo);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<RepositoryEntity>>() {
                    @Override
                    public void onSuccess(ResponseData<RepositoryEntity> response) {
                        RepositoryEntity body = response.getBody();
                        RepositoryModel repositoryModel = new RepositoryMapper().transform(body);
                        liveData.setValue(repositoryModel);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("createFork", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<RepositoryModel>> getForks(String accessToken, String owner, String repo, int page) {
        MutableLiveData<List<RepositoryModel>> liveData = new MutableLiveData<>();
        Call<List<RepositoryEntity>> call = reposService.getForks("token " + accessToken, owner, repo, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<RepositoryEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<RepositoryEntity>> response) {
                        List<RepositoryEntity> body = response.getBody();
                        List<RepositoryModel> repositoryModel = new RepositoryMapper().transform(body);
                        liveData.setValue(repositoryModel);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("createFork", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

}
