

package com.tryhomi.admin.web.controller.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tryhomi.admin.domain.Article;
import com.tryhomi.admin.domain.BlogLanguage;
import com.tryhomi.admin.service.ArticleService;
import com.tryhomi.admin.web.controller.guest.article.ArticleSearchForm;
import com.tryhomi.admin.web.support.Pagination;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping
	public String index(
			@PageableDefault(10) Pageable pageable,
			BlogLanguage blogLanguage,
			Model model,
			HttpServletRequest servletRequest) {
		ArticleSearchForm form = new ArticleSearchForm() {};
		form.setLanguage(blogLanguage.getLanguage());

		Page<Article> articles = articleService.getArticles(form.toArticleSearchRequest(), pageable);
		model.addAttribute("articles", articles);
		model.addAttribute("pageable", pageable);
		model.addAttribute("pagination", new Pagination<>(articles, servletRequest));
		return "index";
//
//
//		Blog blog = blogService.getBlogById(Blog.DEFAULT_ID);
//		String defaultLanguage = blog.getDefaultLanguage();
//		redirectAttributes.addAttribute("language", defaultLanguage);
//		return "redirect:/{language}/";
	}
}
