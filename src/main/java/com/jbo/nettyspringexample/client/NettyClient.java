package com.jbo.nettyspringexample.client;

import com.jbo.nettyspringexample.client.handler.IdleClientHandler;
import com.jbo.nettyspringexample.client.handler.LogicClientHandler;
import com.jbo.nettyspringexample.common.protobuf.Message.MessageBase;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * netty客户端
 * @author lenovo
 *
 */
public class NettyClient {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final static String HOST = "127.0.0.1";
	private final static int PORT = 8090;
	private final static int READER_IDLE_TIME_SECONDS = 20;//读操作空闲20秒
	private final static int WRITER_IDLE_TIME_SECONDS = 20;//写操作空闲20秒
	private final static int ALL_IDLE_TIME_SECONDS = 40;//读写全部空闲40秒

	private EventLoopGroup loop = new NioEventLoopGroup();

	public static void main(String[] args) throws Exception {
		NettyClient client = new NettyClient();  
		client.run();  
	}

	public void run() throws Exception {
		try {  
			doConnect(new Bootstrap(), loop);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * netty client 连接，连接失败10秒后重试连接
	 */
	public Bootstrap doConnect(Bootstrap bootstrap, EventLoopGroup eventLoopGroup) {
		try {
			bootstrap.group(eventLoopGroup);
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();

					p.addLast("idleStateHandler", new IdleStateHandler(READER_IDLE_TIME_SECONDS
							, WRITER_IDLE_TIME_SECONDS, ALL_IDLE_TIME_SECONDS, TimeUnit.SECONDS));
					p.addLast("idleTimeoutHandler", new IdleClientHandler(NettyClient.this));

					p.addLast(new ProtobufVarint32FrameDecoder());
					p.addLast(new ProtobufDecoder(MessageBase.getDefaultInstance()));

					p.addLast(new ProtobufVarint32LengthFieldPrepender());
					p.addLast(new ProtobufEncoder());

					p.addLast("clientHandler", new LogicClientHandler());
				}
			});
			bootstrap.remoteAddress(HOST, PORT);
			ChannelFuture f = bootstrap.connect().addListener((ChannelFuture futureListener)->{
				final EventLoop eventLoop = futureListener.channel().eventLoop();
				if (!futureListener.isSuccess()) {
					logger.warn("Failed to connect to server, try connect after 10s");
					futureListener.channel().eventLoop().schedule(() -> doConnect(new Bootstrap(), eventLoop), 10, TimeUnit.SECONDS);
				}
			});
			f.channel().closeFuture().sync();
			eventLoopGroup.shutdownGracefully();
		} catch (InterruptedException e) {
			logger.error("" );
		}
		return bootstrap;
	}
}