package server.commands;

import collectionClasses.Route;
import responce.Response;
import responce.SuccessResponse;
import server.utilits.CollectionManager;
import server.utilits.RouteCreator;
import utils.Request;

import java.util.stream.Collectors;

public class RemoveLower extends ACommand {
    private CollectionManager collectionManager;
    private RouteCreator routeCreator;


    public RemoveLower(CollectionManager collectionManager, RouteCreator routeCreator) {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.collectionManager = collectionManager;
        this.routeCreator = routeCreator;
    }

    @Override
    public Response execute(Request request) {
        Route newRoute = request.getRoute();

        this.collectionManager.getCollection().stream()
                .filter(route -> route.compareTo(newRoute) < 0)
                .map(Route::getId)
                .collect(Collectors.toList())
                .forEach(this.collectionManager::removeRoute);

        return new SuccessResponse(getName(), "Элементы успешно удалены");
    }
}
