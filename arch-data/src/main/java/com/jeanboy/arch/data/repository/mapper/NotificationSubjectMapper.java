package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.notifications.NotificationSubjectModel;
import com.jeanboy.arch.data.net.entity.notifications.NotificationSubjectEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/6/4 17:58
 */
public class NotificationSubjectMapper extends MapperHandler<NotificationSubjectEntity, NotificationSubjectModel> {

    @Override
    protected NotificationSubjectModel transform(NotificationSubjectEntity notificationSubjectEntity) {
        NotificationSubjectModel notificationSubjectModel = new NotificationSubjectModel();
        notificationSubjectModel.setType(notificationSubjectEntity.getType());
        notificationSubjectModel.setUrl(notificationSubjectEntity.getUrl());
        notificationSubjectModel.setTitle(notificationSubjectEntity.getTitle());
        notificationSubjectModel.setLatestCommentUrl(notificationSubjectEntity.getLatestCommentUrl());
        return notificationSubjectModel;
    }
}
