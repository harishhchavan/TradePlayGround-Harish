package com.trade.enrichedService;

import com.crankuptheamps.client.HAClient;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageStream;
import com.trade.amps.AmpsClientUtil;

public class EnrichmentService {

    public static void main(String[] args) throws Exception {

        HAClient client = AmpsClientUtil.connect("EnrichmentService");
        System.out.println("Enrichment Servicec connected, waiting for trade...");

        MessageStream ms = client.subscribe("trades.raw").timeout(0);
        for(Message msg: ms){
            String trade = msg.getData();
            String enrichedTrade =trade.replace("}", ", \"enriched\":true}");
            client.publish("trades.enriched", enrichedTrade);
            System.out.println("Enriched and Published trade: "+enrichedTrade);
            client.wait(20000);
            client.close();
        }
    }
}

