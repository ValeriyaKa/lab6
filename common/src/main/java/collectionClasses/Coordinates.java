package collectionClasses;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private double x;
    private Long y; //Поле не может быть null

    public Coordinates(double x, long y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
