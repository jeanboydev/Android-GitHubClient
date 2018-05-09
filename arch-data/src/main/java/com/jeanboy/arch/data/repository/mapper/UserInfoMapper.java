package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;
import com.jeanboy.arch.data.repository.util.DateUtil;

/**
 * Created by jeanboy on 2018/5/3.
 */
public class UserInfoMapper extends MapperHandler<UserInfoEntity, UserInfoModel> {

    @Override
    public UserInfoModel transform(UserInfoEntity userInfoEntity) {
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setId(userInfoEntity.getId());
        userInfoModel.setLogin(userInfoEntity.getLogin());
        userInfoModel.setCompany(userInfoEntity.getCompany());
        userInfoModel.setBlog(userInfoEntity.getBlog());
        userInfoModel.setLocation(userInfoEntity.getLocation());
        userInfoModel.setHireable(userInfoEntity.getHireable());
        userInfoModel.setBio(userInfoEntity.getBio());
        userInfoModel.setPublicRepos(userInfoEntity.getPublic_repos());
        userInfoModel.setPublicGists(userInfoEntity.getPublic_gists());
        userInfoModel.setFollowers(userInfoEntity.getFollowers());
        userInfoModel.setFollowing(userInfoEntity.getFollowing());
        userInfoModel.setAvatarUrl(userInfoEntity.getAvatar_url());
        userInfoModel.setGravatarId(userInfoEntity.getGravatar_id());
        userInfoModel.setUrl(userInfoEntity.getUrl());
        userInfoModel.setHtmUrl(userInfoEntity.getUrl());
        userInfoModel.setFollowersUrl(userInfoEntity.getFollowers_url());
        userInfoModel.setFollowingUrl(userInfoEntity.getFollowing_url());
        userInfoModel.setGistsUrl(userInfoEntity.getGists_url());
        userInfoModel.setStarredUrl(userInfoEntity.getStarred_url());
        userInfoModel.setSubscriptionsUrl(userInfoEntity.getSubscriptions_url());
        userInfoModel.setOrganizationsUrl(userInfoEntity.getOrganizations_url());
        userInfoModel.setReposUrl(userInfoEntity.getRepos_url());
        userInfoModel.setEventsUrl(userInfoEntity.getEvents_url());
        userInfoModel.setReceivedEventsUrl(userInfoEntity.getReceived_events_url());
        userInfoModel.setType(userInfoEntity.getType());
        userInfoModel.setSiteAdmin(userInfoEntity.isSite_admin());
        userInfoModel.setCreatedAt(DateUtil.formatUTC(userInfoEntity.getCreated_at()).getTime());
        userInfoModel.setUpdatedAt(DateUtil.formatUTC(userInfoEntity.getUpdated_at()).getTime());
        return userInfoModel;
    }
}
