package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jeanboy.arch.data.cache.database.model.received.OrganizationModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.received.OrganizationEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.OrganizationService;
import com.jeanboy.arch.data.repository.mapper.OrganizationMapper;

import java.util.List;

import retrofit2.Call;

/**
 * Created by 乔晓松 on 2018/5/10 16:49
 */
public class OrganizationRepository {

    private AppDatabase database;
    private OrganizationService organizationService;

    public OrganizationRepository() {
        database = DBManager.getInstance().getDatabase();
        organizationService = NetManager.getInstance().create(OrganizationService.BASE_URL, OrganizationService.class);
    }

    public LiveData<List<OrganizationModel>> getReposInfo(String accessToken, String username, int page) {
        MutableLiveData<List<OrganizationModel>> liveData = new MutableLiveData<>();
        Call<List<OrganizationEntity>> call = organizationService.getRepos("token " + accessToken, username, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<OrganizationEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<OrganizationEntity>> response) {
                        List<OrganizationEntity> body = response.getBody();
                        List<OrganizationModel> organizationModelList = new OrganizationMapper().transform(body);
                        liveData.setValue(organizationModelList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

}
