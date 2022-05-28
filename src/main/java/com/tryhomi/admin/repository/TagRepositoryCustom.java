

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.Tag;
import com.tryhomi.admin.model.TagSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface TagRepositoryCustom {

	Page<Tag> search(TagSearchRequest request, Pageable pageable);
}
