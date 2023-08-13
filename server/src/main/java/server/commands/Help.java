package server.commands;

import responce.Response;
import responce.SuccessResponse;
import server.utilits.CommandManager;
import utils.Request;
import server.Exeption.WrongAmountOfElements;

public class Help extends ACommand {
    private CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            if (!request.getArgument().isEmpty()) {
                throw new WrongAmountOfElements();
            }
            commandManager.helpCommand(request.getArgument());
            return new SuccessResponse(getName(), "Справка по командам успешно выведена.");
        } catch (WrongAmountOfElements exception) {
            return new SuccessResponse("использование: ", getName());
        }
    }
}
