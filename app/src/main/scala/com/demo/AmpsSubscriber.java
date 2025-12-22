package com.demo;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageStream;

public class AmpsSubscriber {

    public static void main(String[] args) throws Exception {

        Client client = new Client("ChatSubscriber");

        client.connect("tcp://192.168.20.52:9007/amps");
        System.out.println("Subscriber connected");

        // Subscribe → MessageStream
        MessageStream stream = client.subscribe("chat");
        System.out.println("Subscribed to chat");

        // Blocking receive loop
        while (true) {
            Message msg = stream.next();   // ✅ THIS IS THE KEY LINE
            System.out.println("Received: " + msg.getData());
        }
    }
}
