package com.trade.enrichedService;

import com.crankuptheamps.client.HAClient;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageStream;
import com.trade.amps.AmpsClientUtil;

public class EnrichmentService {
    public static void main(String[] args) throws Exception {
        HAClient client = AmpsClientUtil.connect("EnrichmentService");
        System.out.println("Enrichment Service connected, waiting for trades...");

        MessageStream ms = client.subscribe("trades.raw").timeout(0);

        int count = 0; // debug limit, remove/comment in production
        for (Message msg : ms) {
            String trade = msg.getData();
            String enrichedTrade = trade.replace("}", ", \"enriched\":true}");
            client.publish("trades.enriched", enrichedTrade);
            System.out.println("Enriched and Published trade: " + enrichedTrade);

            if (++count >= 5) break; // remove in production
        }

        client.close();
    }
}
