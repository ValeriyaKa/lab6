package server.commands;

import collectionClasses.Route;
import responce.Response;
import responce.SuccessResponse;
import server.utilits.CollectionManager;
import utils.Request;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterStartsWithName extends ACommand {
    private CollectionManager collectionManager;

    public FilterStartsWithName(CollectionManager collectionManager) {
        super("filter_starts_with_name", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        String searchString = request.getArgument();

        Predicate<Route> startsWithNamePredicate = route -> route.getName().startsWith(searchString);

        List<Route> matchingRoutes = this.collectionManager.getCollection().stream()
                .filter(startsWithNamePredicate)
                .peek(this.collectionManager::printRoute)
                .collect(Collectors.toList());

        return new SuccessResponse(getName(), "Маршруты, значение поля name которых начинается с заданной подстроки: " + matchingRoutes.size());

    }
}