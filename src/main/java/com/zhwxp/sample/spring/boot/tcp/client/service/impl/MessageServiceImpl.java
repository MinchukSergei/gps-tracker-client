package com.zhwxp.sample.spring.boot.tcp.client.service.impl;

import com.zhwxp.sample.spring.boot.tcp.client.gateway.TcpClientGateway;
import com.zhwxp.sample.spring.boot.tcp.client.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

	private final TcpClientGateway tcpClientGateway;

	public MessageServiceImpl(TcpClientGateway tcpClientGateway) {
		this.tcpClientGateway = tcpClientGateway;
	}

	@Override
	public void sendMessage() {
		String message = LocalDateTime.now().toString();
//		tcpClientGateway.send(message.getBytes());
//		LOGGER.info("Send message: {}", message);
		byte[] responseBytes = tcpClientGateway.send(message.getBytes());
//		String response = new String(responseBytes);
//		LOGGER.info("Receive response: {}", response);
	}
}
