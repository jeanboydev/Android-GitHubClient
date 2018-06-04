package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.notifications.NotificationModel;
import com.jeanboy.arch.data.cache.database.model.notifications.NotificationSubjectModel;
import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.notifications.NotificationEntity;
import com.jeanboy.arch.data.net.entity.notifications.NotificationSubjectEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/6/4 17:54
 */
public class NotificationMapper extends MapperHandler<NotificationEntity, NotificationModel> {

    @Override
    protected NotificationModel transform(NotificationEntity notificationEntity) {
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setId(notificationEntity.getId());
        notificationModel.setLastReadAt(notificationEntity.getLastReadAt());
        notificationModel.setReason(notificationEntity.getReason());
        notificationModel.setUrl(notificationEntity.getUrl());
        notificationModel.setUnread(notificationEntity.isUnread());
        notificationModel.setUpdatedAt(notificationEntity.getUpdatedAt());

        RepositoryEntity repositoryEntity = notificationEntity.getRepository();
        if (repositoryEntity != null) {
            RepositoryModel repositoryModel =
                    new RepositoryMapper().transform(repositoryEntity);
            notificationModel.setRepository(repositoryModel);
        }

        NotificationSubjectEntity subjectEntity = notificationEntity.getSubject();
        if (subjectEntity != null) {
            NotificationSubjectModel subjectModel =
                    new NotificationSubjectMapper().transform(subjectEntity);
            notificationModel.setSubject(subjectModel);
        }

        return notificationModel;
    }
}
