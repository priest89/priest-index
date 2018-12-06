package com.priest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "channel")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Id
	private Long id;

	@Column(name = "channel_guid")
	private String channelGuid;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "status")
	private String status;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "lastModified")
	private String lastModified;

}
