package com.jbo.nettyspringexample.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Getter
@Setter
@Component
public class TCPServer {

	private final ServerBootstrap serverBootstrap;

	private final InetSocketAddress tcpPort;

	private Channel serverChannel;

	@Autowired
	public TCPServer(ServerBootstrap serverBootstrap, InetSocketAddress tcpPort) {
		this.serverBootstrap = serverBootstrap;
		this.tcpPort = tcpPort;
	}

	public void start() throws Exception {
		serverChannel =  serverBootstrap.bind(tcpPort).sync().channel().closeFuture().sync().channel();
	}

	@PreDestroy
	public void stop() throws Exception {
		serverChannel.close();
		serverChannel.parent().close();
	}

}
