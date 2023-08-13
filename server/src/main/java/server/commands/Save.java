package server.commands;

import responce.Response;
import responce.SuccessResponse;
import server.utilits.CSVFile;
import server.utilits.CollectionManager;
import server.utilits.Output;
import utils.Request;
public class Save extends ACommand {
    private CollectionManager collectionManager;
    private CSVFile csvFile;

    public Save(CollectionManager collectionManager, CSVFile csvFile) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.csvFile = csvFile;
    }

    @Override
    public Response execute(Request request) {
        this.collectionManager.saveCollectionToFile();
        Output.println("Успешно сохранено");
        return new SuccessResponse(getName(), "Успешно сохранено");
    }
}