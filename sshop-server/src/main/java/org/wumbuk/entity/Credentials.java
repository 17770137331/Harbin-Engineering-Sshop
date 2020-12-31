package org.wumbuk.entity;

/**
 * @auther PeihaoYang
 * @date 2020/12/19 - 21:11
 */

/**
 * STS后端返回的临时密钥
 */
public class Credentials {
    private String tmpSecretId;
    private String tmpSecretKey;
    private String sessionToken;
    private String startTime;
    private  String expiredTime;
    private String expiration;
    private String requestId;

    @Override
    public String toString() {
        return "Credentials{" +
                "tmpSecretId='" + tmpSecretId + '\'' +
                ", tmpSecretKey='" + tmpSecretKey + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                ", startTime='" + startTime + '\'' +
                ", expiredTime='" + expiredTime + '\'' +
                ", expiration='" + expiration + '\'' +
                ", requestId='" + requestId + '\'' +
                '}';
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTmpSecretId() {
        return tmpSecretId;
    }

    public void setTmpSecretId(String tmpSecretId) {
        this.tmpSecretId = tmpSecretId;
    }

    public String getTmpSecretKey() {
        return tmpSecretKey;
    }

    public void setTmpSecretKey(String tmpSecretKey) {
        this.tmpSecretKey = tmpSecretKey;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
