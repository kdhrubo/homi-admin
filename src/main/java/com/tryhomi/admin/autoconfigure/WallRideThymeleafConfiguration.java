
package com.tryhomi.admin.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import com.tryhomi.admin.service.ArticleService;
import com.tryhomi.admin.service.CategoryService;
import com.tryhomi.admin.service.PageService;
import com.tryhomi.admin.service.TagService;
import com.tryhomi.admin.support.ArticleUtils;
import com.tryhomi.admin.support.CategoryUtils;
import com.tryhomi.admin.support.PageUtils;
import com.tryhomi.admin.support.PostUtils;
import com.tryhomi.admin.support.TagUtils;
import com.tryhomi.admin.web.support.ExtendedThymeleafViewResolver;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
public class WallRideThymeleafConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private WallRideProperties wallRideProperties;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private PageService pageService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TagService tagService;

	@Inject
	private ThymeleafProperties thymeleafProperties;

	@Inject
	private Environment environment;

	@Bean
	public PostUtils postUtils(PageUtils pageUtils) {
		return new PostUtils(pageUtils);
	}

	@Bean
	public ArticleUtils articleUtils() {
		return new ArticleUtils(articleService);
	}

	@Bean
	public PageUtils pageUtils() {
		return new PageUtils(pageService);
	}

	@Bean
	public CategoryUtils categoryUtils() {
		return new CategoryUtils(categoryService);
	}

	@Bean
	public TagUtils tagUtils() {
		return new TagUtils(tagService);
	}

	@Bean
	@ConditionalOnMissingBean
	public WallRideThymeleafDialect wallRideThymeleafDialect(WallRideExpressionObjectFactory expressionObjectFactory) {
		return new WallRideThymeleafDialect(expressionObjectFactory);
	}

	@Bean
	@ConditionalOnMissingBean
	public WallRideExpressionObjectFactory wallRideExpressionObjectFactory() {
		WallRideExpressionObjectFactory expressionObjectFactory = new WallRideExpressionObjectFactory();
		ArticleUtils articleUtils = articleUtils();
		PageUtils pageUtils = pageUtils();
		expressionObjectFactory.setPostUtils(postUtils(pageUtils));
		expressionObjectFactory.setArticleUtils(articleUtils);
		expressionObjectFactory.setPageUtils(pageUtils);
		expressionObjectFactory.setCategoryUtils(categoryUtils());
		expressionObjectFactory.setTagUtils(tagUtils());
		expressionObjectFactory.setWallRideProperties(wallRideProperties);
		return expressionObjectFactory;
	}

	@Bean(name = {"defaultTemplateResolver", "homePathTemplateResolver"})
	public ITemplateResolver homePathTemplateResolver() {
		WallRideResourceTemplateResolver resolver = new WallRideResourceTemplateResolver();
//		resolver.setResourceResolver(wallRideResourceResourceResolver);
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix(wallRideProperties.getHome() + "themes/default/templates/");
		resolver.setSuffix(this.thymeleafProperties.getSuffix());
		resolver.setTemplateMode(this.thymeleafProperties.getMode());
		resolver.setCharacterEncoding(this.thymeleafProperties.getEncoding().name());
		resolver.setCacheable(this.thymeleafProperties.isCache());
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public ITemplateResolver classPathTemplateResolver() {
		WallRideResourceTemplateResolver resolver = new WallRideResourceTemplateResolver();
//		resolver.setResourceResolver(wallRideResourceResourceResolver);
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix(environment.getRequiredProperty("spring.thymeleaf.prefix.guest"));
		resolver.setSuffix(this.thymeleafProperties.getSuffix());
		resolver.setTemplateMode(this.thymeleafProperties.getMode());
		resolver.setCharacterEncoding(this.thymeleafProperties.getEncoding().name());
		resolver.setCacheable(this.thymeleafProperties.isCache());
		resolver.setOrder(2);
		return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine(WallRideThymeleafDialect wallRideThymeleafDialect) {
		SpringTemplateEngine engine = new SpringTemplateEngine();
//		engine.setTemplateResolver(templateResolver());
		Set<ITemplateResolver> templateResolvers = new LinkedHashSet<>();
		templateResolvers.add(homePathTemplateResolver());
		templateResolvers.add(classPathTemplateResolver());
		engine.setTemplateResolvers(templateResolvers);

		Set<IDialect> dialects = new HashSet<>();
		dialects.add(new SpringSecurityDialect());
		dialects.add(new Java8TimeDialect());
		dialects.add(wallRideThymeleafDialect);
		engine.setAdditionalDialects(dialects);
		return engine;
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ExtendedThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		viewResolver.setViewNames(this.thymeleafProperties.getViewNames());
		viewResolver.setCharacterEncoding(this.thymeleafProperties.getEncoding().name());
		viewResolver.setContentType(this.thymeleafProperties.getServlet().getContentType() + ";charset=" + this.thymeleafProperties.getEncoding());
		viewResolver.setCache(false);
		viewResolver.setOrder(2);
		return viewResolver;
	}
}
