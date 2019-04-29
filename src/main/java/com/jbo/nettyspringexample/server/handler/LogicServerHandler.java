package com.jbo.nettyspringexample.server.handler;

import com.jbo.nettyspringexample.common.protobuf.Command.CommandType;
import com.jbo.nettyspringexample.common.protobuf.Message;
import com.jbo.nettyspringexample.common.protobuf.Message.MessageBase;
import com.jbo.nettyspringexample.server.ChannelRepository;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 业务逻辑handler
 *
 * @author Ke Shanqiang
 */
@Component
@Qualifier("logicServerHandler")
@ChannelHandler.Sharable
public class LogicServerHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AttributeKey<String> clientInfo = AttributeKey.valueOf("clientInfo");

    @Autowired
    ChannelRepository channelRepository;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageBase msgBase = (MessageBase) msg;
        logger.info(msgBase.getData());

        MessageBase messageBase = MessageBase.newBuilder()
                .setClientId(msgBase.getClientId())
                .setCmd(CommandType.UPLOAD_DATA_BACK)
                .setData("This is upload data back msg")
                .build();
        ChannelFuture cf = ctx.writeAndFlush(messageBase);
        /* 上一条消息发送成功后，立马推送一条消息 */
        cf.addListener(future -> {
            if (future.isSuccess()) {
                MessageBase message = MessageBase.newBuilder()
                        .setClientId(msgBase.getClientId())
                        .setCmd(CommandType.PUSH_DATA)
                        .setData("This is a push msg")
                        .build();
                ctx.writeAndFlush(message);
            }
        });
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Attribute<String> attr = ctx.attr(clientInfo);
        String clientId = attr.get();
        logger.error(String.format("Connection closed, client is %s", clientId), cause.getCause());
    }

}
