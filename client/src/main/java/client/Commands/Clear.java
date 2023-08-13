package client.Commands;

import client.Exeption.InvalidArgumentException;
import client.utils.CollectionManager;
import client.utils.Output;

public class Clear extends ACommand {
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;

    }

    @Override
    public boolean execute(String userCommand) {
        try {
            if (!userCommand.isEmpty()) throw new InvalidArgumentException();
            collectionManager.clear();
            Output.println("очищенно");
        } catch (InvalidArgumentException e) {
            Output.println("Неверное количество аргументов.");
        }
        return false;
    }

}