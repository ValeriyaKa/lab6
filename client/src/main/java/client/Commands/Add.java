package client.Commands;

import client.utils.CollectionManager;
import client.utils.RouteCreator;
import collectionClasses.Route;
import client.utils.Output;

public class Add implements Command {
    private CollectionManager collectionManager;
    private RouteCreator routeCreator;

    public Add(CollectionManager collectionManager, RouteCreator routeCreator) {
        this.collectionManager = collectionManager;
        this.routeCreator = routeCreator;
    }

    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }

    public String getName() {
        return "add";
    }

    public boolean execute(String userCommand) {
        Route newRoute = new Route(this.collectionManager.generateNextId(), this.routeCreator.nameCreator(), this.routeCreator.coordinatesCreator(), this.routeCreator.getTime().getDate(), this.routeCreator.fromCreator(), this.routeCreator.toCreator(), this.routeCreator.distanceCreator());
        this.collectionManager.addRoute(newRoute);
        Output.println("Новый элемент успешно добавлен в коллекцию.");
        return true;
    }
}
