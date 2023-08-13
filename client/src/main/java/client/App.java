package client;

import client.utils.CSVFile;
import client.utils.CollectionManager;
import client.utils.CommandManager;
import client.utils.RouteCreator;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Создание объектов
        CollectionManager collectionManager = new CollectionManager();
        RouteCreator routeCreator = new RouteCreator();
        CommandManager commandManager = new CommandManager(collectionManager, new CSVFile(), routeCreator);
        UDPClient client = new UDPClient(commandManager);

        // Создание сканнера для чтения пользовательского ввода
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.print("> ");
            input = scanner.nextLine().trim();

            try {
                boolean success = client.sendRequest(input);

                if (!success) {
                    System.out.println("Ошибка при отправке запроса.");
                }
            } catch (IOException e) {
                System.out.println("Сервер не отвечает.");
            } catch ( ClassNotFoundException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        } while (commandManager.isRunning());
    }
}

