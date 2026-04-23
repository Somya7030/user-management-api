package in.scalive.exception;


import java.time.LocalDateTime;
import java.util.List;

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
