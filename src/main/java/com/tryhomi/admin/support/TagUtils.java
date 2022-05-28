

package com.tryhomi.admin.support;

import org.springframework.context.i18n.LocaleContextHolder;
import com.tryhomi.admin.domain.Tag;
import com.tryhomi.admin.service.TagService;

import java.util.List;

public class TagUtils {

	private TagService tagService;

	public TagUtils(TagService tagService) {
		this.tagService = tagService;
	}

	public List<Tag> getAllTags() {
		return tagService.getTags(LocaleContextHolder.getLocale().getLanguage());
	}
}
