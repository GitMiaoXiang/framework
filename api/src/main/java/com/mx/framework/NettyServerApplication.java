package com.mx.framework;

import com.mx.framework.netty.WSSNettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author : ShangGuanMingPeng
 * Description : SpringBoot启动完成启动Netty服务
 * Date :Create in 2019/3/8 9:21
 * Modified By :
 */
@Slf4j
@Component
public class NettyServerApplication implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private WSSNettyServer nettyServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent()==null){
            try {
                log.info("Netty Server开始启动");
                nettyServer.startServer();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
