
package com.tryhomi.admin.autoconfigure;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.tryhomi.admin.job.UpdatePostViewsJobConfigurer;

@Configuration
@Import({
		UpdatePostViewsJobConfigurer.class,
})
@EnableBatchProcessing
public class WallRideJobConfiguration {

}
