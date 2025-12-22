package com.trade.amps

import com.crankuptheamps.client.{DefaultServerChooser, HAClient}

object  AmpsClientUtil {

  def connect(clientName: String):HAClient = {
    val client = new HAClient(clientName);
    val chooser = new DefaultServerChooser();
    chooser.add("tcp://192.168.20.249:9007/amps/json");  // same for all
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