package barberon.barberonbe.Exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String message;
    private int status;
    private String error;
    private String path;

    public ErrorResponse(String message, HttpStatus status, String error, String path) {
        this.message = message;
        this.status = status.value();
        this.error = error;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
}