

package com.tryhomi.admin.web.support;

import com.tryhomi.admin.autoconfigure.WallRideProperties;
import org.thymeleaf.context.IExpressionContext;
import com.tryhomi.admin.domain.Media;

public class Medias {

	private IExpressionContext context;

	private WallRideProperties wallRideProperties;

	public Medias(IExpressionContext context, WallRideProperties wallRideProperties) {
		this.context = context;
		this.wallRideProperties = wallRideProperties;
	}

	public String link(Media media) {
		return link(media.getId());
	}

	public String link(String id) {
		return wallRideProperties.getMediaUrlPrefix() + id;
	}
}
