

package com.tryhomi.admin.web.controller.admin.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tryhomi.admin.domain.Post;
import com.tryhomi.admin.model.PostSearchRequest;
import com.tryhomi.admin.service.PostService;
import com.tryhomi.admin.web.support.DomainObjectSelect2Model;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostSelectController {

	@Autowired
	private PostService postService;

	@RequestMapping(value="/{language}/posts/select")
	public @ResponseBody List<DomainObjectSelect2Model> select(
			@PathVariable String language,
			@RequestParam(required=false) String keyword) {
		PostSearchRequest request = new PostSearchRequest(language)
				.withStatus(Post.Status.PUBLISHED)
				.withKeyword(keyword);
		Page<Post> posts = postService.getPosts(request, PageRequest.of(0, 30));

		List<DomainObjectSelect2Model> results = new ArrayList<>();
		if (posts.hasContent()) {
			/*
			for (Post post : posts) {
				DomainObjectSelect2Model model = new DomainObjectSelect2Model(post);
				results.add(model);
			}*/
		}
		return results;
	}

	@RequestMapping(value="/{language}/posts/select/{id}")
	public @ResponseBody
	DomainObjectSelect2Model select(
			@PathVariable String language,
			@RequestParam Long id,
			HttpServletResponse response)
			throws IOException {
		Post post = postService.getPostById(id, language);
		if (post == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		//DomainObjectSelect2Model model = new DomainObjectSelect2Model(post);
		//return model;

		return null;
	}
}
