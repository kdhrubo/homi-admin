

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.CustomField;
import com.tryhomi.admin.domain.Page;
import com.tryhomi.admin.model.PageSearchRequest;
import org.apache.lucene.analysis.Analyzer;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;

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

public class PageRepositoryImpl implements PageRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public org.springframework.data.domain.Page<Page> search(PageSearchRequest request) {
		return search(request, Pageable.unpaged());
	}

	@Override
	public org.springframework.data.domain.Page<Page> search(PageSearchRequest request, Pageable pageable) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Page.class)
				.setFetchMode("cover", FetchMode.JOIN)
				.setFetchMode("author", FetchMode.JOIN)
				.setFetchMode("categories", FetchMode.JOIN)
				.setFetchMode("tags", FetchMode.JOIN)
				.setFetchMode("customFieldValues", FetchMode.JOIN)
				.setFetchMode("customFieldValues.customField", FetchMode.JOIN)
				.setFetchMode("parent", FetchMode.JOIN)
				.setFetchMode("children", FetchMode.JOIN);

		return null;
	}

	@Override
	public List<Long> searchForId(PageSearchRequest request) {
		return null;
	}


}
