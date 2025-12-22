package com.trade.figurationService;

import com.crankuptheamps.client.HAClient;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageStream;
import com.trade.amps.AmpsClientUtil;

public class FigurationService {
    public static void main(String[] args) throws Exception {
        HAClient client = AmpsClientUtil.connect("FigurationService");
        System.out.println("Figuration Service connected, waiting for trades...");

        MessageStream ms = client.subscribe("trades.validated").timeout(0);

        int count = 0; // debug limit
        for (Message msg : ms) {
            String trade = msg.getData();
            String pricedTrade = trade.replace("}", ",\"price\":150}");
            client.publish("trades.figured", pricedTrade); // new topic to avoid loops
            System.out.println("Figured trade: " + pricedTrade);

            if (++count >= 5) break; // remove in production
        }

        client.close();
    }
}
