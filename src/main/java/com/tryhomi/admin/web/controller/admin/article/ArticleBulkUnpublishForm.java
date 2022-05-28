

package com.tryhomi.admin.web.controller.admin.article;

import com.tryhomi.admin.model.ArticleBulkUnpublishRequest;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class ArticleBulkUnpublishForm implements Serializable {

	private List<Long> ids;
	@NotNull
	private String language;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ArticleBulkUnpublishRequest toArticleBulkUnpublishRequest() {
		ArticleBulkUnpublishRequest request = new ArticleBulkUnpublishRequest();
		request.setIds(getIds());
		request.setLanguage(getLanguage());
		return request;
	}
}
