package in.scalive.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * MODEL LAYER — Represents a "User" in our system.
 *
 * This class has two roles:
 *   1. @Entity  : maps to a "users" table in the H2 database (via JPA/Hibernate)
 *   2. Validation : field-level rules (@NotBlank, @Email, @Size) are checked
 *                   automatically when @Valid is used in the controller
 *
 * REST Relevance: This object is serialized to/from JSON automatically by
 * Spring's Jackson library. No manual JSON parsing needed.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Primary key — auto-incremented by the database.
     * Clients never send this; the server assigns it.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User's full name.
     * @NotBlank : must not be null or whitespace-only
     * @Size     : must be between 2 and 50 characters
     */
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String name;

    /**
     * User's email address.
     * @NotBlank : must not be null or empty
     * @Email    : must match a valid email format (e.g. user@example.com)
     * unique    : no two users can share the same email
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid format (e.g. user@example.com)")
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Timestamp set automatically when the user is created.
     * Clients never send this — the server sets it.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Sets createdAt before the entity is first saved to the database.
     * @PrePersist runs automatically — no manual call needed.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ── Constructors ──────────────────────────────────────────────────────────

    public User() {
        // Required by JPA
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────
    // Jackson uses these to convert the object ↔ JSON

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
