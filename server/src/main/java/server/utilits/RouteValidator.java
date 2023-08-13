package server.utilits;

import collectionClasses.Route;

import java.util.LinkedHashSet;
import java.util.Set;

public class RouteValidator {
    private Set<Route> routesToCheck;

    public RouteValidator(Set<Route> routesToCheck) {
        this.routesToCheck = routesToCheck;
    }

    public boolean checkRouteValidity(Route route) {
        if (route.getId() <= 0) return false;
        if (isIdNotUnique((int) route.getId())) return false;
        if (route.getName() == null || route.getName().trim().isEmpty()) return false;
        if (route.getCoordinates() == null) return false;
        if (route.getCoordinates().getX() == null) return false;
        if (route.getCoordinates().getY() <= -647) return false;
        if (route.getCreationDate() == null) return false;
        if (route.getFrom() == null) return false;
        if (route.getTo() == null) return false;
        if (route.getDistance() == null || route.getDistance() <= 1) return false;

        return true;
    }

    private boolean isIdNotUnique(int id) {
        int count = 0;
        for (Route route : routesToCheck) {
            if (route.getId() == id) count++;
            if (count > 1) return true;
        }
        return false;
    }

    public void checkCollectionValidity() {
        Set<Route> routesToDelete = new LinkedHashSet<>();

        for (Route route : routesToCheck) {
            if (!checkRouteValidity(route)) {
                routesToDelete.add(route);
            }
        }

        if (!routesToDelete.isEmpty()) {
            System.out.println("В коллекции обнаружены некорректные данные. Некорректные элементы будут удалены.");

            for (Route route : routesToDelete) {
                routesToCheck.remove(route);
            }
        }
    }
}
