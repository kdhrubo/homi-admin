

package com.tryhomi.admin.web.controller.admin.category;

import com.tryhomi.admin.model.CategorySearchRequest;

import java.io.Serializable;

public class CategorySearchForm implements Serializable {

	private String keyword;
	private String language;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public CategorySearchRequest toCategorySearchRequest() {
		return new CategorySearchRequest()
				.withKeyword(getKeyword())
				.withLanguage(getLanguage());
	}
}
