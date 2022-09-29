package com.vuongkma.luci.helpers;

import com.vuongkma.luci.entities.UserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseFormat {
    public static Map<String, String> build(String message) {
        Map<String, String> map = new HashMap<>();
        map.put("message", message);

        return map;
    }

    public static Map<String, Object> build(UserEntity userEntity, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("info", userEntity);

        return map;
    }

    public static Map<String, Object> build(Long id, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("message", message);

        return map;
    }

    public static Map<String, Object> build(List<UserEntity> users, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", users);
        map.put("message", message);

        return map;
    }
}
