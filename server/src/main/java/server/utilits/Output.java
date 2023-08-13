package server.utilits;

import responce.Response;

public class Output {
    public static void println(Object o) {
        System.out.println(o);
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void printWelcomeMessage() {
        println("Welcome to the application!");
    }

    public static void printGoodbyeMessage() {
        println("Goodbye!");
    }

    public static void printErrorMessage(String errorMessage) {
        println("Error: " + errorMessage);
    }

    public static void printResponse(Response response) {
        println("Response: " + response);
    }
}
