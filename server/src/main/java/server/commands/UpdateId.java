package server.commands;

import collectionClasses.Route;
import responce.Response;
import responce.SuccessResponse;
import server.utilits.CollectionManager;
import server.utilits.Output;
import server.utilits.RouteCreator;
import utils.Request;

import java.util.NoSuchElementException;

public class UpdateId extends ACommand {
    private CollectionManager collectionManager;
    private RouteCreator routeCreator;

    public UpdateId(CollectionManager collectionManager, RouteCreator routeCreator) {
        super("update_id", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.routeCreator = routeCreator;
    }

    @Override
    public Response execute(Request request) {
        try {
            Route newRoute = request.getRoute();
            long id = Long.parseLong(request.getArgument().trim());
            this.collectionManager.getCollection().stream()
                    .filter(route -> route.getId() == id)
                    .findFirst()
                    .ifPresent(route -> {
                        this.collectionManager.removeRoute(route.getId());
                        this.collectionManager.addRoute(newRoute);
                        Output.println("Элемент с id " + id + " успешно обновлен.");
                    });
        } catch (NumberFormatException e) {
            Output.println("Некорректный формат аргумента. Ожидалось целое число.");
        } catch (NoSuchElementException e) {
            Output.println("Элемент с таким id не найден.");
        }
        return new SuccessResponse(getName(), "Обновление элемента успешно выполнено");
    }
}