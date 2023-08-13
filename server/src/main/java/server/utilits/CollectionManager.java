package server.utilits;

import collectionClasses.Route;

import java.util.*;

public class CollectionManager {
    private final Set<Route> collection;
    private RouteValidator routeValidator;


    public CollectionManager() {
        this.collection = new LinkedHashSet<>();
        sortRoutesFromFile();
        this.routeValidator = new RouteValidator(collection);
    }


    public boolean addRoute(Route route) {
        return collection.add(route);
    }

    public LinkedHashSet<Route> getCollection() {
        return (LinkedHashSet<Route>) collection;
    }

    public void removeRoute(long route) {
        collection.remove(route);
    }

    public boolean getById(long id) {
        for (Route route : collection) {
            if (route.getId() == (id))
                return true;
        }
        return false;
    }


    public long generateNextId() {
        long nextId = 0;
        for (Route route : collection) {
            if (route.getId() >= nextId) {
                nextId = route.getId();
            }
        }
        return (nextId + 1);
    }

    public void sortRoutesFromFile() {
        Set<Route> sortedRoutes = new TreeSet<>(Comparator.comparing(Route::toString));
        try {
            sortedRoutes.addAll(collection);
            // Assuming that 'collection' is a field of the class
            collection.addAll(sortedRoutes);
        } catch (Exception e) {
            System.out.println("Ошибка загрузки файла: " + e.getMessage());
        }
    }


    public List<Route> getAllRoutes() {
        List<Route> routeList = new ArrayList<>();
        for (Route route : collection) {
            routeList.add(route);
        }
        return routeList;
    }

    public void clear() {
        collection.clear();
    }

    public boolean loadCollection() {
        CSVFile csvFile = new CSVFile();
        return csvFile.loadFile();
    }


    public boolean saveCollectionToFile() {
        try {
            CSVFile csvFile = new CSVFile();

            for (Route route : collection) {
                csvFile.addRoute(route);
            }
            csvFile.saveFile();
            System.out.println("Коллекция успешно сохранена в файл " + csvFile.getFile());
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении коллекции в файл: " + e.getMessage());
        }
        return false;

    }

    public void printRouteList() {
        for (Route route : collection) {
            Output.println("id: " + route.getId());
            Output.println("name: " + route.getName());
            Output.println("coordinates: X:" + route.getCoordinates().getX() + " Y:" + route.getCoordinates().getY());
            Output.println("creation_date: " + route.getCreationDate());
            Output.println("distance: " + route.getDistance());
            Output.println("location_from: X:" + route.getFrom().getX() + " Y:" + route.getFrom().getY() + " Z:" + route.getFrom().getZ() + " name:" + route.getFrom().getName());
            Output.println("location_to: X:" + route.getTo().getX() + " Y:" + route.getTo().getY() + " Z:" + route.getTo().getZ() + " name:" + route.getTo().getName());
            Output.println("------------------------------------------");
        }
    }

    public void printRoute(Route route) {
        Output.println("id: " + route.getId());
        Output.println("name: " + route.getName());
        Output.println("coordinates: X:" + route.getCoordinates().getX() + " Y:" + route.getCoordinates().getY());
        Output.println("creation_date: " + route.getCreationDate());
        Output.println("distance: " + route.getDistance());
        Output.println("location_from: X:" + route.getFrom().getX() + " Y:" + route.getFrom().getY() + " Z:" + route.getFrom().getZ() + " name:" + route.getFrom().getName());
        Output.println("location_to: X:" + route.getTo().getX() + " Y:" + route.getTo().getY() + " Z:" + route.getTo().getZ() + " name:" + route.getTo().getName());
        Output.println("------------------------------------------");

    }

    public int getSize() {
        return collection.size();
    }

    public String getTypeOfCollection() {
        return collection.getClass().getSimpleName();
    }
}