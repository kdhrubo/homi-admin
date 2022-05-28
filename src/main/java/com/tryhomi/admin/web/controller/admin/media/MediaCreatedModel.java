

package com.tryhomi.admin.web.controller.admin.media;

import com.tryhomi.admin.autoconfigure.WallRideProperties;
import com.tryhomi.admin.domain.Media;

import java.io.Serializable;

public class MediaCreatedModel implements Serializable {

	private String id;

	private String link;

	private String name;

	public MediaCreatedModel(Media media, String contextPath, WallRideProperties wallRideProperties) {
		this.id = media.getId();
		this.link = contextPath + wallRideProperties.getMediaUrlPrefix() + media.getId();
		this.name = media.getOriginalName();
	}

	public String getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}
}
