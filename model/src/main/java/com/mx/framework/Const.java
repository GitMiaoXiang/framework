package com.mx.framework;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
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
public class Const {

   public String pageSize;

   @Value("${config.netty.port}")
   private Integer port;

   @Value("${config.netty.readTimeout}")
   private Integer readTimeout;

   @Value("${config.netty.writeTimeout}")
   private Integer writeTimeout;

   @Value("${config.netty.allTimeout}")
   private Integer allTimeout;

   @Value("${config.netty.webSocketPath}")
   private String webSocketPath;

}
