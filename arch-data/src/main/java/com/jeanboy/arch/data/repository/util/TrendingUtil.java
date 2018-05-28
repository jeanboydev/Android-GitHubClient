package com.jeanboy.arch.data.repository.util;

import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class TrendingUtil {

    public static List<RepositoryEntity> getTrendingRepos(Document document) {
        List<RepositoryEntity> dataList = new ArrayList<>();
        try {
            Elements elements = document.getElementsByClass("col-12 d-block width-full py-4 border-bottom");
            if (elements.size() > 0) {
                for (Element element : elements) {
                    dataList.add(getRepository(element));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    private static RepositoryEntity getRepository(Element element) throws Exception {
        String fullName = element.select("div > h3 > a").attr("href");
        fullName = fullName.substring(1);
        String owner = fullName.substring(0, fullName.lastIndexOf("/"));
        String repoName = fullName.substring(fullName.lastIndexOf("/") + 1);

        Element descElement = element.select("div > p").first();
        StringBuilder desc = new StringBuilder("");
        for (TextNode textNode : descElement.textNodes()) {
            desc.append(textNode.getWholeText());
        }

        Element numElement = element.getElementsByClass("f6 text-gray mt-2").first();
        String language = "";
        Elements languageElements = numElement.select("span > span");
        if (languageElements.size() > 0) {
            language = numElement.select("span > span").get(1).textNodes().get(0).toString().trim();
        }
        String starNumStr = numElement.select("a").get(0).textNodes().get(1).toString()
                .replaceAll(" ", "").replaceAll(",", "");
        String forkNumStr = numElement.select("a").get(1).textNodes().get(1).toString()
                .replaceAll(" ", "").replaceAll(",", "");
        Element periodElement = numElement.getElementsByClass("d-inline-block float-sm-right").first();
        String periodNumStr = "0";
        if (periodElement != null) {
            periodNumStr = periodElement.childNodes().get(2).toString().trim();
            periodNumStr = periodNumStr.substring(0, periodNumStr.indexOf(" "))
                    .replaceAll(",", "");
        }

        RepositoryEntity repo = new RepositoryEntity();
        repo.setFull_name(fullName);
        repo.setName(repoName);
        UserInfoEntity user = new UserInfoEntity();
        user.setLogin(owner);
        repo.setOwner(user);

        repo.setDescription(desc.toString().trim().replaceAll("\n", ""));
        repo.setStargazers_count(Integer.parseInt(starNumStr));
        repo.setForks_count(Integer.parseInt(forkNumStr));
        repo.setLanguage(language);
        repo.setPeriod_stargazers_count(Integer.parseInt(periodNumStr));
        return repo;

    }
}
