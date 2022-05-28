

package com.tryhomi.admin.web.controller.admin.tag;

import org.springframework.beans.BeanUtils;
import com.tryhomi.admin.domain.Category;
import com.tryhomi.admin.model.TagCreateRequest;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@SuppressWarnings("serial")
public class TagCreateForm implements Serializable {

	@NotNull
	private String name;
	@NotNull
	private String language;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public TagCreateRequest buildTagCreateRequest() {
		TagCreateRequest.Builder builder = new TagCreateRequest.Builder();
		return builder
				.name(name)
				.language(language)
				.build();
	}

	public static TagCreateForm fromDomainObject(Category category) {
		TagCreateForm form = new TagCreateForm();
		BeanUtils.copyProperties(category, form);
		return form;
	}
}
