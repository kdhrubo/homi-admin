

package com.tryhomi.admin.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.tryhomi.admin.domain.Blog;
import com.tryhomi.admin.domain.BlogLanguage;
import com.tryhomi.admin.domain.PopularPost;
import com.tryhomi.admin.service.BlogService;
import com.tryhomi.admin.service.PostService;

import javax.inject.Inject;

@Configuration
@EnableScheduling
public class WallRideScheduleConfiguration {

	private static final int POPULAR_POST_MAX_RANK = 5;

	@Inject
	private BlogService blogService;
	@Inject
	private PostService postService;

	@Scheduled(cron="0 */5 * * * *")
	public void publishPost() {
		postService.publishScheduledPosts();
	}

	@Scheduled(cron="0 0 3,15 * * *")
	public void updatePostViews() {
		postService.updatePostViews();
	}

	@Scheduled(cron="0 0 4,16 * * *")
	public void updatePopularPosts() {
		Blog blog = blogService.getBlogById(Blog.DEFAULT_ID);
		for (BlogLanguage blogLanguage : blog.getLanguages()) {
			for (PopularPost.Type type : PopularPost.Type.values()) {
				postService.updatePopularPosts(blogLanguage, type, POPULAR_POST_MAX_RANK);
			}
		}
	}
}
