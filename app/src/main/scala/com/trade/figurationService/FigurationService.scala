//package com.trade.figurationService
//
//import com.crankuptheamps.client.{HAClient, Message}
//import com.trade.amps.AmpsClientUtil
//import com.trade.common.Trade
//import play.api.libs.json.Json
//
//object FigurationService extends App {
//
//  val client: HAClient = AmpsClientUtil.connect("FigurationService")
//  println("Figuration Service connected")
//
//  val ms = client.subscribe("trades.validated").timeout(0)
//
//  while (true) {
//    val msg = ms.next()
//    val trade = Json.parse(msg.getData).as[Trade]
//
//    val pricedTrade = trade.copy(price = Some(150.0))
//    val json = Json.stringify(Json.toJson(pricedTrade))
//
//    client.publish("trades.figured", json)
//    println(s"Figured trade: $json")
//  }
//}
//
