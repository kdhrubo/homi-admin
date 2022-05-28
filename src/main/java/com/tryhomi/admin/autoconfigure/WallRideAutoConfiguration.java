

package com.tryhomi.admin.autoconfigure;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import com.tryhomi.admin.repository.BlogRepository;
import com.tryhomi.admin.service.BlogService;

@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(WallRideProperties.class)
@EnableAsync
@EnableJpaRepositories(basePackageClasses = BlogRepository.class)
@Import({
		WallRideCacheConfiguration.class,
		WallRideJobConfiguration.class,
		WallRideJpaConfiguration.class,
		WallRideMailConfiguration.class,
		WallRideMessageSourceConfiguration.class,
		WallRideScheduleConfiguration.class,
		WallRideSecurityConfiguration.class,
		WallRideServletConfiguration.class,
		WallRideThymeleafConfiguration.class,
		WallRideWebMvcConfiguration.class,
})
@ComponentScan(basePackageClasses = BlogService.class)
public class WallRideAutoConfiguration {

	@Bean
	public AmazonS3 amazonS3() {
//		final String accessKey = environment.getRequiredProperty("aws.accessKey");
//		final String secretKey = environment.getRequiredProperty("aws.secretKey");
		ClientConfiguration configuration = new ClientConfiguration();
		configuration.setMaxConnections(1000);
//		return new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey), configuration);
		return new AmazonS3Client(configuration);
	}
}
