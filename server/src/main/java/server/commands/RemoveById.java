package server.commands;

import responce.Response;
import responce.SuccessResponse;
import server.utilits.CollectionManager;
import utils.Request;
public class RemoveById extends ACommand {
    private CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        String searchString = request.getArgument();

        if (searchString.isEmpty()) {
            return new SuccessResponse(getName(), "Не указан id. Попробуйте еще раз.");
        }

        try {
            Long id = Long.parseLong(searchString);
            if (!this.collectionManager.getById(id)) {
                return new SuccessResponse(getName(), "Не найдена группа с данным id: " + id);
            }
            this.collectionManager.removeRoute(id);
            return new SuccessResponse(getName(), "Успешно удалено по id: " + request);
        } catch (NumberFormatException e) {
            return new SuccessResponse(getName(), "Некорректный формат id: " + request);
        }
    }
}
