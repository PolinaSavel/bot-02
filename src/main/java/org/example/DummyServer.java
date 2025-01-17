package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


import java.io.IOException;
import java.net.InetSocketAddress;

// Но относится к функциональности бота.
// Используется только для того, что бы хостинг знал, что приложение работает и не отключал его.
public class DummyServer {
    public static void  startServer() throws IOException {
        HttpServer server = HttpServer.create(
                new InetSocketAddress(getServerPort()),
                0
        );
        HttpHandler getServerStatus = DummyServer::getServerStatus;
        server.createContext("/", getServerStatus);
    }

    static void getServerStatus(HttpExchange exchange) throws IOException {
        String resp = "Bot is running!";
        exchange.sendResponseHeaders(200, resp.getBytes().length);
        exchange.getResponseBody().write(resp.getBytes());
        exchange.close();
    }
    static Integer getServerPort() {
        return Integer.parseInt(System.getenv("SERVER_PORT"));
    }
}