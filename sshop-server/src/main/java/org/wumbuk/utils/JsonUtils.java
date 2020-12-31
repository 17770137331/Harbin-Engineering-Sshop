package org.wumbuk.utils;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * @auther PeihaoYang
 * @date 2020/9/28 - 14:55
 */
//工具类、用来转换json字符串

/**
 * 可能值得注意的就是T范型、通配符？
 */
public class JsonUtils {
    /**
     * 将Object转变成json字符串
     * @param obj
     * @return
     * @throws Exception
     */
        public static String objTojson(Object obj) throws Exception {
            return JSON.toJSONString(obj);
        }

    /**
     * 将Json字符串转换成某一个具体的类
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
        public static <T> T jsonToobj(String jsonStr, Class<T> clazz) throws Exception {
            return JSON.parseObject(jsonStr, clazz);
        }

    /**
     * 将Json字符串转换成Map对象
     * @param jsonStr
     * @param <T>
     * @return
     * @throws Exception
     */
        public static <T> Map<String, Object> jsonTomap(String jsonStr)     throws Exception {
            return JSON.parseObject(jsonStr, Map.class);
        }

    /**
     * 将Map对象转换成某一个类
     * @param map
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
        public static <T> T mapToobj(  Map<?, ?> map, Class<T> clazz) throws Exception {
            String s = JSON.toJSONString(map);
            return JSON.parseObject(s, clazz);
        }
    }

