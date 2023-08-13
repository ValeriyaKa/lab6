package client.Commands;

public interface Command {
    String getDescription();

    String getName();

    public boolean execute(String userCommand);
}