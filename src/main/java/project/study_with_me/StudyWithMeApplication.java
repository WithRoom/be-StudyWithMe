package project.study_with_me;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StudyWithMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyWithMeApplication.class, args);
	}

}
