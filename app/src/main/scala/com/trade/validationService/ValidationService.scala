package com.trade.validationService

import com.crankuptheamps.client.{HAClient, Message}
import com.trade.amps.AmpsClientUtil
import com.trade.common.Trade
import play.api.libs.json.Json

object ValidationService extends App {

  val client: HAClient = AmpsClientUtil.connect("ValidationService")
  println("Validation Service connected")

  val ms = client.subscribe("trades.enriched").timeout(0)

  while (true){
    val msg = ms.next();
    val trade = Json.parse(msg.getData).as[Trade]

    if (trade.qty > 0) {
      val json = Json.stringify(Json.toJson(trade))
      client.publish("trades.validated", json)
      println(s"Validated trade: $json")
    } else {
      println(s"Invalid trade: $trade")
    }
  }
}
