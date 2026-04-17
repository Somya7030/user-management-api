package in.scalive.service;

import in.scalive.model.User;
import in.scalive.repository.UserRepository;
import in.scalive.exception.DuplicateEmailException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // ⭐ VERY IMPORTANT
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new DuplicateEmailException("Email already exists");
                });

        return userRepository.save(user);
    }
}