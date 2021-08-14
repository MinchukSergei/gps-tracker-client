package com.zhwxp.sample.spring.boot.tcp.client.scheduler;

import com.zhwxp.sample.spring.boot.tcp.client.service.MessageService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

@Component
public class MessageJobScheduler implements SchedulingConfigurer {

	private final MessageService messageService;

	public MessageJobScheduler(MessageService messageService) {
		this.messageService = messageService;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(100);
		scheduler.initialize();

		scheduledTaskRegistrar.setTaskScheduler(scheduler);
		IntStream.range(0, 20).forEach(value -> scheduledTaskRegistrar.addFixedRateTask(messageService::sendMessage, 1L));
	}
}
