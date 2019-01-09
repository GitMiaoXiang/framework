package com.mx.framework.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : ShangGuanMingPeng
 * Description : 全局变量
 * Date :Create in 2018/12/6 18:29
 * Modified By :
 */
@Component
@Data
@ConfigurationProperties(prefix = "config")
public class Const {

   public String pageSize;

}
