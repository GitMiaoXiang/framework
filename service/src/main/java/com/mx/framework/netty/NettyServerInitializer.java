package com.mx.framework.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @author : ShangGuanMingPeng
 * Description : 初始化器，channel注册后，会执行里面的初始化方法
 * Date :Create in 2019/3/7 17:37
 * Modified By :
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 通过SocketChannel去获得对应的管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        //通过管道，添加handler
        //HttpServerCodec是由Netty自己提供的助手类，可以理解为拦截器
        //当请求到服务端，我们需要解码，响应到客户端做编码
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast(null);
    }
}
