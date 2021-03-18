package abhishek.ecs.crudservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CrudServiceApplication {


	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(CrudServiceApplication.class, args);
		Task task = applicationContext.getBean(Task.class);
		task.performTask();
				
		System.exit(0);

	}

}
