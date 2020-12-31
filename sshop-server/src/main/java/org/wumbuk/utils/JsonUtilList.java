package org.wumbuk.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;
/**
 * @auther PeihaoYang
 * @date 2020/12/21 - 9:56
 */

public class JsonUtilList<T> {

    public static JSONObject mapToJson(Map<String, Object> map) {
        String data = JSON.toJSONString(map);
        return JSON.parseObject(data);
    }
    /**
     * map中取key对应的value
     * @param map
     * @param key
     * @return
     */
    public String mapToString(Map<String, Object> map, String key) {
        JSONObject jsonObject = mapToJson(map);
        return jsonObject.getString(key);
    }
    /**
     * map中取类对象
     * @param map
     * @param clazz
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz, String key) {
        T t = null;
        JSONObject jsonObject = mapToJson(map);
        JSONObject object = jsonObject.getJSONObject(key);
        t = object.toJavaObject(clazz);
        return t;
    }

    /** 这个方法很好用！！！
     * map中取list
     * @param map
     * @param clazz
     * @param key
     * @return
     */
    public  List<T> mapToList(Map<String, Object> map, Class<T> clazz, String key) {
        List<T> t = null;
        JSONObject jsonObject = mapToJson(map);
        JSONArray array = jsonObject.getJSONArray(key);
        t = array.toJavaList(clazz);
        return t;
    }

}
