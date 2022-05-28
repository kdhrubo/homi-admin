

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.Category;
import com.tryhomi.admin.model.CategorySearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryRepositoryCustom {

	void lock(long id);
	Page<Category> search(CategorySearchRequest request);
	Page<Category> search(CategorySearchRequest request, Pageable pageable);
}
