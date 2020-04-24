package com.zc.springbootbase.utils;

import java.util.Map;


/**
 * @author zhanchang
 * @version 1.0
 * @date 2020/3/7 8:46
 */
public class JsonUtilsCustomize {
//    public static void putAllIdToMap(String json, String name, String id) {
//
//        String value = "";
//        String key = "";
//        Map<String, Object> map = (Map<String, Object>) JsonUtils.jsonToMap(json);
//        for (Entry<String, Object> key1 : map.entrySet()) {
//            JsonParser parser = new JsonParser();
//            JsonElement jsonElement = parser.parse(JsonUtils.object2Json(key1.getValue()));
//            if (jsonElement.isJsonObject()) {
//                JsonObject jsonObject = jsonElement.getAsJsonObject();
//                String jsonstr = jsonObject.toString();
//                putAllIdToMap(jsonstr, name, id);
//            } else if (jsonElement.isJsonArray()) {
//                List<Map<String, Object>> liatMap = (List<Map<String, Object>>) key1.getValue();
//                for (Map<String, Object> map2 : liatMap) {
//                    String jsonstr = JsonUtils.object2Json(map2);
//                    putAllIdToMap(jsonstr, name, id);
//                }
//            } else {
//                if (key1.getKey().equals(name)) {
//                    if (!"".equals(key1.getValue()) && null != key1.getValue()) {
//                        key = key1.getValue().toString();
//                    }
//
//                }
//                if (key1.getKey().equals(id)) {
//                    if (!"".equals(key1.getValue()) && null != key1.getValue()) {
//                        value = key1.getValue().toString();
//                    }
//                }
//                if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
//                    idMap.put(key, value);
//                    break;
//                }
//            }
//        }
//    }
}
