package server.commands;

import responce.Response;
import responce.SuccessResponse;
import server.utilits.CollectionManager;
import collectionClasses.Route;
import server.utilits.Output;
import utils.Request;
import java.util.function.Predicate;

public class CountGreaterThanDistance extends ACommand {
    private CollectionManager collectionManager;

    public CountGreaterThanDistance(CollectionManager collectionManager) {
        super("count_greater_than_distance", "вывести количество элементов, значение поля distance которых больше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            Float d = Float.parseFloat(request.getArgument());
            Predicate<Route> distancePredicate = route -> route.getDistance().compareTo(d) > 0;
            long count = this.collectionManager.getAllRoutes().stream()
                    .filter(distancePredicate)
                    .count();

            Output.println("Количество маршрутов с дистанцией больше " + count);
        } catch (NumberFormatException e) {
            Output.println("Некорректное значение аргумента");
        }
        return new SuccessResponse(getName(), "нет маршрутов с дистанцией больше чем заданная ");
    }
}
