package client.Commands;

import collectionClasses.Route;
import client.utils.CollectionManager;
import client.utils.Output;

import java.util.function.Predicate;

public class CountByDistance implements Command {
    private CollectionManager collectionManager;

    public CountByDistance(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "count_by_distance";
    }

    @Override
    public String getDescription() {
        return "вывести количество элементов, значение поля distance которых равно заданному";
    }

    @Override
    public boolean execute(String userCommand) {

        try {
            Float distance = Float.parseFloat(userCommand);
            Predicate<Route> distancePredicate = route -> route.getDistance().compareTo(distance) == 0;
            long count = collectionManager.getAllRoutes().stream()
                    .filter(distancePredicate)
                    .count();
            Output.println("Количество маршрутов с дистанцией " + count);
            return true;
        } catch (NumberFormatException e) {
            Output.println("Некорректное значение аргумента");
            return false;
        }
    }
}