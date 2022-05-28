

package com.tryhomi.admin.repository;

import com.tryhomi.admin.domain.Page;
import com.tryhomi.admin.model.PageSearchRequest;
import org.springframework.data.domain.Pageable;



import java.util.List;

public interface PageRepositoryCustom {

	org.springframework.data.domain.Page<Page> search(PageSearchRequest request);
	org.springframework.data.domain.Page<Page> search(PageSearchRequest request, Pageable pageable);
	List<Long> searchForId(PageSearchRequest request);
}
