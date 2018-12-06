package com.priest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.priest.dto.ChannelDto;
import com.priest.entity.ChannelEntity;

@Component
public class ChannelMapper {
	public ChannelDto toChannelDto(ChannelEntity channelEntity) {
		ChannelDto channelDto = new ChannelDto();
		return channelDto;
	}

	public ChannelEntity toChannelEntity(ChannelDto channelDto) {
		ChannelEntity channelEntity = new ChannelEntity();
		return channelEntity;
	}

	public List<ChannelDto> toChannelDtos(List<ChannelEntity> channelEntities) {
		List<ChannelDto> channelDtos = new ArrayList<ChannelDto>();
		for (ChannelEntity channelEntity : channelEntities) {
			channelDtos.add(toChannelDto(channelEntity));
		}
		return channelDtos;
	}

	public List<ChannelEntity> toChannelEntities(List<ChannelDto> channelDtos) {
		List<ChannelEntity> channelEntities = new ArrayList<ChannelEntity>();
		for (ChannelDto channelDto : channelDtos) {
			channelEntities.add(toChannelEntity(channelDto));
		}
		return channelEntities;
	}
}
