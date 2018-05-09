package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.ReceivedEventModel;
import com.jeanboy.arch.data.cache.database.model.received.ActorModel;
import com.jeanboy.arch.data.cache.database.model.received.OrganizationModel;
import com.jeanboy.arch.data.cache.database.model.received.PayLoadModel;
import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;
import com.jeanboy.arch.data.net.entity.ReceivedEventEntity;
import com.jeanboy.arch.data.net.entity.received.ActorEntity;
import com.jeanboy.arch.data.net.entity.received.OrganizationEntity;
import com.jeanboy.arch.data.net.entity.received.PayLoadEntity;
import com.jeanboy.arch.data.net.entity.received.RepositoryEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;
import com.jeanboy.arch.data.repository.util.DateUtil;

/**
 * Created by jeanboy on 2018/5/7.
 */
public class ReceivedEventMapper extends MapperHandler<ReceivedEventEntity, ReceivedEventModel> {


    @Override
    protected ReceivedEventModel transform(ReceivedEventEntity receivedEventEntity) {
        ReceivedEventModel receivedEventModel = new ReceivedEventModel();
        receivedEventModel.setId(receivedEventEntity.getId());
        receivedEventModel.setType(receivedEventEntity.getType());

        ActorEntity actorEntity = receivedEventEntity.getActor();
        if (actorEntity != null) {
            ActorModel actorModel = new ActorModel();
            actorModel.setId(actorEntity.getId());
            actorModel.setLogin(actorEntity.getLogin());
            actorModel.setDisplayLogin(actorEntity.getDisplay_login());
            actorModel.setGravatarId(actorEntity.getGravatar_id());
            actorModel.setUrl(actorEntity.getUrl());
            actorModel.setAvatarUrl(actorEntity.getAvatar_url());
            receivedEventModel.setActor(actorModel);
        }

        RepositoryEntity repositoryEntity = receivedEventEntity.getRepo();
        if (repositoryEntity != null) {
            RepositoryModel repositoryModel = new RepositoryModel();
            repositoryModel.setId(repositoryEntity.getId());
            repositoryModel.setName(repositoryEntity.getName());
            repositoryModel.setUrl(repositoryEntity.getUrl());
            receivedEventModel.setRepo(repositoryModel);
        }

        PayLoadEntity payLoadEntity = receivedEventEntity.getPayload();
        if (payLoadEntity != null) {
            PayLoadModel payLoadModel = new PayLoadModel();
            payLoadModel.setAction(payLoadEntity.getAction());
            payLoadModel.setForkee(payLoadEntity.getForkee());
            receivedEventModel.setPayload(payLoadModel);
        }

        OrganizationEntity organizationEntity = receivedEventEntity.getOrg();
        if (organizationEntity != null) {
            OrganizationModel organizationModel = new OrganizationModel();
            organizationModel.setId(organizationEntity.getId());
            organizationModel.setLogin(organizationEntity.getLogin());
            organizationModel.setAvatarUrl(organizationEntity.getAvatar_url());
            organizationModel.setGravatarId(organizationEntity.getGravatar_id());
            organizationModel.setUrl(organizationEntity.getUrl());
            receivedEventModel.setOrg(organizationModel);
        }

        receivedEventModel.setPublic(receivedEventEntity.isPublic());
        receivedEventModel.setCreatedAt(DateUtil.formatUTC(receivedEventEntity.getCreated_at()).getTime());
        return receivedEventModel;
    }
}
