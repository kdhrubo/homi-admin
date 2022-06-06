

package com.tryhomi.admin.dashboard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.wallride.domain.Article;
//import org.wallride.domain.Blog;
//import org.wallride.domain.PopularPost;
//import org.wallride.domain.Post;
//import org.wallride.service.*;
//import org.wallride.web.controller.admin.article.ArticleSearchForm;


import java.util.List;
import java.util.SortedSet;

@Slf4j
@Controller
public class DashboardController {


	//private BlogService blogService;
	//private PostService postService;
	//private ArticleService articleService;
	//private PageService pageService;
	//private CategoryService categoryService;
	
	@RequestMapping({"/","/dashboard"})
	public String dashboard(RedirectAttributes redirectAttributes) {
		log.info("Here ---");
		//Blog blog = blogService.getBlogById(Blog.DEFAULT_ID);
		//String defaultLanguage = blog.getDefaultLanguage();
		//redirectAttributes.addAttribute("language", defaultLanguage);
		return "redirect:/_admin/{language}/";
	}

	/*
	@RequestMapping("/{language}/")
	public String dashboard(@PathVariable String language, Model model) {
		long articleCount = articleService.countArticlesByStatus(Post.Status.PUBLISHED, language);
		long pageCount = pageService.countPagesByStatus(Post.Status.PUBLISHED, language);
		long categoryCount = categoryService.getCategories(language).size();

		model.addAttribute("articleCount", articleCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("categoryCount", categoryCount);
		model.addAttribute("popularPosts", popularPosts(language));
		model.addAttribute("recentPublishedArticles", recentPublishedArticles(language));
		model.addAttribute("recentDraftArticles", recentDraftArticles(language));

		return "dashboard";
	}

	private SortedSet<PopularPost> popularPosts(String language) {
		return postService.getPopularPosts(language, PopularPost.Type.DAILY);
	}

	private List<Article> recentPublishedArticles(String language) {
		ArticleSearchForm form = new ArticleSearchForm();
		form.setStatus(Post.Status.PUBLISHED);
		Page<Article> page = articleService.getArticles(form.toArticleSearchRequest());
		return page.getContent();
	}

	private List<Article> recentDraftArticles(String language) {
		ArticleSearchForm form = new ArticleSearchForm();
		form.setStatus(Post.Status.DRAFT);
		Page<Article> page = articleService.getArticles(form.toArticleSearchRequest());
		return page.getContent();
	}

	 */
}
