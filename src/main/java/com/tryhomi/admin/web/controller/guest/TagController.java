package com.tryhomi.admin.web.controller.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tryhomi.admin.domain.BlogLanguage;
import com.tryhomi.admin.domain.Post;
import com.tryhomi.admin.domain.Tag;
import com.tryhomi.admin.model.PostSearchRequest;
import com.tryhomi.admin.model.TagSearchRequest;
import com.tryhomi.admin.service.PostService;
import com.tryhomi.admin.service.TagService;
import com.tryhomi.admin.web.support.HttpNotFoundException;
import com.tryhomi.admin.web.support.Pagination;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tag")
public class TagController {

	@Autowired
	private TagService tagService;
	@Autowired
	private PostService postService;

	@RequestMapping
	public String index(
			@PageableDefault Pageable pageable,
			Model model,
			HttpServletRequest servletRequest) {
		Page<Tag> tags = tagService.getTags(new TagSearchRequest(), pageable);
		model.addAttribute("tags", tags);
		model.addAttribute("pageable", pageable);
		model.addAttribute("pagination", new Pagination<>(tags, servletRequest));
		return "tag/index";
	}

	@RequestMapping("/{name}")
	public String post(
			@PathVariable String name,
			@PageableDefault Pageable pageable,
			BlogLanguage blogLanguage,
			Model model,
			HttpServletRequest servletRequest) {
		Tag tag = tagService.getTagByName(name, blogLanguage.getLanguage());
		if (tag == null) {
			tag = tagService.getTagByName(name, blogLanguage.getBlog().getDefaultLanguage());
		}
		if (tag == null) {
			throw new HttpNotFoundException();
		}

		PostSearchRequest request = new PostSearchRequest(tag.getLanguage());
		request.withTagNames(name);

		Page<Post> posts = postService.getPosts(request, pageable);
		model.addAttribute("tag", tag);
		model.addAttribute("posts", posts);
		model.addAttribute("pageable", pageable);
		model.addAttribute("pagination", new Pagination<>(posts, servletRequest));
		return "tag/post";
	}
}
