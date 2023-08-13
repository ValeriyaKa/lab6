package server.commands;

import utils.Request;
import responce.Response;

public interface Command {
    String getDescription();

    String getName();

    Response execute(Request request);
}
