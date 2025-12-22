package com.trade.settlementService;

import com.crankuptheamps.client.HAClient;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageStream;
import com.trade.amps.AmpsClientUtil;

public class SettlementService {

    public static void main(String []args) throws Exception{

        HAClient client = AmpsClientUtil.connect("SettlementService");
        System.out.println("Settlement Service connected, waiting for trades...");

        MessageStream ms = client.subscribe("trades.validated").timeout(0);
        for(Message msg: ms){

            String data = msg.getData();

            //final trade to DB
            System.out.println("Received validated trade: "+data);
        }
        client.wait(20000);
        client.close();
    }
}
