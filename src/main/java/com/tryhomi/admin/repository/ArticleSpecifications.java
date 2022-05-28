

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.Article;
import org.springframework.data.jpa.domain.Specification;



import javax.persistence.criteria.Predicate;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.List;

public class ArticleSpecifications {

	public static Specification<Article> draft(Article article) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			//TODO - review

			//predicates.add(cb.equal(root.get(Article_.drafted), article));

			Subquery<Long> subquery = query.subquery(Long.class);
			Root<Article> p = subquery.from(Article.class);
			//TODO - review
			//subquery.select(cb.max(p.get(Article_.id))).where(cb.equal(p.get(Article_.drafted), article));

			//predicates.add(cb.equal(root.get(Article_.id), subquery));
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
