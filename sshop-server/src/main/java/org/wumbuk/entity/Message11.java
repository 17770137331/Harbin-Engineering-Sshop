package org.wumbuk.entity;

/**
 * @auther PeihaoYang
 * @date 2020/9/20 - 18:37
 */
//该实体类用来表示发送给某个用户的信息
    //客户端到服务端
public class Message11 {

    private String toName;
    private String message;

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

