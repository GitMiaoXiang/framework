package com.mx.framework.netty;

import com.mx.framework.Const;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : ShangGuanMingPeng
 * Description : Netty启动入口
 * Date :Create in 2019/3/7 17:23
 * Modified By :
 */
@Component
public class NettyServer {

    @Autowired
    private Const aConst;

    public void start() throws Exception {
        //主线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //从线程组
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //Netty服务器的创建，ServerBootStrap是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup) //是指主从线程组
                    .channel(NioServerSocketChannel.class) //设置Nio的双向通道
                    .childHandler(new NettyServerInitializer());  //子处理器，处理work
            //启动Server,并且设置启动端口，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(aConst.getPort()).sync();
            //监听关闭的Channel,设置为同步方式
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
