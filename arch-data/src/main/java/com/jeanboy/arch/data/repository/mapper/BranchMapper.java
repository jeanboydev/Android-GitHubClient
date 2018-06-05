package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.BranchModel;
import com.jeanboy.arch.data.cache.database.model.received.CommitModel;
import com.jeanboy.arch.data.net.entity.BranchEntity;
import com.jeanboy.arch.data.net.entity.received.CommitEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/6/5 15:18
 */
public class BranchMapper extends MapperHandler<BranchEntity, BranchModel> {
    @Override
    protected BranchModel transform(BranchEntity branchEntity) {
        BranchModel branchModel = new BranchModel();
        branchModel.setName(branchEntity.getName());
        CommitEntity commitEntity = branchEntity.getCommit();
        if (commitEntity != null) {
            CommitModel commitModel = new CommitMapper().transform(commitEntity);
            branchModel.setCommit(commitModel);
        }
        return branchModel;
    }
}
