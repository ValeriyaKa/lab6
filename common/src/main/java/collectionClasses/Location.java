package collectionClasses;


import java.io.Serializable;

public class Location implements Serializable {
    private double x;
    private long y;
    private Integer z; //Поле не может быть null(to)//Поле может быть null(from)
    private String name; //Поле не может быть null

    public Location(double x, long y, Integer z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
