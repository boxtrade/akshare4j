package com.boxtrade.akshare4j.util;

/**
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2022/11/3 22:07
 */

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Created by acer-pc on 2018/6/19.
 */

public class GsonUtil {

    public static Gson gson;

    public static <T> T fromJson(String jsonStr, Class<T> tClass) {
        try {
            if (gson == null) {
                gson = new Gson();
            }
            return gson.fromJson(jsonStr, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        if (gson == null) {
            gson = new Gson();
        }
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json)
            .getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;

    }

    public static <T extends Object> T parseJson(String jsonStr, Class<T> tClass) {
        try {
            if (gson == null) {
                gson = new Gson();
            }
            return gson.fromJson(jsonStr, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object object) {
        try {
            if (gson == null) {
                gson = new Gson();
            }
            return gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}