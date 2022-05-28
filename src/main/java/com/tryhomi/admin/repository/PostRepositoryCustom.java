

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.Post;
import com.tryhomi.admin.model.PostSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PostRepositoryCustom {

	void lock(long id);

	Page<Post> search(PostSearchRequest request, Pageable pageable);
}
