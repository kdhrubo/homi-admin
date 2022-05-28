

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.Article;
import com.tryhomi.admin.domain.CustomField;
import com.tryhomi.admin.model.ArticleSearchRequest;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;

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
	}

	@Override
	public List<Long> searchForId(ArticleSearchRequest request) {
		FullTextQuery persistenceQuery = buildFullTextQuery(request, Pageable.unpaged(), null);
		persistenceQuery.setProjection("id");
		List<Object[]> results = persistenceQuery.getResultList();
		List<Long> nos = results.stream().map(result -> (long) result[0]).collect(Collectors.toList());
		return nos;
	}

	private FullTextQuery buildFullTextQuery(ArticleSearchRequest request, Pageable pageable, Criteria criteria) {
		FullTextEntityManager fullTextEntityManager =  Search.getFullTextEntityManager(entityManager);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Article.class)
				.get();

		@SuppressWarnings("rawtypes")
		BooleanJunction<BooleanJunction> junction = qb.bool();
		junction.must(qb.all().createQuery());

		junction.must(qb.keyword().onField("drafted").ignoreAnalyzer().matching("_null_").createQuery());

		if (StringUtils.hasText(request.getKeyword())) {
//			Analyzer analyzer = fullTextEntityManager.getSearchFactory().getAnalyzer("synonyms");
			Analyzer analyzer = new WhitespaceAnalyzer();
			String[] fields = new String[] {
					"title", "body",
					"categories.name", "tags.name",
					"customFieldValues.value"
			};
			MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
			parser.setDefaultOperator(QueryParser.Operator.AND);
			Query query = null;
			try {
				query = parser.parse(request.getKeyword());
			}
			catch (ParseException e1) {
				try {
					query = parser.parse(QueryParser.escape(request.getKeyword()));
				}
				catch (ParseException e2) {
					throw new RuntimeException(e2);
				}
			}
			junction.must(query);
		}
		if (request.getStatus() != null) {
			//TODO - Review
			//junction.must(qb.keyword().onField("status").matching(request.getStatus()).createQuery());
		}
		if (StringUtils.hasText(request.getLanguage())) {
			junction.must(qb.keyword().onField("language").matching(request.getLanguage()).createQuery());
		}

		if (request.getDateFrom() != null) {
			junction.must(qb.range().onField("date").above(request.getDateFrom()).createQuery());
		}
		if (request.getDateTo() != null) {
			junction.must(qb.range().onField("date").below(request.getDateTo()).createQuery());
		}

		if (!CollectionUtils.isEmpty(request.getCategoryIds())) {
			BooleanJunction<BooleanJunction> subJunction = qb.bool();
			for (long categoryId : request.getCategoryIds()) {
				subJunction.should(qb.keyword().onField("categories.id").matching(categoryId).createQuery());
			}
			junction.must(subJunction.createQuery());
		}
		if (!CollectionUtils.isEmpty(request.getCategoryCodes())) {
			BooleanJunction<BooleanJunction> subJunction = qb.bool();
			for (String categoryCode : request.getCategoryCodes()) {
				subJunction.should(qb.keyword().onField("categories.code").matching(categoryCode).createQuery());
			}
			junction.must(subJunction.createQuery());
		}

		if (!CollectionUtils.isEmpty(request.getTagIds())) {
			BooleanJunction<BooleanJunction> subJunction = qb.bool();
			for (long tagId : request.getTagIds()) {
				subJunction.should(qb.keyword().onField("tags.id").matching(tagId).createQuery());
			}
			junction.must(subJunction.createQuery());
		}
		if (!CollectionUtils.isEmpty(request.getTagNames())) {
			BooleanJunction<BooleanJunction> subJunction = qb.bool();
			for (String tagName : request.getTagNames()) {
				subJunction.should(qb.phrase().onField("tags.name").sentence(tagName).createQuery());
			}
			junction.must(subJunction.createQuery());
		}

		if (!CollectionUtils.isEmpty(request.getCustomFields())) {
			javax.persistence.Query query = entityManager.createQuery("from CustomField where language = :language and code in (:codes)", CustomField.class);
			query.setParameter("language", request.getLanguage())
					.setParameter("codes", request.getCustomFields().keySet());
			List<CustomField> customFields = query.getResultList();

			if (!CollectionUtils.isEmpty(customFields)) {
				Map<String, CustomField> customFieldMap = customFields.stream()
						.collect(Collectors.toMap(
								CustomField::getCode,
								Function.identity()
						));

				BooleanJunction<BooleanJunction> subJunction = qb.bool();
				for (String key : request.getCustomFields().keySet()) {
					List<Object> values = request.getCustomFields().get(key);
					CustomField target = customFieldMap.get(key);
					BooleanJunction<BooleanJunction> customFieldJunction = qb.bool();
					switch (target.getFieldType()) {
						case TEXT:
						case TEXTAREA:
						case HTML:
							for(Object value : values) {
								customFieldJunction.must(qb.keyword().onField("customFieldValues." + key).ignoreFieldBridge().matching(value.toString()).createQuery());
							}
							break;
						default:
							for(Object value : values) {
								customFieldJunction.must(qb.phrase().onField("customFieldValues." + key).ignoreFieldBridge().sentence(value.toString()).createQuery());
							}
					}
					subJunction.must(customFieldJunction.createQuery());
				}
				junction.must(subJunction.createQuery());
			}
		}

		if (request.getAuthorId() != null) {
			junction.must(qb.keyword().onField("author.id").matching(request.getAuthorId()).createQuery());
		}

		Query searchQuery = junction.createQuery();

		Sort sort = new Sort(
				new SortField("sortDate", SortField.Type.STRING, true),
				new SortField("sortId", SortField.Type.LONG, true));

		FullTextQuery persistenceQuery = fullTextEntityManager
				.createFullTextQuery(searchQuery, Article.class)
				.setCriteriaQuery(criteria)
				.setSort(sort);
		if (pageable.isPaged()) {
			persistenceQuery.setFirstResult((int) pageable.getOffset());
			persistenceQuery.setMaxResults(pageable.getPageSize());
		}
		return persistenceQuery;
	}
}
