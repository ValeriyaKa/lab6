package server;

import server.commands.Command;
import server.utilits.CollectionManager;
import utils.Request;
import responce.Response;

import java.io.*;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServer {
    private DatagramSocket serverSocket;
    private int port = 8081;
    private byte[] receiveBuffer = new byte[2048];
    private boolean isRunning;
    private Logger logger = Logger.getLogger(UDPServer.class.getName());

    public void start(HashMap<String, Command> map, CollectionManager collectionManager) {
        try {
            DatagramChannel serverChannel = DatagramChannel.open();
            serverChannel.socket().bind(new InetSocketAddress(port));

            serverSocket = serverChannel.socket();
            isRunning = true;
            logger.info("UDP Server started on port " + port);
            while (isRunning) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);
                processRequest(receivePacket, map, collectionManager);
            }
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "Error occurred while running UDP server", ioe);
        } catch (ClassNotFoundException ioe) {
            logger.log(Level.SEVERE, "Error in the received request", ioe);
        } finally {
            closeServerSocket();
        }
    }

    // Метод для остановки сервера
    public void stop() {
        isRunning = false;
        closeServerSocket();
    }

    private void processRequest(DatagramPacket receivePacket, HashMap<String, Command> map,
                                CollectionManager collectionManager) throws IOException, ClassNotFoundException {
        byte[] requestData = receivePacket.getData();
        ByteArrayInputStream byteStream = new ByteArrayInputStream(requestData);
        try (ObjectInput objectInput = new ObjectInputStream(byteStream)) {
            Request request = (Request) objectInput.readObject();
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            if (map.containsKey(request.getName())) {
                Command command = map.get(request.getName());
                Response response = command.execute(request);
                sendResponse(response, clientAddress, clientPort);

                logger.info("Запрос от клиента: " + request);
                logger.info("Ответ, отправленный клиенту: " + response);
            } else {
                logger.warning("Unknown command received");
            }
        }
    }

    private void sendResponse(Response response, InetAddress clientAddress, int clientPort) throws IOException {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             ObjectOutputStream objectOut = new ObjectOutputStream(byteOut)) {
            objectOut.writeObject(response);
            objectOut.flush();

            byte[] responseData = byteOut.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }
    }

    private void closeServerSocket() {
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
    }
}
