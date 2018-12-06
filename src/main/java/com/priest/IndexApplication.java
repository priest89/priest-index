package com.priest;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.priest.config.IndexProperties;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.priest.index.service", "com.priest.index.controller", "com.priest.index.config" })
@EntityScan(basePackages = { "com.priest.index.entity" })
@EnableJpaRepositories(basePackages = { "com.priest.index.repository" })
@EnableDiscoveryClient
@EnableScheduling
public class IndexApplication {

	@Autowired
	private IndexProperties indexProperties;

	public static void main(String[] args) {
		SpringApplication.run(IndexApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	public TopicExchange getChannelExchange() {
		return new TopicExchange(indexProperties.getChannelExchange());
	}

	@Bean
	public Queue getChannelQueue() {
		return new Queue(indexProperties.getChannelQueue());
	}

	@Bean
	public TopicExchange getVideoExchange() {
		return new TopicExchange(indexProperties.getVideoExchange());
	}

	@Bean
	public Queue getVideoQueue() {
		return new Queue(indexProperties.getVideoQueue());
	}

	@Bean
	public Binding bindVideo() {
		return BindingBuilder.bind(getVideoQueue()).to(getVideoExchange()).with(indexProperties.getVideoRoutingKey());
	}

	@Bean
	public Binding bindChannel() {
		return BindingBuilder.bind(getChannelQueue()).to(getChannelExchange())
				.with(indexProperties.getChannelRoutingKey());
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}
}
