package barberon.barberonbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

@EntityScan(basePackages = { "barberon.barberonbe.model" })

public class BarberOnApp {

	public static void main(String[] args) {
		SpringApplication.run(BarberOnApp.class, args);
	}

}
