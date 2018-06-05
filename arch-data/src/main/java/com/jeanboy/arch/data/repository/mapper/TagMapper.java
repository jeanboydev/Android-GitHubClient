package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.TagModel;
import com.jeanboy.arch.data.cache.database.model.received.CommitModel;
import com.jeanboy.arch.data.net.entity.TagEntity;
import com.jeanboy.arch.data.net.entity.received.CommitEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/6/5 15:36
 */
public class TagMapper extends MapperHandler<TagEntity, TagModel> {
    @Override
    protected TagModel transform(TagEntity tagEntity) {
        TagModel tagModel = new TagModel();
        tagModel.setName(tagEntity.getName());
        tagModel.setNodeId(tagEntity.getNodeId());
        tagModel.setTarballUrl(tagEntity.getTarballUrl());
        tagModel.setZipballUrl(tagEntity.getZipballUrl());
        CommitEntity commitEntity = tagEntity.getCommit();
        if (commitEntity != null) {
            CommitModel commitModel = new CommitMapper().transform(commitEntity);
            tagModel.setCommit(commitModel);
        }
        return tagModel;
    }
}
