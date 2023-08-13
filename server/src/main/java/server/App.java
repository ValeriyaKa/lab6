package server;

import server.utilits.CSVFile;
import server.utilits.CommandManager;
import server.utilits.CollectionManager;
import server.utilits.RouteCreator;

public class App {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager(collectionManager, new CSVFile(), new RouteCreator());

        UDPServer server = new UDPServer();
        server.start(commandManager.getCommandMap(), collectionManager);
        collectionManager.loadCollection();
    }
}
