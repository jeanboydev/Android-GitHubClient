package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.events.UserEventModel;
import com.jeanboy.arch.data.cache.database.model.received.ActorModel;
import com.jeanboy.arch.data.cache.database.model.received.OrganizationModel;
import com.jeanboy.arch.data.cache.database.model.received.PayLoadModel;
import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.events.UserEventEntity;
import com.jeanboy.arch.data.net.entity.received.ActorEntity;
import com.jeanboy.arch.data.net.entity.received.OrganizationEntity;
import com.jeanboy.arch.data.net.entity.received.PayLoadEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/5/10 14:38
 */
public class UserEventsMapper extends MapperHandler<UserEventEntity, UserEventModel> {

    @Override
    protected UserEventModel transform(UserEventEntity userEventEntity) {
        UserEventModel userEventModel = new UserEventModel();
        userEventModel.setId(userEventEntity.getId());
        userEventModel.setPublic(userEventEntity.isPublic());
        userEventModel.setType(userEventEntity.getType());
        userEventModel.setCreated_at(userEventEntity.getCreated_at());

        ActorEntity actorEntity = userEventEntity.getActor();
        if (actorEntity != null) {
            ActorModel actorModel = new ActorModel();
            actorModel.setId(actorEntity.getId());
            actorModel.setAvatarUrl(actorEntity.getAvatar_url());
            actorModel.setDisplayLogin(actorEntity.getDisplay_login());
            actorModel.setGravatarId(actorEntity.getGravatar_id());
            actorModel.setLogin(actorEntity.getLogin());
            actorModel.setUrl(actorEntity.getUrl());
            userEventModel.setActor(actorModel);
        }

        RepositoryEntity repositoryEntity = userEventEntity.getRepo();
        if (repositoryEntity != null) {
            RepositoryModel repositoryModel = new RepositoryModel();
            repositoryModel.setId(repositoryEntity.getId());
            repositoryModel.setName(repositoryEntity.getName());
            repositoryModel.setUrl(repositoryEntity.getUrl());
            userEventModel.setRepo(repositoryModel);
        }

        PayLoadEntity payLoadEntity = userEventEntity.getPayload();
        if (payLoadEntity != null) {
            PayLoadModel payLoadModel = new PayLoadModel();
            payLoadModel.setAction(payLoadEntity.getAction());
            userEventModel.setPayload(payLoadModel);
        }

        OrganizationEntity organizationEntity = userEventEntity.getOrg();
        if (organizationEntity != null) {
            OrganizationModel organizationModel = new OrganizationModel();
            organizationModel.setId(organizationEntity.getId());
            organizationModel.setAvatarUrl(organizationEntity.getAvatar_url());
            organizationModel.setGravatarId(organizationEntity.getGravatar_id());
            organizationModel.setLogin(organizationEntity.getLogin());
            organizationModel.setUrl(organizationEntity.getUrl());
            userEventModel.setOrg(organizationModel);
        }

        return null;
    }
}
