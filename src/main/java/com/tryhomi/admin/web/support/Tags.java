

package com.tryhomi.admin.web.support;

import org.thymeleaf.context.IExpressionContext;
import com.tryhomi.admin.domain.Tag;
import com.tryhomi.admin.support.TagUtils;

import java.util.List;

public class Tags {

	private IExpressionContext context;

	private TagUtils tagUtils;

	public Tags(IExpressionContext context, TagUtils TagUtils) {
		this.context = context;
		this.tagUtils = TagUtils;
	}

	public List<Tag> getAllTags() {
		return tagUtils.getAllTags();
	}
}
