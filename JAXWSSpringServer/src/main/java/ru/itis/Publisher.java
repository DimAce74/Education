package ru.itis;

import ru.itis.services.AutoServiceImpl;
import ru.itis.services.UserServiceImpl;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8081/ws/autos", new AutoServiceImpl());
        Endpoint.publish("http://localhost:8081/ws/users", new UserServiceImpl());
    }
}
