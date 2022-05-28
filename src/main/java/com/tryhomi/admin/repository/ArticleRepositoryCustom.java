
package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.Article;
import com.tryhomi.admin.model.ArticleSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ArticleRepositoryCustom {

	Page<Article> search(ArticleSearchRequest request);
	Page<Article> search(ArticleSearchRequest request, Pageable pageable);
	List<Long> searchForId(ArticleSearchRequest request);
}
