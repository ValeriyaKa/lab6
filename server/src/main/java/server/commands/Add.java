package server.commands;

import collectionClasses.Route;
import responce.Response;
import responce.SuccessResponse;
import server.Exeption.WrongAmountOfElements;
import server.utilits.CollectionManager;
import server.utilits.Output;
import utils.Request;

public class Add extends ACommand {
    private CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            Route newRoute = request.getRoute();

            if (newRoute == null) {
                throw new WrongAmountOfElements();
            }

            Output.println("* Создание нового маршрута:");

            long id = collectionManager.generateNextId();
            newRoute.setId(id);

            this.collectionManager.addRoute(newRoute);
            return new SuccessResponse(getName(), "Маршрут успешно добавлен");
        } catch (WrongAmountOfElements exception) {
            Output.println("Неправильное количество элементов!");
            return new SuccessResponse(getName(), "Ошибка: неправильное количество элементов");
        }
    }
}
