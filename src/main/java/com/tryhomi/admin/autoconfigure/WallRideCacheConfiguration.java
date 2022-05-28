
package com.tryhomi.admin.autoconfigure;

import com.amazonaws.util.EC2MetadataUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableCaching
public class WallRideCacheConfiguration extends CachingConfigurerSupport {

	public static final String BLOG_CACHE = "blogs";
	public static final String POPULAR_POST_CACHE = "popularPosts";
	public static final String ARTICLE_CACHE = "articles";
	public static final String PAGE_CACHE = "pages";
	public static final String CATEGORY_CACHE = "categories";
	public static final String CUSTOM_FIELD_CACHE = "customFields";
	public static final String MEDIA_CACHE = "medias";
	public static final String BANNER_CACHE = "banners";
	public static final String USER_CACHE = "users";

	@Autowired
	private DataSource dataSource;

	@Autowired
	private Environment environment;

	@Autowired
	private DataSourceProperties dataSourceProperties;

	private static Logger logger = LoggerFactory.getLogger(WallRideCacheConfiguration.class);



	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new CacheKeyGenerator();
	}
}
