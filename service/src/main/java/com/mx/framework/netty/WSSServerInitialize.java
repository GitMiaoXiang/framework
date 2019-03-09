package com.mx.framework.netty;

import com.mx.framework.Const;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : ShangGuanMingPeng
 * Description : WebSocket初始化类
 * Date :Create in 2019/3/7 23:10
 * Modified By :
 */
@Component
public class WSSServerInitialize extends ChannelInitializer<SocketChannel> {

    @Autowired
    private Const aConst;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //WebSocket基于http协议，所以要有Http编码器
        pipeline.addLast(new HttpServerCodec());
        //对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpMessage进行整合，聚合成FullHttpRequest或FullHttpResponse
        //几乎在netty中的编程，都会使用到此handler
        pipeline.addLast(new HttpObjectAggregator(1024*64));

        // =================以上是用于http协议 =================//

        //websocket 服务器处理的协议，用于指定给客户端连接访问的路由：/ws
        //本handler会帮你处理一些复杂的事
        //会帮你处理握手动作，handshaking(close,ping,pong) ping+pong = 心跳
        //对于websocket,都是以frames进行传输的，不同的数据类型对应的frames也不同
        pipeline.addLast(new WebSocketServerProtocolHandler(aConst.getWebSocketPath()));

        //加入心跳Hand
        pipeline.addLast(new IdleStateHandler(
                aConst.getReadTimeout(),
                aConst.getWriteTimeout(),
                aConst.getAllTimeout()
        ));
        //自定义handler
        pipeline.addLast(new WSSChatHandler());

    }
}
