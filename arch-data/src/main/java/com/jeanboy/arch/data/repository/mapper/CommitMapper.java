package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.received.AuthorModel;
import com.jeanboy.arch.data.cache.database.model.received.CommitModel;
import com.jeanboy.arch.data.net.entity.received.AuthorEntity;
import com.jeanboy.arch.data.net.entity.received.CommitEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/6/5 15:29
 */
public class CommitMapper extends MapperHandler<CommitEntity, CommitModel> {

    @Override
    protected CommitModel transform(CommitEntity commitEntity) {
        CommitModel commitModel = new CommitModel();
        commitModel.setDistinct(commitEntity.isDistinct());
        commitModel.setSha(commitEntity.getSha());
        commitModel.setMessage(commitEntity.getMessage());
        commitModel.setUrl(commitEntity.getUrl());
        AuthorEntity authorEntity = commitEntity.getAuthor();
        if (authorEntity != null) {
            AuthorModel authorModel = new AuthorModel();
            authorModel.setEmail(authorEntity.getEmail());
            authorModel.setName(authorEntity.getName());
        }
        return commitModel;
    }
}
