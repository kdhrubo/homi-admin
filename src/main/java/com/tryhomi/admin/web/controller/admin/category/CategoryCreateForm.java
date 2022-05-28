

package com.tryhomi.admin.web.controller.admin.category;

import org.springframework.beans.BeanUtils;
import com.tryhomi.admin.domain.Category;
import com.tryhomi.admin.model.CategoryCreateRequest;
import com.tryhomi.admin.support.CodeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@SuppressWarnings("serial")
public class CategoryCreateForm implements Serializable {

	private Long parentId;

	@CodeFormat
	private String code;

	@NotNull
	private String name;

	private String description;

	@NotNull
	private String language;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public CategoryCreateRequest buildCategoryCreateRequest() {
		CategoryCreateRequest.Builder builder = new CategoryCreateRequest.Builder();
		return builder
				.parentId(parentId)
				.code(code)
				.name(name)
				.description(description)
				.language(language)
				.build();
	}

	public static CategoryCreateForm fromDomainObject(Category category) {
		CategoryCreateForm form = new CategoryCreateForm();
		BeanUtils.copyProperties(category, form);
		return form;
	}
}
