package com.mx.framework.netty;

/**
 * @author : ShangGuanMingPeng
 * Description : Netty操作码
 * Date :Create in 2019/3/11 23:08
 * Modified By :
 */
public enum ActionCode {

    WS_HEARTBEAT_DETECTION(1001, "心跳检测");


    ActionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
