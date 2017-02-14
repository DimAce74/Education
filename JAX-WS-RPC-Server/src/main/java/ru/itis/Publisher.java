package ru.itis;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8081/ws/hello", new HelloWorldImpl());
    }
}
