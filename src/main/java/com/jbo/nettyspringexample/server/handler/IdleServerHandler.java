package com.jbo.nettyspringexample.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 连接空闲Handler
 */
@Component
public class IdleServerHandler extends ChannelInboundHandlerAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			logger.info(String.format("[%s] 超时类型: %s", ctx.channel().remoteAddress(), event.state().name()));
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}
}
