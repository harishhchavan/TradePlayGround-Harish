package com.trade.settlementService;

import com.crankuptheamps.client.HAClient;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageStream;
import com.trade.amps.AmpsClientUtil;

public class SettlementService {
    public static void main(String[] args) throws Exception {
        HAClient client = AmpsClientUtil.connect("SettlementService");
        System.out.println("Settlement Service connected, waiting for trades...");

        MessageStream ms = client.subscribe("trades.figured").timeout(0);

        int count = 0; // debug limit
        for (Message msg : ms) {
            String data = msg.getData();
            System.out.println("Received final trade: " + data);

            if (++count >= 5) break; // remove in production
        }

        client.close();
    }
}
