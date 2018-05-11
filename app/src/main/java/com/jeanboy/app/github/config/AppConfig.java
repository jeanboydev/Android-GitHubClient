package com.jeanboy.app.github.config;

import com.jeanboy.app.github.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeanboy on 2018/4/27.
 */
public class AppConfig {

    public final static String CLIENT_ID = "17ecea64f7d686beaa7a";
    public final static String CLIENT_SECRET = "7e17dec12e7ab41ae43e8c6af684fdb5b8ab8ed5";
    public final static String REDIRECT_URI = "myhubapp://callback";

    //received event
    public final static String WATCH_EVENT = "WatchEvent";
    public final static String CREATE_EVENT = "CreateEvent";
    public final static String FORK_EVENT = "ForkEvent";
    public final static String MEMBER_EVENT = "MemberEvent";
    public final static String PUSH_EVENT = "PushEvent";
    public final static String PUBLIC_EVENT = "PublicEvent";
    private static Map<String, Integer> eventContentMap = new HashMap<>();

    static {
        eventContentMap.put(WATCH_EVENT, R.string.watch_event_content);
        eventContentMap.put(CREATE_EVENT, R.string.create_event_content);
        eventContentMap.put(FORK_EVENT, R.string.fork_event_content);
        eventContentMap.put(MEMBER_EVENT, R.string.member_event_content);
        eventContentMap.put(PUSH_EVENT, R.string.push_event_content);
        eventContentMap.put(PUBLIC_EVENT, R.string.public_event_content);
    }

    public static Integer getEventStringId(String event) {
        return eventContentMap.get(event);
    }
}
