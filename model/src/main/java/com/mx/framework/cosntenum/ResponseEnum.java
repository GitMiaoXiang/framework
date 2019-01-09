package com.mx.framework.cosntenum;

/**
 * @author ShangGuanMingPeng
 * date: 2018/8/24 14:53
 * Description:
 */
public enum ResponseEnum {

    SYSTEM_EXCEPTION(0, 0, "系统异常"),
    SUCCESS(1, 1, "成功"),
    FAILED(1, 0, "失败"),
    SAVE_FAILED(1,0,"保存失败"),
    BUSINESS_EXCEPTION(0, 3, "业务异常");

    private int code;

    private int status;

    private String message;

    ResponseEnum(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
