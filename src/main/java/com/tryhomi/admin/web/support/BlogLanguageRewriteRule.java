

package com.tryhomi.admin.web.support;

import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UrlPathHelper;
import org.tuckey.web.filters.urlrewrite.extend.RewriteMatch;
import org.tuckey.web.filters.urlrewrite.extend.RewriteRule;
import com.tryhomi.admin.autoconfigure.WallRideServletConfiguration;
import com.tryhomi.admin.domain.Blog;
import com.tryhomi.admin.domain.BlogLanguage;
import com.tryhomi.admin.service.BlogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlogLanguageRewriteRule extends RewriteRule {

	private BlogService blogService;

	public BlogLanguageRewriteRule(BlogService blogService) {
		this.blogService = blogService;
	}

	// https://github.com/paultuckey/urlrewritefilter/issues/136
//	@Override
//	public boolean initialise(ServletContext servletContext) {
//		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		blogService = context.getBean(BlogService.class);
//		return super.initialise(servletContext);
//	}

	@Override
	public RewriteMatch matches(HttpServletRequest request, HttpServletResponse response) {
		UrlPathHelper urlPathHelper = new UrlPathHelper();

		String servletPath = urlPathHelper.getOriginatingServletPath(request);
		if (ObjectUtils.nullSafeEquals(servletPath, WallRideServletConfiguration.ADMIN_SERVLET_PATH)) {
			return null;
		}

		String lookupPath = urlPathHelper.getLookupPathForRequest(request);

		Blog blog = blogService.getBlogById(Blog.DEFAULT_ID);
		if (blog == null) {
			return null;
		}

		BlogLanguage matchedBlogLanguage = null;
		for (BlogLanguage blogLanguage : blog.getLanguages()) {
			if (lookupPath.startsWith("/" + blogLanguage.getLanguage() + "/")) {
				matchedBlogLanguage = blogLanguage;
				break;
			}
		}

		if (matchedBlogLanguage == null) {
			matchedBlogLanguage = blog.getLanguage(blog.getDefaultLanguage());
		}

		return new BlogLanguageRewriteMatch(matchedBlogLanguage);
	}
}
