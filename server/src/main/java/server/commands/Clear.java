package server.commands;

import server.Exeption.WrongAmountOfElements;
import responce.Response;
import responce.SuccessResponse;
import server.utilits.CollectionManager;
import utils.Request;
import server.utilits.Output;

public class Clear extends ACommand {

    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        String searchString = request.getArgument();

        try {
            if (!searchString.isEmpty()) throw new WrongAmountOfElements();
            this.collectionManager.clear();
        } catch (WrongAmountOfElements e) {
            Output.println("Неверное количество аргументов.");
        }
        return new SuccessResponse(getName(), "Коллекция успешно отчищена");
    }

}