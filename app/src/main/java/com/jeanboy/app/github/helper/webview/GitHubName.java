

package com.jeanboy.app.github.helper.webview;

import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by ThirtyDegreesRay on 2017/10/30 13:49:02
 */

public class GitHubName {

    private String url;
    private String userName ;
    private String repoName ;

    private static final String GITHUB_BASE_URL_PATTERN_STR = "(https://)?(http://)?(www.)?github.com";
    private static final Pattern GITHUB_URL_PATTERN = Pattern.compile(GITHUB_BASE_URL_PATTERN_STR + "(.)*");
    public static boolean isGitHubUrl(@NonNull String url){
        return GITHUB_URL_PATTERN.matcher(url).matches();
    }

    public static GitHubName fromUrl(@NonNull String url){
        if(!isGitHubUrl(url)) return null;
        GitHubName gitHubName = new GitHubName();
        url = url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
        gitHubName.url = url;
        try{
            Uri uri = Uri.parse(url);
            ArrayList<String> list = new ArrayList<>(uri.getPathSegments());
            list.remove("repos");
            if(list.size() > 0) gitHubName.userName = list.get(0);
            if(list.size() > 1) gitHubName.repoName = list.get(1);
        }catch (Exception e){

        }
        return gitHubName;
    }

    public String getUserName() {
        return userName;
    }

    public String getRepoName() {
        return repoName;
    }


}
