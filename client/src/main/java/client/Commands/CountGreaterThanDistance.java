package client.Commands;

import collectionClasses.Route;
import client.utils.CollectionManager;
import client.utils.Output;

import java.util.function.Predicate;

public class CountGreaterThanDistance implements Command {
    private CollectionManager collectionManager;

    public CountGreaterThanDistance(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "count_greater_than_distance";
    }

    @Override
    public String getDescription() {
        return "вывести количество элементов, значение поля distance которых больше заданного";
    }

    @Override
    public boolean execute(String userCommand) {
        try {
            Float d = Float.parseFloat(userCommand);
            Predicate<Route> distancePredicate = route -> route.getDistance().compareTo(d) > 0;
            long count = collectionManager.getAllRoutes().stream()
                    .filter(distancePredicate)
                    .count();

            Output.println("Количество маршрутов с дистанцией больше " + count);
            return true;
        } catch (NumberFormatException e) {
            Output.println("Некорректное значение аргумента");
            return false;
        }
    }
}