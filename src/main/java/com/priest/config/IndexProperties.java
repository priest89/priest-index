package com.priest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ConfigurationProperties(prefix = "com.priest")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IndexProperties {
	private String channelQueue;

	private String channelExchange;

	private String channelRoutingKey;

	private String videoQueue;

	private String videoExchange;

	private String videoRoutingKey;

}
