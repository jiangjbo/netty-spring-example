package com.jbo.nettyspringexample.server.configuration;

import com.jbo.nettyspringexample.common.protobuf.Message.MessageBase;
import com.jbo.nettyspringexample.server.handler.IdleServerHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Qualifier("serverChannelInitializer")
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

	private final static int READER_IDLE_TIME_SECONDS = 20;//读操作空闲20秒
	private final static int WRITER_IDLE_TIME_SECONDS = 20;//写操作空闲20秒
	private final static int ALL_IDLE_TIME_SECONDS = 40;//读写全部空闲40秒
	
    private final ChannelInboundHandlerAdapter authServerHandler;
    
    private final ChannelInboundHandlerAdapter logicServerHandler;

    @Autowired
    public ServerChannelInitializer(@Qualifier("authServerHandler") ChannelInboundHandlerAdapter authServerHandler,
                                    @Qualifier("logicServerHandler") ChannelInboundHandlerAdapter logicServerHandler) {
        this.authServerHandler = authServerHandler;
        this.logicServerHandler = logicServerHandler;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
    	ChannelPipeline p = socketChannel.pipeline();

    	/*
    	* IdleStateHandler这个类会根据你设置的超时参数的类型和值，循环去检测channelRead和write方法多久没有被调用了，
    	* 如果这个时间超过了你设置的值，那么就会触发对应的事件，read触发read，write触发write，all触发all
    	* */
    	p.addLast("idleStateHandler", new IdleStateHandler(READER_IDLE_TIME_SECONDS
    			, WRITER_IDLE_TIME_SECONDS, ALL_IDLE_TIME_SECONDS, TimeUnit.SECONDS));
	    p.addLast("idleTimeoutHandler", new IdleServerHandler());
	    
        p.addLast(new ProtobufVarint32FrameDecoder());
        p.addLast(new ProtobufDecoder(MessageBase.getDefaultInstance()));

        p.addLast(new ProtobufVarint32LengthFieldPrepender());
        p.addLast(new ProtobufEncoder());
	    
        p.addLast("authServerHandler", authServerHandler);
        p.addLast("hearableServerHandler", logicServerHandler);
    }
}
