package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.FileModel;
import com.jeanboy.arch.data.net.entity.FileEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/6/5 15:02
 */
public class FileMapper extends MapperHandler<FileEntity,FileModel> {

    @Override
    protected FileModel transform(FileEntity fileEntity) {
        FileModel fileModel = new FileModel();
        fileModel.setName(fileEntity.getName());
        fileModel.setType(fileEntity.getType());
        fileModel.setDownloadUrl(fileEntity.getDownloadUrl());
        fileModel.setGitUrl(fileEntity.getGitUrl());
        fileModel.setHtmlUrl(fileEntity.getHtmlUrl());
        fileModel.setUrl(fileEntity.getUrl());
        fileModel.setSize(fileEntity.getSize());
        fileModel.setPath(fileEntity.getPath());
        fileModel.setSha(fileEntity.getSha());

        return fileModel;
    }
}
