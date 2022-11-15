package br.com.java.Spring;

import br.com.java.Spring.view.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	@Autowired
	private Window window;

	public static void main(String[] args) {

		// Initializes the application and allows using the jframe
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);

	}

}
