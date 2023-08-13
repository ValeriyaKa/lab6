package client.Commands;

import client.utils.CollectionManager;
import client.utils.RouteCreator;
import collectionClasses.Route;

public class AddIfMin implements Command {
    private CollectionManager collectionManager;
    private RouteCreator routeCreator;

    public AddIfMin(CollectionManager collectionManager, RouteCreator routeCreator) {
        this.collectionManager = collectionManager;
        this.routeCreator = routeCreator;
    }

    public String getDescription() {
        return "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }

    public String getName() {
        return "add_if_min";
    }

    public boolean execute(String userCommand) {
        Route newRoute = new Route(this.collectionManager.generateNextId(), this.routeCreator.nameCreator(), this.routeCreator.coordinatesCreator(), this.routeCreator.getTime().getDate(), this.routeCreator.fromCreator(), this.routeCreator.toCreator(), this.routeCreator.distanceCreator());

//        if (this.collectionManager.getCollection().isEmpty() || newRoute.compareTo(this.collectionManager.getMinRoute()) < 0) {
//            this.collectionManager.addRoute(newRoute);
//            Output.println("����� ������� ������� �������� � ���������.");
//        } else {
//            Output.println("����� ������� �� �������� ����������� �� �������� � �� ����� �������� � ���������.");
//        }

        return true;
    }
}
