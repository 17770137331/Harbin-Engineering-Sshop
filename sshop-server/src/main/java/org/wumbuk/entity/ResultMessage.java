package org.wumbuk.entity;

/**
 * @auther PeihaoYang
 * @date 2020/9/20 - 18:47
 */
//服务端到客户端
public class ResultMessage {
    //如果为系统消息，则为true。如果非系统消息，则为false
    private  boolean isSystem;
    private String fromName;
    private Object message;

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
