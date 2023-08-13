package server.commands;

import collectionClasses.Route;
import responce.Response;
import responce.SuccessResponse;
import server.utilits.CollectionManager;
import utils.Request;
public class Show extends ACommand {
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        StringBuilder sb = new StringBuilder();
        int maxElements = 100; // Максимальное количество элементов для вывода
        int count = 0;

        for (Route route : collectionManager.getCollection()) {
            sb.append("id: ").append(route.getId()).append("\n");
            sb.append("name: ").append(route.getName()).append("\n");
            sb.append("coordinates: X:").append(route.getCoordinates().getX()).append(" Y:").append(route.getCoordinates().getY()).append("\n");
            sb.append("creation_date: ").append(route.getCreationDate()).append("\n");
            sb.append("distance: ").append(route.getDistance()).append("\n");
            sb.append("location_from: X:").append(route.getFrom().getX()).append(" Y:").append(route.getFrom().getY()).append(" Z:").append(route.getFrom().getZ()).append(" name:").append(route.getFrom().getName()).append("\n");
            sb.append("location_to: X:").append(route.getTo().getX()).append(" Y:").append(route.getTo().getY()).append(" Z:").append(route.getTo().getZ()).append(" name:").append(route.getTo().getName()).append("\n");
            sb.append("------------------------------------------\n");

            count++;
            if (count >= maxElements) {
                sb.append("...").append("\n");
                break;
            }
        }

        if (count >= maxElements) {
            sb.append("Максимальное количество элементов достигнуто. Выведены первые ").append(maxElements).append(" элементов.").append("\n");
        }

        return new SuccessResponse(getName(), sb.toString());
    }

}
