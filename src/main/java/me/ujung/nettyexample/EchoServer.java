package me.ujung.nettyexample;

import java.net.InetSocketAddress;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.ujung.nettyexample.handler.EchoServerHandler;

@Component
public class EchoServer {

	private final int port;
	
	public EchoServer(){
		this.port = 8080;
	}
	
	public EchoServer(int port){
		this.port = port;
	}
	
	public void start() throws Exception {
		
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new EchoServerHandler());
					}
					
				});
			ChannelFuture f = b.bind(this.port).sync();
			f.channel().closeFuture().sync();
		}finally{
			group.shutdownGracefully().sync();
		}
		
	}
	
}
