package bg.softuni.ut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UnderwaterTourismApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnderwaterTourismApplication.class, args);
	}

}
