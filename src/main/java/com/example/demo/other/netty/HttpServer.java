package com.example.demo.other.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Created by void on 2018/11/19.
 */
public class HttpServer {

    private final int port;

    public HttpServer(int port){
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        if(args.length != 1){
            System.err.println("Usage:"+HttpServer.class.getSimpleName()+"<port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        new HttpServer(port).start();
    }

    public void start() throws InterruptedException {
        ServerBootstrap b = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        b.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("initChannel ch:"+ch);
                        ch.pipeline().addLast("decoder", new HttpRequestDecoder())//1.用于解码request
                        .addLast("encoder", new HttpResponseEncoder())//2.用于编码response
                        .addLast("aggregator", new HttpObjectAggregator(515*1024))//3.消息聚合器,消息不超过512kb
                                .addLast("handler", new HttpHandler());        // 4.业务处理接口
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
        b.bind(port).sync();
    }
}
