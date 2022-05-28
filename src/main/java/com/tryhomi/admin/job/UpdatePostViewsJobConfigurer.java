

package com.tryhomi.admin.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;



@Import({
		UpdatePostViewsItemReader.class,
		UpdatePostViewsItemWriter.class,
})
public class UpdatePostViewsJobConfigurer {

	@Autowired
	private JobBuilderFactory jobBuilders;
	@Autowired
	private StepBuilderFactory stepBuilders;

	@Autowired
	private UpdatePostViewsItemReader updatePostViewsItemReader;
	@Autowired
	private UpdatePostViewsItemWriter updatePostViewsItemWriter;

	@Bean
	public Job updatePostViewsJob() {
		return jobBuilders.get("updatePostViewsJob")
				.start(updatePostViewsStep())
				.build();
	}

	public Step updatePostViewsStep() {
		return stepBuilders.get("updatePostViewsStep")
				.chunk(10)
				.reader((ItemReader) updatePostViewsItemReader)
				.writer((ItemWriter) updatePostViewsItemWriter)
				.build();
	}
}
