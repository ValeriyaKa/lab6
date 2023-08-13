package server.utilits;

import responce.NoSuchCommandResponse;
import responce.Response;
import server.commands.*;
import utils.Request;

import java.util.HashMap;

public class CommandManager {
    private final CollectionManager collectionManager;
    private final RouteCreator routeCreator;
    private final CSVFile csvFile;
    private String currentScript = null;
    private final HashMap<String, Command> commandMap;

    public CommandManager(CollectionManager collectionManager, CSVFile csvFile, RouteCreator routeCreator) {
        this.collectionManager = collectionManager;
        this.routeCreator = routeCreator;
        this.csvFile = csvFile;

        commandMap = new HashMap<>();
        commandMap.put("help", new Help(this));
        commandMap.put("exit", new Exit(collectionManager));
        commandMap.put("info", new Info(collectionManager));
        commandMap.put("show", new Show(collectionManager));
        commandMap.put("add", new Add(collectionManager));
        commandMap.put("update_id", new UpdateId(collectionManager, routeCreator));
        commandMap.put("remove_by_id", new RemoveById(collectionManager));
        commandMap.put("clear", new Clear(collectionManager));
        commandMap.put("execute_script", new ExecuteScript(collectionManager, this));
        commandMap.put("remove_greater", new RemoveGreater(collectionManager, routeCreator));
        commandMap.put("remove_lower", new RemoveLower(collectionManager, routeCreator));
        commandMap.put("count_by_distance", new CountByDistance(collectionManager));
        commandMap.put("count_greater_than_distance", new CountGreaterThanDistance(collectionManager));
        commandMap.put("add_if_min", new AddIfMin(collectionManager, routeCreator));
        commandMap.put("filter_starts_with_name", new FilterStartsWithName(collectionManager));
    }

    public Response handle(Request request) {
        String commandName = request.getName();
        Command command = commandMap.get(commandName.toLowerCase());

        if (command == null) {
            NoSuchCommandResponse response = new NoSuchCommandResponse(commandName);
            Output.println(response.getMessage());
            return response;
        } else {
            Response response = command.execute(request);
            Output.println(response.getMessage());
            return response;
        }
    }

    public String getCurrentScript() {
        return currentScript;
    }

    public void setCurrentScript(String currentScript) {
        this.currentScript = currentScript;
    }

    public void process(String line) {
        String[] tokens = line.trim().split("\\s+", 2);
        String commandName = tokens[0];
        String argument = tokens.length > 1 ? tokens[1] : null;
        Request request = new Request(commandName, argument, null);
        processCommand(request);
    }

    public void processCommand(Request request) {
        handle(request);
    }

    public void helpCommand(String commandName) {
        if (commandName.isEmpty()) {
            Output.println("Список команд:");
            commandMap.keySet().forEach(Output::println);
        } else {
            Command command = commandMap.get(commandName);
            if (command != null) {
                Output.println(command.getDescription());
            } else {
                Output.println("Команда '" + commandName + "' не найдена.");
            }
        }
    }

    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName.toLowerCase());
    }

    public boolean isRunning() {
        // Вернуть состояние работы сервера
        return false;
    }
}
