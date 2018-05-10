package com.jeanboy.arch.data.net.entity.received;

/**
 * Created by jeanboy on 2018/5/10.
 */
public class CommitEntity {
    /**
     * {
     * "sha": "7efa6f992304e505ec6dbe847074776761636e12",
     * "author": {
     * "email": "coolspan@sina.cn",
     * "name": "coolspan"
     * },
     * "message": "Add the dynamic list interface to get the specified user(添加获取指定用户的动态列表接口)",
     * "distinct": true,
     * "url": "https://api.github.com/repos/jeanboydev/Android-GitHubClient/commits/7efa6f992304e505ec6dbe847074776761636e12"
     * }
     */

    private String sha;
    private String message;
    private boolean distinct;
    private String url;
    private AuthorEntity author;
}
