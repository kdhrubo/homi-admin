

package com.tryhomi.admin.web.support;

import com.tryhomi.admin.autoconfigure.WallRideProperties;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.context.IExpressionContext;
import com.tryhomi.admin.domain.Blog;
import com.tryhomi.admin.domain.User;

import java.util.HashMap;
import java.util.Map;

public class Users {

	private IExpressionContext context;

	private WallRideProperties wallRideProperties;

	public Users(IExpressionContext context, WallRideProperties wallRideProperties) {
		this.context = context;
		this.wallRideProperties = wallRideProperties;
	}

	public String link(User user) {
		UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
		return path(builder, user, true);
	}

	public String link(User user, boolean encode) {
		UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
		return path(builder, user, encode);
	}

	public String path(User user) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("");
		return path(builder, user, true);
	}

	public String path(User user, boolean encode) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("");
		return path(builder, user, encode);
	}

	private String path(UriComponentsBuilder builder, User user, boolean encode) {
		Map<String, Object> params = new HashMap<>();
		builder.path("/author/{code}");
		params.put("code", user.getLoginId());

		UriComponents components = builder.buildAndExpand(params);
		if (encode) {
			components = components.encode();
		}
		return components.toUriString();
	}

	public String title(User user) {
		Blog blog = (Blog) context.getVariable("BLOG");
		return String.format("%s | %s",
				user.getNickname(),
				blog.getTitle(context.getLocale().getLanguage()));
	}
}
