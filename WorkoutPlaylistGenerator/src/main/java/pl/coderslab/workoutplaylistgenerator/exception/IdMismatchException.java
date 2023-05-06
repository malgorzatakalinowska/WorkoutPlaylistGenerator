package pl.coderslab.workoutplaylistgenerator.exception;

public class IdMismatchException extends RuntimeException{
    private String errorCode = "CODE1";

    public IdMismatchException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public IdMismatchException(String message) {
        super(message);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
