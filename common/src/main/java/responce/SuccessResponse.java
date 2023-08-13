package responce;

import java.util.Objects;

public class SuccessResponse extends Response {
    private final String message;

    public SuccessResponse(String name, String message) {
        super(name, message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuccessResponse that = (SuccessResponse) o;
        return Objects.equals(message, that.message) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, getName());
    }

    @Override
    public String toString() {
        return "SuccessResponse{" +
                "name='" + getName() + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
