package com.trade.amps;

import com.crankuptheamps.client.*;

public class AmpsClientUtil {

    public static HAClient connect(String clientName) throws Exception {
        HAClient client = new HAClient(clientName);
        DefaultServerChooser chooser = new DefaultServerChooser();
        chooser.add("tcp://192.168.20.52:9007/amps/json");  // same for all
        client.setServerChooser(chooser);
        client.connectAndLogon();
        return client;
    }
}

/*
        #all services will call...
        .
        HAClient client = AmpsClientUtil.connect("ServiceName");
        .
 */