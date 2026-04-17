package in.scalive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Boot application.
 *
 * @SpringBootApplication is a convenience annotation that combines:
 *   - @Configuration       : marks this as a source of bean definitions
 *   - @EnableAutoConfiguration : tells Spring Boot to auto-configure based on classpath
 *   - @ComponentScan       : scans this package and sub-packages for Spring components
 *
 * When you run this class, Spring Boot:
 *   1. Starts an embedded Tomcat server on port 8080
 *   2. Creates the H2 in-memory database
 *   3. Registers all @RestController, @Service, @Repository beans
 *   4. The server is now ready to accept HTTP requests — completely stateless
 */
@SpringBootApplication
public class UserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
        System.out.println("\n✅ User Management API is running!");
        System.out.println("   → API Base URL  : http://localhost:8080");
        System.out.println("   → GET  users    : http://localhost:8080/users");
        System.out.println("   → POST users    : http://localhost:8080/users");
        System.out.println("   → H2 Console    : http://localhost:8080/h2-console\n");
    }
}
