package in.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@ComponentScan(basePackages = "in.springboot")
@EnableJpaRepositories("in.springboot.repository")
@EntityScan("in.springboot.entity")
@EnableTransactionManagement
public class JournalappApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalappApplication.class, args);
	}

}
