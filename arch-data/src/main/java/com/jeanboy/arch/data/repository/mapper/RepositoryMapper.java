package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/5/10 17:18
 */
public class RepositoryMapper extends MapperHandler<RepositoryEntity, RepositoryModel> {

    @Override
    protected RepositoryModel transform(RepositoryEntity repositoryEntity) {
        RepositoryModel repositoryModel = new RepositoryModel();
        repositoryModel.setId(repositoryEntity.getId());
        repositoryModel.setUrl(repositoryEntity.getUrl());
        repositoryModel.setName(repositoryEntity.getName());

        return repositoryModel;
    }
}
