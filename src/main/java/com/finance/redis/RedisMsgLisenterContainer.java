package com.finance.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
public class RedisMsgLisenterContainer {
    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private RedisConnectionFactory connectionFactory = null;

    @Autowired
    private MessageListener messageListener = null;
    // 任务池
    private ThreadPoolTaskScheduler taskScheduler = null;

    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler(){
        if (taskScheduler != null){
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    @Bean
    public RedisMessageListenerContainer initRedisMessageListenerContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        // Redis 连接工厂
        container.setConnectionFactory(connectionFactory);
        // 设置运行任务池
        container.setTaskExecutor(initTaskScheduler());
        // 定义监听渠道
        Topic topic = new ChannelTopic("topic1");
        container.addMessageListener(messageListener,topic);
        return container;
    }
}
