package com.demo;

import com.crankuptheamps.client.Client;

public class AmpsPublisher {

    public static void main(String[] args) throws Exception {

        Client client = new Client("ChatPublisher");

        client.connect("tcp://192.168.20.52:9007/amps");
        System.out.println("Publisher connected");

        client.publish("chat", "{\"text\":\"Hello AMPS from IntelliJ\"}");

        System.out.println("Message published");
        client.close();
    }
}
