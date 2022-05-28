

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.CustomField;
import com.tryhomi.admin.model.CustomFieldSearchRequest;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;


import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class CustomFieldRepositoryImpl implements CustomFieldRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void lock(long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<CustomField> root = query.from(CustomField.class);
		//query.select(root.get(CustomField_.id));
		//query.where(cb.equal(root.get(CustomField_.id), id));
		entityManager.createQuery(query).setLockMode(LockModeType.PESSIMISTIC_WRITE).getSingleResult();
	}

	@Override
	public Page<CustomField> search(CustomFieldSearchRequest request) {
		return search(request, Pageable.unpaged());
	}

	@Override
	public Page<CustomField> search(CustomFieldSearchRequest request, Pageable pageable) {
		/*
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(CustomField.class)
				.setFetchMode("options", FetchMode.JOIN);

		FullTextQuery persistenceQuery = buildFullTextQuery(request, pageable, criteria);
		int resultSize = persistenceQuery.getResultSize();
		List<CustomField> results = persistenceQuery.getResultList();
		return new PageImpl<>(results, pageable, resultSize);

		 */
		return null;
	}

	@Override
	public List<Long> searchForId(CustomFieldSearchRequest request) {
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