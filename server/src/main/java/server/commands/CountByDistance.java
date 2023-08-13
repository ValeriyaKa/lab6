package server.commands;

import responce.Response;
import responce.SuccessResponse;
import server.utilits.*;
import collectionClasses.Route;
import utils.Request;

import java.util.function.Predicate;

public class CountByDistance extends ACommand {
    private CollectionManager collectionManager;

    public CountByDistance(CollectionManager collectionManager) {
        super("count_by_distance", "вывести количество элементов, значение поля distance которых равно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        String searchString = request.getArgument();

        try {
            Float distance = Float.parseFloat(searchString);
            Predicate<Route> distancePredicate = route -> route.getDistance().compareTo(distance) == 0;
            long count = this.collectionManager.getAllRoutes().stream()
                    .filter(distancePredicate)
                    .count();
            return new SuccessResponse(getName(), "Количество маршрутов с дистанцией " + count);
        } catch (NumberFormatException e) {
            return new SuccessResponse(getName(), "Некорректное значение аргумента");
        }
    }
}