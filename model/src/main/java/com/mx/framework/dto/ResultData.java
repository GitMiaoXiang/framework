package com.mx.framework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mx.framework.cosntenum.ResponseEnum;

/**
 * @author ShangGuanMingPeng
 * date: 2018/8/24 14:55
 * Description: 统一响应体,@Data该注解相当于同时加上以下注解@Setter @Getter,@ToString,@EqualsAndHashCode
 * Include.Include.ALWAYS （Default / 都参与序列化）
 * Include.NON_DEFAULT（当Value 为默认值的时候不参与，如Int a; 当 a=0 的时候不参与）
 * Include.NON_EMPTY（当Value 为“” 或者null 不输出）
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResultData<T> {

    private int code;  //请求业务码

    private int status; //请求是否达到客户端想要的效果

    private String message; //提示语

    private T data; //数据

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //通过枚举传入信息
    public void setResponseMessage(ResponseEnum responseMessage) {
        this.code = responseMessage.getCode();
        this.status = responseMessage.getStatus();
        this.message = responseMessage.getMessage();
    }


}
