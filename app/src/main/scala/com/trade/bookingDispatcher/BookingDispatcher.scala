package com.trade.bookingDispatcher

import com.crankuptheamps.client.HAClient
import com.trade.amps.AmpsClientUtil
import com.trade.common.Trade
import play.api.libs.json.Json

object BookingDispatcher extends App {

  val client: HAClient = AmpsClientUtil.connect("BookingDispatcher")
  println("Booking Dispatcher connected")

  val trade = Trade(
    id = 1,
    symbol = "AAPL",
    qty = 100
  )

  val jsonTrade = Json.stringify(Json.toJson(trade))
  client.publish("trades.raw", jsonTrade)
  println(s"Published trade: $jsonTrade")

  client.close()
}

