package escuelaing.edu.co.patrones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Property Application.
 * This class uses Spring Boot's auto-configuration to launch the application.
 */
@SpringBootApplication
public class PropertyApplication {

	/**
     * The main method that serves as the starting point for the Spring Boot application.
     * It initializes and runs the Property Application.
     *
     * @param args command-line arguments passed during application startup.
     */
	public static void main(String[] args) {
		SpringApplication.run(PropertyApplication.class, args);
	}

}
