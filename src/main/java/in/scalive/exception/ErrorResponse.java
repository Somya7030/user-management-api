package in.scalive.exception;


import java.time.LocalDateTime;
import java.util.List;

/**
 * ERROR RESPONSE DTO — A structured JSON shape for all error responses.
 *
 * Instead of returning a raw error string, we always return a consistent
 * JSON object so the client (frontend / mobile app) can reliably parse it.
 *
 * Example JSON output:
 * {
 *   "timestamp": "2024-01-15T10:30:00",
 *   "status": 400,
 *   "error": "Bad Request",
 *   "messages": ["Name is required", "Email must be a valid format"],
 *   "path": "/users"
 * }
 *
 * DTO = Data Transfer Object — a plain class used only to carry data,
 * no business logic here.
 */
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private List<String> messages;
    private String path;

    public ErrorResponse(int status, String error, List<String> messages, String path, LocalDateTime timestamp) {
        this.timestamp = timestamp;  // ✔ use passed value
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.path = path;
    }

    // Getters — Jackson needs these to serialize to JSON
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public List<String> getMessages() { return messages; }
    public String getPath() { return path; }
}
