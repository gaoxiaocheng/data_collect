package com.machine.record.util;

import java.util.HashMap;
import java.util.Map;

public final class ResultUtil {
    public static Map<String, Object> error(String code, Object o) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", o);
        return map;
    }

    public static Map<String, Object> sucess(Object o) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("data", o);
        return map;
    }
}
