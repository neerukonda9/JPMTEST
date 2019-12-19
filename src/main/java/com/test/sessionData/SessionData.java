package com.test.sessionData;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SessionData {

    private Map<String, Object> sessionData = new HashMap<String, Object>();
    private Map<String, List> list = new HashMap<>();

    public void setData(String key, Object value) {
        sessionData.put(key, value);
    }

    public <T> T getData(String key) {
        return (T) sessionData.get(key);
    }

    public void cleanScenarioSession() {
        sessionData.clear();
        list.clear();
    }

}

