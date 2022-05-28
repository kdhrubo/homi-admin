

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.Category;
import com.tryhomi.admin.model.CategorySearchRequest;
import org.apache.lucene.analysis.Analyzer;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
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

public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void lock(long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Category> root = query.from(Category.class);
		//query.select(root.get(Category_.id));
		//query.where(cb.equal(root.get(Category_.id), id));
		entityManager.createQuery(query).setLockMode(LockModeType.PESSIMISTIC_WRITE).getSingleResult();
	}

	@Override
	public Page<Category> search(CategorySearchRequest request) {
		return search(request, Pageable.unpaged());
	}

	@Override
	public Page<Category> search(CategorySearchRequest request, Pageable pageable) {
		return null;
	}
}