package in.scalive.exception;

/**
 * CUSTOM EXCEPTION — Thrown when a POST /users request uses an email
 * that already belongs to an existing user.
 *
 * Why a custom exception?
 *   - Gives the error a meaningful name (better than a generic RuntimeException)
 *   - The GlobalExceptionHandler can catch THIS specific type and return
 *     exactly the right HTTP status code (409 Conflict) and message
 *   - Keeps error-handling logic OUT of the service and controller
 *
 * Extends RuntimeException (unchecked) so we don't need try/catch everywhere.
 */
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
