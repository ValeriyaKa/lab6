package responce;

import java.io.Serializable;
import java.util.Objects;

public abstract class Response implements Serializable {
    private final String name;
    private final String message;

    public Response(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(name, response.name) && Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, message);
    }

    @Override
    public String toString() {
        return "Response{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
