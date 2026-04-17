package in.scalive.controller;

import in.scalive.model.User;
import in.scalive.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * CONTROLLER LAYER — Handles incoming HTTP requests and returns HTTP responses.
 *
 * This is the "front door" of your REST API. Its only job is:
 *   1. Receive the HTTP request
 *   2. Delegate work to the Service layer
 *   3. Return an appropriate HTTP response (status code + JSON body)
 *
 * Key Annotations:
 *   @RestController  = @Controller + @ResponseBody
 *                      Every method return value is written directly into the
 *                      HTTP response body as JSON (via Jackson)
 *
 *   @RequestMapping  = sets the base URL path for all methods in this class
 *
 * REST Fundamentals demonstrated here:
 *   - Stateless: No HttpSession. Each request carries all info it needs.
 *   - Uniform Interface: GET = read, POST = create
 *   - JSON: Spring automatically serializes/deserializes Java ↔ JSON
 *   - HTTP Status Codes: 200, 201, 400, 409 used meaningfully
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  GET /users
    //  → Returns a list of all users
    //  → HTTP 200 OK (success, resource retrieved)
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Handles: GET http://localhost:8080/users
     *
     * ResponseEntity<List<User>> lets us control:
     *   - The response BODY  : the list of users (serialized to JSON array)
     *   - The status CODE    : 200 OK
     *
     * Sample Response:
     * HTTP/1.1 200 OK
     * [
     *   { "id": 1, "name": "Alice", "email": "alice@example.com", "createdAt": "..." },
     *   { "id": 2, "name": "Bob",   "email": "bob@example.com",   "createdAt": "..." }
     * ]
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);  // 200 OK
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  POST /users
    //  → Creates a new user from the JSON request body
    //  → HTTP 201 Created (success, new resource created)
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Handles: POST http://localhost:8080/users
     *          Content-Type: application/json
     *          Body: { "name": "Alice", "email": "alice@example.com" }
     *
     * @RequestBody   : Jackson reads the JSON body and maps it to a User object
     * @Valid         : triggers field validation (@NotBlank, @Email, @Size)
     *                  If validation fails, Spring throws MethodArgumentNotValidException
     *                  BEFORE this method even runs — the GlobalExceptionHandler catches it
     *
     * Sample Response on success:
     * HTTP/1.1 201 Created
     * {
     *   "id": 1,
     *   "name": "Alice",
     *   "email": "alice@example.com",
     *   "createdAt": "2024-01-15T10:30:00"
     * }
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);  // 201 Created
    }
}
