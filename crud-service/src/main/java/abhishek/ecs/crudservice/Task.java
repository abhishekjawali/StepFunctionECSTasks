package abhishek.ecs.crudservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Task {

	@Value("${BUCKET_NAME}")
	private  String bucketName;

	@Value("${FILE_NAME}")
	private  String fileName;


    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

	// This task will print the input details to console
	public void performTask() {

		LOGGER.info("Processing the file contents");
		LOGGER.info("File Name " + fileName);
		LOGGER.info("BucketName "+ bucketName);
	}

}