package com.trade.validationService;

import com.crankuptheamps.client.HAClient;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageStream;
import com.trade.amps.AmpsClientUtil;

public class ValidationService {
    public static void main(String []args) throws Exception{
        HAClient client = AmpsClientUtil.connect("ValidationService");
        System.out.println("Validation Service connected, waiting for trades...");

        MessageStream ms = client.subscribe("trades.enriched").timeout(0);
        for(Message msg :ms){
            String trade = msg.getData();

            //Simple validation
            boolean valid = trade.contains("qty");
            if(valid){
                client.publish("trades.validated", trade);
                System.out.println("Validated trade: "+trade);
            } else {
                System.out.println("Invalid trade: "+trade);
            }
        }

        client.wait(20000);
        client.close();
    }
}

