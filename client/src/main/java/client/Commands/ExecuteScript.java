package client.Commands;

import client.utils.CommandManager;
import client.utils.Output;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteScript implements Command {
    private final CommandManager commandManager;

    public ExecuteScript(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public String getDescription() {
        return "исполнить скрипт из указанного файла";
    }

    public String getName() {
        return "execute_script";
    }

    public boolean execute(String userCommand) {
        String[] tokens = userCommand.split("\\s+");
        if (tokens.length < 2) {
            Output.println("Не указан файл скрипта.");
            return false;
        }

        String fileName = tokens[1];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            commandManager.setCurrentScript(fileName);
            while ((line = reader.readLine()) != null) {
                commandManager.process(line);
            }
            commandManager.setCurrentScript(null);
        } catch (IOException e) {
            Output.println("Ошибка при чтении файла скрипта: " + e.getMessage());
            return false;
        }

        return true;
    }
}
