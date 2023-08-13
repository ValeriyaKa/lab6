package responce;

public class ErrorResponse extends Response {
    private String errorMessage;

    public ErrorResponse(String name, String errorMessage) {
        super(name, errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return "Error: " + errorMessage;
    }
}
