package server.commands;

import responce.ErrorResponse;
import responce.Response;
import responce.SuccessResponse;
import server.utilits.CollectionManager;
import utils.Request;

public class Info extends ACommand {
    private CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            String message = "Размер коллекции: " + collectionManager.getSize() +
                    ", Тип коллекции: " + collectionManager.getTypeOfCollection();
            return new SuccessResponse("info", message);
        } catch (NullPointerException e) {
            return new ErrorResponse("info", "Ошибка: " + e.getMessage());
        }
    }
}
