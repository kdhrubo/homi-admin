

package com.tryhomi.admin.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.tryhomi.admin.domain.Article;
import com.tryhomi.admin.model.ArticleSearchRequest;
import com.tryhomi.admin.service.ArticleService;

public class ArticleUtils {

	private ArticleService articleService;

	public ArticleUtils(ArticleService articleService) {
		this.articleService = articleService;
	}

	public Page<Article> search(ArticleSearchRequest request, int size) {
		return articleService.getArticles(request,  PageRequest.of(0, size));
	}
}
