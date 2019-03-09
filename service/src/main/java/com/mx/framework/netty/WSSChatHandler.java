package com.mx.framework.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2019/3/8 9:09
 * Modified By :
 */
@Slf4j
public class WSSChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        log.info("WebSocket 传过来的数据：{}",text);
        //群发消息
        for (Channel channel:clients){
            channel.writeAndFlush(new TextWebSocketFrame(
               "[服务器在]"+ LocalDateTime.now()+"接受的到消息"+text));
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端{}连接",ctx.channel().id().asShortText());
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        log.info("客户端断开，channle对应的长Id为：{}"+ctx.channel().id().asLongText());
        log.info("客户端{}断开",ctx.channel().id().asShortText());
        clients.remove(ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if(IdleState.READER_IDLE==event.state()){
                log.info("客户端{}进入读空闲",ctx.channel().id().asShortText());
            }else if(IdleState.WRITER_IDLE==event.state()){
                log.info("客户端{}进入写空闲",ctx.channel().id().asShortText());
            }else if(IdleState.ALL_IDLE==event.state()){
                log.info("客户端{}进入读写空闲",ctx.channel().id().asShortText());
            }
        }
    }
}
