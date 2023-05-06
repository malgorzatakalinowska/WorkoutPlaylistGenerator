package pl.coderslab.workoutplaylistgenerator.exception;

public class ResourceNotFoundException extends RuntimeException {
    private String errorCode = "CODE1";

    public ResourceNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Long id) {
        super(String.format("Resource with %d not found", id));
    }

    public String getErrorCode() {
        return errorCode;
    }
}
