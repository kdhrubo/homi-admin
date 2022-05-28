

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.User;
import com.tryhomi.admin.model.UserSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserRepositoryCustom {

	Page<User> search(UserSearchRequest request);
	Page<User> search(UserSearchRequest request, Pageable pageable);
	List<Long> searchForId(UserSearchRequest request);
}
