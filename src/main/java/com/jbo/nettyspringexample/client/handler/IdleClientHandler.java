package com.jbo.nettyspringexample.client.handler;

import com.jbo.nettyspringexample.client.NettyClient;
import com.jbo.nettyspringexample.common.protobuf.Command;
import com.jbo.nettyspringexample.common.protobuf.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class IdleClientHandler extends SimpleChannelInboundHandler<Message> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final static String CLIENT_ID = "123456789";
	private int heartbeatCount = 0;

	private NettyClient nettyClient;

	public IdleClientHandler(NettyClient nettyClient) {
		this.nettyClient = nettyClient;
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			logger.info(String.format("[%s] 超时类型: %s", ctx.channel().remoteAddress(), event.state().name()));
			sendPingMsg(ctx);
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}

	// 发送ping消息
	private void sendPingMsg(ChannelHandlerContext context) {
		Message.MessageBase pingMsg = Message.MessageBase.newBuilder()
				.setClientId(CLIENT_ID)
				.setCmd(Command.CommandType.PING)
				.setData("This is a ping msg")
				.build();
		context.writeAndFlush(pingMsg);
		heartbeatCount++;
		logger.info("Client sent ping msg to " + context.channel().remoteAddress() + ", count: " + heartbeatCount);
	}

	/**
	 * 处理断开重连
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		final EventLoop eventLoop = ctx.channel().eventLoop();  
		eventLoop.schedule(() -> nettyClient.doConnect(new Bootstrap(), eventLoop), 10L, TimeUnit.SECONDS);  
		super.channelInactive(ctx);  
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {

	}
}
