package com.priest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.priest.index.service", "com.priest.index.controller" })
@EntityScan(basePackages = { "com.priest.index.entity" })
@EnableJpaRepositories(basePackages = { "com.priest.index.repository" })
@EnableDiscoveryClient
public class IndexApplication {
	public static void main(String[] args) {
		SpringApplication.run(IndexApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
