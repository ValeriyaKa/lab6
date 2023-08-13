package server.utilits;

import collectionClasses.Coordinates;
import collectionClasses.Location;
import collectionClasses.Route;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CSVFile {


    private Set<Route> collection;
    String test;

    public CSVFile() {
        collection = new LinkedHashSet<>();
        test = System.getenv("ROUTE_FILE");
    }

    private boolean routeExists(Long id) {
        for (Route rrr : collection) {
            if (rrr.getId() == id.longValue()) {
                return true;
            }
        }
        return false;
    }

    public void addRoute(Route route) {
        collection.add(route);
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public void saveFile() throws Exception {
        try (CSVWriter writer = new CSVWriter(new FileWriter(test))) {
            String[] header = {"id", "name", "coordinates_x", "coordinates_y", "creation_date", "name_from", "coordinates_x_from", "coordinates_y_from", "coordinates_z_from", "name_to", "coordinates_x_to", "coordinates_y_to", "coordinates_z_to", "distance"};
            writer.writeNext(header);

            List<String[]> data = new ArrayList<>();
            for (Route route : collection) {
                String[] line = {
                        String.valueOf(route.getId()),
                        route.getName(),
                        String.valueOf(route.getCoordinates().getX()),
                        String.valueOf(route.getCoordinates().getY()),
                        dateFormat.format(route.getCreationDate()),
                        route.getFrom().getName(),
                        String.valueOf(route.getFrom().getX()),
                        String.valueOf(route.getFrom().getY()),
                        String.valueOf(route.getFrom().getZ()),
                        route.getTo().getName(),
                        String.valueOf(route.getTo().getX()),
                        String.valueOf(route.getTo().getY()),
                        String.valueOf(route.getTo().getZ()),
                        String.valueOf(route.getDistance())
                };
                data.add(line);
            }
            writer.writeAll(data);
        } catch (IOException e) {
            throw new Exception("Failed to write file", e);
        }
    }

    public boolean loadFile() {
        try {
            CSVReader reader = new CSVReader(new FileReader(test));

            // Считываем первую строку (заголовки столбцов)
            String[] header = reader.readNext();
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < header.length; i++) {
                headerMap.put(header[i], i);
            }
            // Считываем остальные строки
            String[] nextLine;
            boolean duplicateIdsFound = false;
            while ((nextLine = reader.readNext()) != null) {
                // Создаем объект Route
                if (routeExists(Long.parseLong(nextLine[headerMap.get("id")]))) {
                    Output.println("Данные в файле неверны, существуют две группы с одинаковыми id!");
                    duplicateIdsFound = true;
                    break;
                }

                Date parsedDate = dateFormat.parse(nextLine[headerMap.get("creation_date")]);
                // Добавляем объект в коллекцию
                Route route = new Route(Long.parseLong(nextLine[headerMap.get("id")]), (nextLine[headerMap.get("name")]),
                        new Coordinates(Double.parseDouble(nextLine[headerMap.get("coordinates_x")]), Long.parseLong(nextLine[headerMap.get("coordinates_y")])),
                        parsedDate,
                        new Location(Double.parseDouble(nextLine[headerMap.get("coordinates_x_to")]), Long.parseLong(nextLine[headerMap.get("coordinates_y_to")]),
                                Integer.parseInt(nextLine[headerMap.get("coordinates_z_to")]), nextLine[headerMap.get("name_to")]),
                        new Location(Double.parseDouble(nextLine[headerMap.get("coordinates_x_from")]), Long.parseLong(nextLine[headerMap.get("coordinates_y_from")]), Integer.parseInt(nextLine[headerMap.get("coordinates_z_from")]), nextLine[headerMap.get("name_from")]),
                        Float.parseFloat(nextLine[headerMap.get("distance")]));
                collection.add(route);
            }

            reader.close();
            if (!duplicateIdsFound) {
                Output.println("Файл успешно загружен");
            }
            return true;


        } catch (IOException e) {
            Output.println("Ошибка чтения файла!" + e.getMessage());
        } catch (NumberFormatException e) {
            Output.println("Данные в файле неверны!" + System.lineSeparator() + e.getMessage());
        } catch (ParseException e) {
            Output.println("Ошибка парсинга даты!\n" + e.getMessage());
        }
        return false;

    }


    public String getFile() {
        return test;
    }

    public Set<Route> getCollection() {
        return collection;
    }

}