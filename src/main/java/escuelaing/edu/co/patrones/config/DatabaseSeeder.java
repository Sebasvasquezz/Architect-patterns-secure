package escuelaing.edu.co.patrones.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import escuelaing.edu.co.patrones.model.User;
import escuelaing.edu.co.patrones.repository.UserRepository;

/**
 * Configuration class that seeds the database with initial data.
 * It creates a default "admin" user with a predefined password if it does not already exist.
 */
@Configuration
public class DatabaseSeeder {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructor that injects the UserRepository and initializes the password encoder.
     *
     * @param userRepository the repository used to interact with the User entity in the database.
     */
    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Bean that runs during the application startup to seed the database with default data.
     * If a user with the username "admin" does not already exist, it creates one with the password "admin123".
     *
     * @return ApplicationRunner that checks for the existence of the admin user and creates it if necessary.
     */
    @Bean
    public ApplicationRunner seedDatabase() {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User defaultUser = new User();
                defaultUser.setUsername("admin");
                defaultUser.setPassword(passwordEncoder.encode("admin123"));
                userRepository.save(defaultUser);
                System.out.println("Default admin user created: username=admin, password=admin123");
            } else {
                System.out.println("Admin user already exists. No changes made.");
            }
        };
    }
}
