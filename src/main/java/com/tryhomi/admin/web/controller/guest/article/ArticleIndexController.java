

package com.tryhomi.admin.web.controller.guest.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;
import com.tryhomi.admin.domain.Article;
import com.tryhomi.admin.domain.BlogLanguage;
import com.tryhomi.admin.domain.Category;
import com.tryhomi.admin.domain.User;
import com.tryhomi.admin.service.ArticleService;
import com.tryhomi.admin.service.CategoryService;
import com.tryhomi.admin.service.UserService;
import com.tryhomi.admin.web.support.HttpNotFoundException;
import com.tryhomi.admin.web.support.Pagination;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class ArticleIndexController {

//	/article/[:yyyy]/[:mm]/[:dd]/[:code]
//	/categories/[:code]/[:code]/[:code]/[:code]/
//	/tag/[:code]/

	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;

	@RequestMapping("/{year:[0-9]{4}}")
	public String year(
			@PathVariable int year,
			@PageableDefault(10) Pageable pageable,
			BlogLanguage blogLanguage,
			HttpServletRequest servletRequest,
			Model model) {
		ArticleSearchForm form = new ArticleSearchForm() {};
		form.setLanguage(blogLanguage.getLanguage());
		form.setDateFrom(LocalDateTime.of(year, 1, 1, 0, 0, 0));
		form.setDateTo(LocalDateTime.of(year, 12, 31, 0, 0, 0));

		Page<Article> articles = articleService.getArticles(form.toArticleSearchRequest(), pageable);
		model.addAttribute("articles", articles);
		model.addAttribute("pageable", pageable);
		model.addAttribute("pagination", new Pagination<>(articles, servletRequest));
		return "article/index";
	}

	@RequestMapping("/{year:[0-9]{4}}/{month:[0-9]{2}}")
	public String month(
			@PathVariable int year,
			@PathVariable int month,
			@PageableDefault(10) Pageable pageable,
			BlogLanguage blogLanguage,
			HttpServletRequest servletRequest,
			Model model) {
		ArticleSearchForm form = new ArticleSearchForm() {};
		form.setLanguage(blogLanguage.getLanguage());
		LocalDateTime date = LocalDateTime.of(year, month, 1, 0, 0, 0);
		form.setDateFrom(LocalDateTime.of(year, month, 1, 0, 0, 0));
		form.setDateTo(LocalDateTime.of(year, month, date.getMonth().length(true), 23, 59, 59));

		Page<Article> articles = articleService.getArticles(form.toArticleSearchRequest(), pageable);
		model.addAttribute("articles", articles);
		model.addAttribute("pageable", pageable);
		model.addAttribute("pagination", new Pagination<>(articles, servletRequest));
		return "article/index";
	}

	@RequestMapping("/{year:[0-9]{4}}/{month:[0-9]{2}}/{day:[0-9]{2}}")
	public String day(
			@PathVariable int year,
			@PathVariable int month,
			@PathVariable int day,
			@PageableDefault(10) Pageable pageable,
			BlogLanguage blogLanguage,
			HttpServletRequest servletRequest,
			Model model) {
		ArticleSearchForm form = new ArticleSearchForm() {};
		form.setLanguage(blogLanguage.getLanguage());
		form.setDateFrom(LocalDateTime.of(year, month, day, 0, 0, 0));
		form.setDateTo(LocalDateTime.of(year, month, day, 23, 59, 59));

		Page<Article> articles = articleService.getArticles(form.toArticleSearchRequest(), pageable);
		model.addAttribute("articles", articles);
		model.addAttribute("pageable", pageable);
		model.addAttribute("pagination", new Pagination<>(articles, servletRequest));
		return "article/index";
	}

	@RequestMapping("/author/{loginId}")
	public String author(
			@PathVariable String loginId,
			@PageableDefault(10) Pageable pageable,
			BlogLanguage blogLanguage,
			HttpServletRequest servletRequest,
			Model model) {
		User author = userService.getUserByLoginId(loginId);
		if (author == null) {
			throw new HttpNotFoundException();
		}

		ArticleSearchForm form = new ArticleSearchForm() {};
		form.setLanguage(blogLanguage.getLanguage());
		form.setAuthorId(author.getId());

		Page<Article> articles = articleService.getArticles(form.toArticleSearchRequest(), pageable);
		model.addAttribute("author", author);
		model.addAttribute("articles", articles);
		model.addAttribute("pageable", pageable);
		model.addAttribute("pagination", new Pagination<>(articles, servletRequest));
		return "article/author";
	}
}
