package server.commands;

import collectionClasses.Route;
import responce.Response;
import responce.SuccessResponse;
import server.Exeption.WrongAmountOfElements;
import server.utilits.CollectionManager;
import server.utilits.Output;
import server.utilits.RouteCreator;
import utils.Request;

import java.util.Collections;

public class AddIfMin extends ACommand {
    private CollectionManager collectionManager;
    private RouteCreator routeCreator;

    public AddIfMin(CollectionManager collectionManager, RouteCreator routeCreator) {
        super("add_if_min", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.routeCreator = routeCreator;
    }

    @Override
    public Response execute(Request request) {
        try {
            if (request.getRoute() == null || request.getArgument().isEmpty()) {
                throw new WrongAmountOfElements();
            }

            Route newRoute = request.getRoute();
            Route minRoute = Collections.min(this.collectionManager.getCollection());

            if (newRoute.compareTo(minRoute) < 0) {
                this.collectionManager.addRoute(newRoute);
                return new SuccessResponse(getName(), "Элемент успешно добавлен в коллекцию.");
            } else {
                return new SuccessResponse(getName(), "Значение элемента не меньше, чем у наименьшего элемента коллекции.");
            }
        } catch (WrongAmountOfElements exception) {
            Output.println("Неправильное количество аргументов!");
            return new SuccessResponse(getName(), "Ошибка: неправильное количество аргументов");
        } catch (NumberFormatException exception) {
            return new SuccessResponse(getName(), "Неправильный формат числа.");
        }
    }
}
