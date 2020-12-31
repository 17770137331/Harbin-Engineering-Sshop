package org.wumbuk.entity;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用Json的时候通用的返回类
 *
 * @auther PeihaoYang
 * @date 2020/7/26 - 10:31
 */
public class Msg {

    //状态码 0-成功 1-失败
    private int code;

    //提示信息
    private String msg;

    //用户要返回给浏览器的数据
    private Map<String, Object> extend = new HashMap<String, Object>();

    /**
     * 处理成功的函数
     *
     * @return
     */
    public static Msg success() {
        Msg result = new Msg();
        result.setCode(1);
        result.setMsg("处理成功！");
        return result;
    }

    /**
     * 失败的返回函数
     *
     * @return
     */
    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(2);
        result.setMsg("处理失败！");
        return result;
    }

    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;


    }


    public int getCode() {
        return code;
    }

    public void setCode(int resultcode) {
        this.code = resultcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
