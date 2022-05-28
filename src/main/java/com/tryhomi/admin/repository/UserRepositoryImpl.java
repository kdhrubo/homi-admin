
package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.User;
import com.tryhomi.admin.model.UserSearchRequest;
import org.apache.lucene.analysis.Analyzer;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.Session;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<User> search(UserSearchRequest request) {
		return search(request, Pageable.unpaged());
	}

	@Override
	public Page<User> search(UserSearchRequest request, Pageable pageable) {
		return null;
	}

	@Override
	public List<Long> searchForId(UserSearchRequest request) {
		return null;
	}


}
