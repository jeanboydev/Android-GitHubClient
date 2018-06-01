package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.PullRequestModel;
import com.jeanboy.arch.data.net.entity.PullRequestEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/6/1 16:12
 */
public class PullRequestMapper extends MapperHandler<PullRequestEntity, PullRequestModel> {

    @Override
    protected PullRequestModel transform(PullRequestEntity pullRequestEntity) {
        PullRequestModel pullRequestModel = new PullRequestModel();
        pullRequestModel.setDiffUrl(pullRequestEntity.getDiffUrl());
        pullRequestModel.setHtmlUrl(pullRequestEntity.getHtmlUrl());
        pullRequestModel.setPatchUrl(pullRequestEntity.getPatchUrl());
        pullRequestModel.setUrl(pullRequestEntity.getUrl());
        return pullRequestModel;
    }

}
