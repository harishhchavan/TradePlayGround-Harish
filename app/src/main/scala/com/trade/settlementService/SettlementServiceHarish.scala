package com.trade.settlementService

import com.crankuptheamps.client.{HAClient, Message}
import com.trade.amps.AmpsClientUtil
import com.trade.common.Trade
import play.api.libs.json.Json

object SettlementServiceHarish extends App {

  val client: HAClient = AmpsClientUtil.connect("SettlementService")
  println("Settlement Service connected")

  val ms = client.subscribe("trades.figured").timeout(0)

  while (true) {
    val msg = ms.next()
    val trade = Json.parse(msg.getData).as[Trade]

    println(s"Final trade received: $trade")

    // Next step: save trade to DB
  }
}
