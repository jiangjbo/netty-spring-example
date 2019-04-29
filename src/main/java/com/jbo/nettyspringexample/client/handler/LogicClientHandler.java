package com.jbo.nettyspringexample.client.handler;


import com.jbo.nettyspringexample.common.protobuf.Command.CommandType;
import com.jbo.nettyspringexample.common.protobuf.Message.MessageBase;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogicClientHandler extends SimpleChannelInboundHandler<MessageBase>{
	public Logger log = LoggerFactory.getLogger(this.getClass());	

	private final static String CLIENTID = "123456789";

	// 连接成功后，向server发送消息  
	@Override  
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		MessageBase authMsg = MessageBase.newBuilder()
				.setClientId(CLIENTID)
				.setCmd(CommandType.AUTH)
				.setData("This is auth data")
				.build();
		ctx.writeAndFlush(authMsg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.debug("连接断开 ");
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageBase msg) throws Exception {
		if(msg.getCmd().equals(CommandType.AUTH_BACK)){
			log.debug("验证成功");
			ctx.writeAndFlush(
					MessageBase.newBuilder()
					.setClientId(CLIENTID)
					.setCmd(CommandType.PUSH_DATA)
					.setData("This is upload data")
					.build()
					);

		}else if(msg.getCmd().equals(CommandType.PING)){
			//接收到server发送的ping指令
			log.info(msg.getData());
			
		}else if(msg.getCmd().equals(CommandType.PONG)){
			//接收到server发送的pong指令
			log.info(msg.getData());
			
		}else if(msg.getCmd().equals(CommandType.PUSH_DATA)){
			//接收到server推送数据
			log.info(msg.getData());
			
		}else if(msg.getCmd().equals(CommandType.PUSH_DATA_BACK)){
			//接收到server返回数据
			log.info(msg.getData());
			
		}else{
			log.info(msg.getData());
		}
	}
}
