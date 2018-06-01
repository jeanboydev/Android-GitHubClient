package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.LabelModel;
import com.jeanboy.arch.data.net.entity.LabelEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/6/1 16:15
 */
public class LabelMapper extends MapperHandler<LabelEntity, LabelModel> {
    @Override
    protected LabelModel transform(LabelEntity labelEntity) {
        LabelModel labelModel = new LabelModel();
        labelModel.setColor(labelEntity.getColor());
        labelModel.setDefault(labelEntity.isDefault());
        labelModel.setId(labelEntity.getId());
        labelModel.setName(labelEntity.getName());
        labelModel.setNodeId(labelEntity.getNodeId());
        labelModel.setUrl(labelEntity.getUrl());
        return labelModel;
    }
}
