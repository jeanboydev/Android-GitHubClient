package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.received.OrganizationModel;
import com.jeanboy.arch.data.net.entity.received.OrganizationEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/5/10 17:24
 */
public class OrganizationMapper extends MapperHandler<OrganizationEntity, OrganizationModel> {
    @Override
    protected OrganizationModel transform(OrganizationEntity organizationEntity) {
        OrganizationModel organizationModel = new OrganizationModel();
        organizationModel.setId(organizationEntity.getId());
        organizationModel.setUrl(organizationEntity.getUrl());
        organizationModel.setLogin(organizationEntity.getLogin());
        organizationModel.setGravatarId(organizationEntity.getGravatar_id());
        organizationModel.setAvatarUrl(organizationEntity.getAvatar_url());
        return organizationModel;
    }
}
