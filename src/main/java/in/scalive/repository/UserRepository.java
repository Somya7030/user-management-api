package in.scalive.repository;

import in.scalive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * REPOSITORY LAYER — Handles all database operations for User.
 *
 * By extending JpaRepository<User, Long>, Spring Data JPA auto-generates
 * implementations for common database operations. We get these for FREE:
 *
 *   save(user)        → INSERT or UPDATE a user row
 *   findAll()         → SELECT * FROM users
 *   findById(id)      → SELECT * FROM users WHERE id = ?
 *   deleteById(id)    → DELETE FROM users WHERE id = ?
 *   existsById(id)    → SELECT COUNT(*) ... WHERE id = ?
 *   count()           → SELECT COUNT(*) FROM users
 *
 * We only need to declare CUSTOM queries here (like findByEmail below).
 * Everything else is inherited — no SQL needed!
 *
 * REST Relevance: The repository is the only layer that touches the database.
 * This keeps the rest of the code clean and testable.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Custom query: find a user by their email address.
     *
     * Spring Data JPA reads the method name and generates:
     *   SELECT * FROM users WHERE email = ?
     *
     * Returns Optional<User> so the caller must handle the "not found" case
     * explicitly — no NullPointerException surprises.
     *
     * Used in UserService to check for duplicate emails before creating a user.
     */
    Optional<User> findByEmail(String email);
}
