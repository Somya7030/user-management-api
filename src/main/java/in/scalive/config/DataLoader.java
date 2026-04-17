package in.scalive.config;

import in.scalive.model.User;
import in.scalive.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    // Constructor Injection (IMPORTANT)
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        userRepository.save(new User(null, "Alice", "alice@example.com"));
        userRepository.save(new User(null, "Bob", "bob@example.com"));
    }
}