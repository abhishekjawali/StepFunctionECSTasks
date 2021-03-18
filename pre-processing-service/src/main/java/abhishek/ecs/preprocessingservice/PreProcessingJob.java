package abhishek.ecs.preprocessingservice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class PreProcessingJob {
    
    private static final Log logger = LogFactory.getLog(PreProcessingJob.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private S3Process s3Process;

    @Value("${SOURCE_BUCKET_NAME}")
    private String sourceBucketName;

    @Value("${DESTINATION_BUCKET_NAME}")
    private String destinationBucketName;

    @Value("${OBJECT_KEY}")
    private String objectKey;

    @Value("${AWS_REGION}")
    private String awsRegion;

	@Bean
	public Job job1() {
		return this.jobBuilderFactory.get("job1")
			.start(this.stepBuilderFactory.get("job1step1")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						logger.info("Executing Pre-processing job");

                        // Create S3Client object
                        //Region region = Region.US_EAST_1;
                        Region region = Region.of(awsRegion);
                        S3Client s3 = S3Client.builder().region(region).build();
                        //S3Client s3 = S3Client.builder().build();

                        
                        //String bucketName = "abhi-stepfunction-ecstask-example";
                        logger.info("Listing objects in source bucket");
                        s3Process.listObjects(s3, sourceBucketName);

                        // Pre-process the object

                        // Copy the object to destination S3 bucket
                        logger.info("Lcopying from source to destination");
                        s3Process.copyObjet(s3, sourceBucketName, destinationBucketName, objectKey);
                        
                        logger.info("Listing objects in destination bucket");
                        s3Process.listObjects(s3, destinationBucketName);

						return RepeatStatus.FINISHED;
					}
				})
				.build())
			.build();
	}

    

	
}
