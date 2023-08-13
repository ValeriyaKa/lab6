package responce;

public class NoSuchCommandResponse extends Response {
    private final String commandName;

    public NoSuchCommandResponse(String commandName) {
        super(commandName, "Error: Command '" + commandName + "' not found.");
        this.commandName = commandName;
    }
}
