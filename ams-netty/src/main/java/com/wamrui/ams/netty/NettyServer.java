package com.wamrui.ams.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
 * @user WangRui
 * @date 2020/10/29
 * @tel 18583638630
 * 用netty来传输与前端的交互，没有用http，相对来说，
 * 这里的传输更快。但是可能与前端的交互有信息丢失
**/
@Component
public class NettyServer {

    private WSServerInitialzer wsServerInitialzer;
    @Autowired
    public NettyServer(WSServerInitialzer wsServerInitialzer){
        this.wsServerInitialzer =  wsServerInitialzer;
    }

    public void start() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.
                    group(boss, work).
                    channel(NioServerSocketChannel.class).
                    childHandler(wsServerInitialzer);
            //第4步绑定8889端口
            ChannelFuture future = boot.bind(8889).sync();
            //当通道关闭了，就继续往下走
            future.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
