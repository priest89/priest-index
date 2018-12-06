package com.priest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priest.entity.ChannelEntity;

public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {

}
