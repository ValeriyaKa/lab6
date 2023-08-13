package utils;

import collectionClasses.Route;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private String name;
    private String argument;
    private Route route;

    public Request(String name, String argument, Route route) {
        this.name = name;
        this.argument = argument;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public String getArgument() {
        return argument;
    }

    public Route getRoute() {
        return route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(name, request.name) && Objects.equals(argument, request.argument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, argument);
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", argument='" + argument + '\'' +
                '}';
    }
}
