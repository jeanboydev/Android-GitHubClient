package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.cache.database.model.received.OrganizationModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.jeanboy.arch.data.net.entity.received.OrganizationEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.OrganizationService;
import com.jeanboy.arch.data.repository.mapper.OrganizationMapper;
import com.jeanboy.arch.data.repository.mapper.UserInfoMapper;

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

    public LiveData<List<OrganizationModel>> getUserOrgs(String accessToken, String username, int page) {
        MutableLiveData<List<OrganizationModel>> liveData = new MutableLiveData<>();
        Call<List<OrganizationEntity>> call = organizationService.getUserOrgs("token " + accessToken, username, page);
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

    public LiveData<List<UserInfoModel>> getOrgMembers(String accessToken, String org, int page) {
        MutableLiveData<List<UserInfoModel>> liveData = new MutableLiveData<>();
        Call<List<UserInfoEntity>> call = organizationService.getOrgMembers(accessToken, org, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<UserInfoEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<UserInfoEntity>> response) {
                        List<UserInfoEntity> body = response.getBody();
                        List<UserInfoModel> userInfoModelList = new UserInfoMapper().transform(body);
                        liveData.setValue(userInfoModelList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getOrgMembers", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

}
