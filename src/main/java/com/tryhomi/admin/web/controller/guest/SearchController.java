/*
 * Copyright 2014 Tagbangers, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tryhomi.admin.web.controller.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tryhomi.admin.domain.BlogLanguage;
import com.tryhomi.admin.domain.Post;
import com.tryhomi.admin.model.PostSearchRequest;
import com.tryhomi.admin.service.PostService;
import com.tryhomi.admin.web.support.Pagination;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private PostService postService;

	@RequestMapping
	public String search(
			@RequestParam String keyword,
			@PageableDefault(50) Pageable pageable,
			BlogLanguage blogLanguage,
			Model model,
			HttpServletRequest servletRequest) {
		PostSearchRequest request = new PostSearchRequest(blogLanguage.getLanguage()).withKeyword(keyword);
		Page<Post> posts = postService.getPosts(request, pageable);
		model.addAttribute("keyword", keyword);
		model.addAttribute("posts", posts);
		model.addAttribute("pageable", pageable);
		model.addAttribute("pagination", new Pagination<>(posts, servletRequest));
		return "search";
	}
}
