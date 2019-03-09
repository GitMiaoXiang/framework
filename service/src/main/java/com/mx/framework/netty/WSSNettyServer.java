package com.mx.framework.netty;

import com.mx.framework.Const;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2019/3/7 23:07
 * Modified By :
 */
@Slf4j
@Component
public class WSSNettyServer {

    @Autowired
    private Const aConst;

    @Autowired
    private WSSServerInitialize wssServerInitialize;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture future;

    public void startServer() throws InterruptedException {
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        try {
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(wssServerInitialize);
            future = serverBootstrap.bind(aConst.getPort()).sync();
            if(future.isSuccess()){
                log.info("Netty服务启动成功，端口：{}",aConst.getPort());
            }
            future.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
