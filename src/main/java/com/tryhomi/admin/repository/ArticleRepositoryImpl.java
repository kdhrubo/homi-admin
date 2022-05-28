

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.Article;
import com.tryhomi.admin.domain.CustomField;
import com.tryhomi.admin.model.ArticleSearchRequest;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArticleRepositoryImpl implements ArticleRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Article> search(ArticleSearchRequest request) {
		return search(request, Pageable.unpaged());
	}

	@Override
	public Page<Article> search(ArticleSearchRequest request, Pageable pageable) {
		/*
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Article.class)
				.setFetchMode("cover", FetchMode.JOIN)
				.setFetchMode("user", FetchMode.JOIN)
				.setFetchMode("categories", FetchMode.JOIN)
				.setFetchMode("tags", FetchMode.JOIN)
				.setFetchMode("customFieldValues", FetchMode.JOIN)
				.setFetchMode("customFieldValues.customField", FetchMode.JOIN);

		FullTextQuery persistenceQuery = buildFullTextQuery(request, pageable, criteria);
		int resultSize = persistenceQuery.getResultSize();
		List<Article> results = persistenceQuery.getResultList();
		return new PageImpl<>(results, pageable, resultSize);

		 */
		return null;
	}

	@Override
	public List<Long> searchForId(ArticleSearchRequest request) {
		/*
		FullTextQuery persistenceQuery = buildFullTextQuery(request, Pageable.unpaged(), null);
		persistenceQuery.setProjection("id");
		List<Object[]> results = persistenceQuery.getResultList();
		List<Long> nos = results.stream().map(result -> (long) result[0]).collect(Collectors.toList());
		return nos;

		 */

		return null;
	}


}
