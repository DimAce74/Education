package ru.itis;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.itis.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }
}
