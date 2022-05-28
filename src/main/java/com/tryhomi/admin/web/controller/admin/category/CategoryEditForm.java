

package com.tryhomi.admin.web.controller.admin.category;

import com.tryhomi.admin.support.CodeFormat;
import org.springframework.beans.BeanUtils;
import com.tryhomi.admin.domain.Category;
import com.tryhomi.admin.model.CategoryUpdateRequest;
import com.tryhomi.admin.support.CodeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@SuppressWarnings("serial")
public class CategoryEditForm implements Serializable {

	@NotNull
	private Long id;

	private Long parentId;

	@CodeFormat
	private String code;

	@NotNull
	private String name;

	private String description;

	@NotNull
	private String language;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public CategoryUpdateRequest buildCategoryUpdateRequest() {
		CategoryUpdateRequest.Builder builder = new CategoryUpdateRequest.Builder();
		return builder
				.id(id)
				.parentId(parentId)
				.code(code)
				.name(name)
				.description(description)
				.language(language)
				.build();
	}

	public static CategoryEditForm fromDomainObject(Category category) {
		CategoryEditForm form = new CategoryEditForm();
		BeanUtils.copyProperties(category, form);
		return form;
	}
}
