package server.commands;

import collectionClasses.Route;
import responce.Response;
import responce.SuccessResponse;
import server.utilits.CollectionManager;
import server.utilits.RouteCreator;
import utils.Request;
public class RemoveGreater extends ACommand {
    private CollectionManager collectionManager;
    private RouteCreator routeCreator;

    public RemoveGreater(CollectionManager collectionManager, RouteCreator routeCreator) {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.routeCreator = routeCreator;
    }

    @Override
    public Response execute(Request request) {
        try {
            Route newRoute = request.getRoute();
            for (Route route : this.collectionManager.getCollection()) {
                if (route.compareTo(newRoute) == -1) {
                    this.collectionManager.removeRoute(route.getId());
                }
            }
            return new SuccessResponse(getName(), "Элементы превышающие заданный успешно удалены");
        } catch (Exception e) {
            return new SuccessResponse(getName(), "Ошибка при выполнении команды: " + e.getMessage());
        }
    }
}
