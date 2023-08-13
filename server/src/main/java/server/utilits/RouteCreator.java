package server.utilits;

import collectionClasses.Coordinates;
import collectionClasses.Location;
import server.Exeption.ValueExeption;

import java.util.Scanner;

public class RouteCreator {
    private static Scanner scanner = new Scanner(System.in);

    public String nameCreator() {
        String name;
        try {
            Output.println("Введите имя:");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) throw new ValueExeption();
        } catch (IllegalArgumentException ve) {
            Output.println("Не коректный формат строки попробуйте ещё раз");
            name = nameCreator();
        } catch (ValueExeption e) {
            Output.println("Поле не может быть пустым, попробуйте ещё раз");
            name = nameCreator();
        }
        return name;

    }

    public Coordinates coordinatesCreator() {
        double x;
        Long y;
        Coordinates coordinates = null;
        try {
            Output.println("Введите координаты: \nКоордината x:");
            x = Double.parseDouble(scanner.nextLine().trim());
            Output.println("Координата y:");
            y = Long.parseLong(scanner.nextLine().trim());
            coordinates = new Coordinates(x, y);
            if (coordinates == null) throw new ValueExeption();
        } catch (IllegalArgumentException ve) {
            Output.println("Не коректно введенны данные, повторите ввод");
            coordinates = coordinatesCreator();
        } catch (ValueExeption e) {
            Output.println("Поле не может быть пустым, повторите ввод");
            coordinates = coordinatesCreator();
        }
        return coordinates;
    }

    public Location fromCreator() {
        double x;
        long y;
        Integer z;
        String name;
        try {
            Output.println("Введите координаты начальной Локации : \nКоордината x:");
            x = Double.parseDouble(scanner.nextLine().trim());
            Output.println("Координата y:");
            y = Long.parseLong(scanner.nextLine().trim());
            Output.println("Координата z:");
            z = Integer.parseInt(scanner.nextLine().trim());
            Output.println("Введите название:");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) throw new ValueExeption();
            Location location = new Location(x, y, z, name);
            return location;
        } catch (NumberFormatException ex) {
            Output.println("Ошибка формата числа. Попробуйте еще раз.");
            return fromCreator();
        } catch (ValueExeption ex) {
            Output.println("Название не может быть пустым. Попробуйте еще раз.");
            return fromCreator();
        }
    }

    public Location toCreator() {
        double x_to;
        long y_to;
        Integer z_to;
        String name;
        try {
            Output.println("Введите координаты конечной Локации: \nКоордината x:");
            x_to = Double.parseDouble(scanner.nextLine().trim());
            Output.println("Координата y:");
            y_to = Long.parseLong(scanner.nextLine().trim());
            Output.println("Координата z:");
            z_to = Integer.parseInt(scanner.nextLine().trim());
            Output.println("Введите название:");
            name = scanner.nextLine();
            if (name.trim().isEmpty() || z_to == null) throw new ValueExeption();
            Location location = new Location(x_to, y_to, z_to, name);
            return location;
        } catch (NumberFormatException ex) {
            Output.println("Ошибка формата числа. Попробуйте еще раз.");
            return fromCreator();
        } catch (ValueExeption ex) {
            Output.println("Название не может быть пустым. Попробуйте еще раз.");
            return fromCreator();
        } catch (IllegalArgumentException ex) {
            Output.println("Не коректно введенны данные, повторите ввод");
            return fromCreator();
        }
    }

    public DataTime getTime() {
        DataTime time = new DataTime();
        Output.println(time.getDate());
        return time;
    }

    public Float distanceCreator() {
        Float distance;
        try {
            Output.print("Ведити растояние между пунктами:");
            distance = Float.parseFloat(scanner.nextLine());
            if (distance < 1) throw new ValueExeption();
        } catch (IllegalArgumentException ve) {
            Output.println("Дистанция не может быть меньше 1");
            distance = distanceCreator();
        } catch (ValueExeption e) {
            Output.println("Поле не может быть пустым, попробуйте ещё раз");
            distance = distanceCreator();
        }
        return distance;
    }


}