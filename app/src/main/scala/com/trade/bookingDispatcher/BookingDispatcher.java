package com.trade.bookingDispatcher;

import com.crankuptheamps.client.HAClient;
import com.trade.amps.AmpsClientUtil;

public class BookingDispatcher {
    public static void main(String[] args) throws Exception {
        HAClient client = AmpsClientUtil.connect("BookingDispatcher");
        System.out.println("Booking Dispatcher connected, publishing trades...");

        //Simulate DB poll
        String trade = "{\"id\":1,\"symbol\":\"AAPL\", \"qty\":100}" ;
        client.publish("trades.raw", trade);
        System.out.println("Published trade to trades.raw");
    }
}

