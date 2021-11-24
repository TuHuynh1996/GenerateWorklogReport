package gwr.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The Class DemoApplication.
 */
@SpringBootApplication(scanBasePackages = {"gwr"})
@ComponentScan(basePackages = "gwr")
@EntityScan(basePackages = "gwr")
@EnableJpaRepositories(basePackages = "gwr")
public class DemoApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
