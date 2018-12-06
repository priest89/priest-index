package com.priest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.priest.dto.ChannelDto;
import com.priest.entity.ChannelEntity;
import com.priest.mapper.ChannelMapper;
import com.priest.repository.ChannelRepository;

@Service
public class ChannelService {
	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private ChannelMapper channelMapper;

	public List<ChannelDto> getListChannel(int offset, int pageSize) {
		PageRequest pageRequest = PageRequest.of(offset, pageSize);
		Page<ChannelEntity> channelEntities = channelRepository.findAll(pageRequest);
		return channelMapper.toChannelDtos(channelEntities.getContent());
	}
}
