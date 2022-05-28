

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.Comment;
import com.tryhomi.admin.model.CommentSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CommentRepositoryCustom {

	Page<Comment> search(CommentSearchRequest request, Pageable pageable);
}
