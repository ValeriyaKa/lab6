package collectionClasses;

import java.io.Serializable;
import java.util.Date;


public class Route implements Comparable<Route>, Serializable {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле не может быть null
    private Float distance; //Поле не может быть null, Значение поля должно быть больше 1


    public Route(long id, String name, Coordinates coordinates, Date creationDate, Location from, Location to, Float distance) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Route) {
            Route route = (Route) obj;
            return name.equals(route.getName()) && coordinates.equals(route.getCoordinates()) &&
                    (id == route.getId()) &&
                    (creationDate.equals(route.getCreationDate())) && (from == route.getFrom()) &&
                    (to.equals(route.getTo())) && (distance.equals(route.getDistance()));
        }
        return false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Location getTo() {
        return to;
    }

    public Location getFrom() {
        return from;
    }

    public Location setTo(Location to) {
        this.to = to;
        return to;
    }

    public Location setTFrom(Location from) {
        this.from = from;
        return from;
    }

    public Float setDistance(Float distance) {
        this.distance = distance;
        return distance;
    }

    public Float getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Route o) {
        return 0;
    }

}
