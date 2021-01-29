package com.wamrui.ams.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DQOServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final DQOServerAdapter dqoServerAdapter;

    public DQOServerHandler(DQOServerAdapter dqoServerAdapter) {
        this.dqoServerAdapter = dqoServerAdapter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext cxt, TextWebSocketFrame text) {
        System.out.println(text.text());
        cxt.writeAndFlush(new TextWebSocketFrame(text.text()+123123));
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.err.println("[handlerAdded]:" + ctx.channel().id());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.err.println("[handlerRemoved]:" + ctx.channel().id());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //ctx.channel().close();
        cause.printStackTrace();
    }

}