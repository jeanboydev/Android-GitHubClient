package com.jeanboy.arch.data.repository.util;

import com.jeanboy.arch.data.net.entity.TopicEntity;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class TopicUtil {

    public static List<TopicEntity> getTopTopics(Document document) {
        List<TopicEntity> dataList = new ArrayList<>();
        try {
            Elements elements = document.getElementsByClass("col-12 col-sm-6 col-md-4 mb-4");
            for (Element element : elements) {
                Element idElement = element.select("a").first();
                Element imageElement = element.select("a > img").first();
                Element titleElement = element.select("a > p").get(0);
                Element descElement = element.select("a > p").get(1);

                String id = idElement.attr("href");
                id = id.substring(id.lastIndexOf("/") + 1);
                String name = titleElement.textNodes().get(0).text();
                String desc = descElement.textNodes().get(0).text();
                String image = imageElement == null ? null : imageElement.attr("src");

                TopicEntity topicEntity = new TopicEntity(id, name, desc, image);
                dataList.add(topicEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static List<TopicEntity> getItemTopics(Document document) {
        List<TopicEntity> dataList = new ArrayList<>();
        try {
            Elements topElements = document.getElementsByClass("py-4 border-bottom");
            for (Element element : topElements) {
                Element idElement = element.select("a").first();
                Element imageElement = element.select("a > img").first();
                Element titleElement = element.select("a > div > p").get(0);
                Element descElement = element.select("a > div > p").get(1);

                String id = idElement.attr("href");
                id = id.substring(id.lastIndexOf("/") + 1);
                String name = titleElement.textNodes().get(0).text();
                String desc = descElement.textNodes().get(0).text();
                String image = imageElement == null ? null : imageElement.attr("src");

                TopicEntity topicEntity = new TopicEntity(id, name, desc, image);
                dataList.add(topicEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
