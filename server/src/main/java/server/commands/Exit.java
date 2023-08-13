package server.commands;

import responce.Response;
import responce.SuccessResponse;
import server.utilits.CollectionManager;
import server.utilits.Output;
import utils.Request;

public class Exit extends ACommand {
    private CollectionManager collectionManager;

    public Exit(CollectionManager collectionManager) {
        super("exit", "завершить программу");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        collectionManager.saveCollectionToFile();
        Output.println("Завершение программы...");
        System.exit(0);

        // Возвращаем объект-индикатор успеха
        return new SuccessResponse(getName(), "Программа успешно завершена.");
    }
}
