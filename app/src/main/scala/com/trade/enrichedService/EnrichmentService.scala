//package com.trade.enrichedService
//
//import com.crankuptheamps.client.{HAClient, Message}
//import com.trade.amps.AmpsClientUtil
//import com.trade.common.Trade
//import play.api.libs.json.Json
//
//object EnrichmentService extends App {
//
//  val client: HAClient = AmpsClientUtil.connect("EnrichmentService")
//  println("Enrichment Service connected")
//
//  val ms = client.subscribe("trades.raw").timeout(0)
//
//  while(true) {
//    val msg = ms.next()
//    val trade = Json.parse(msg.getData).as[Trade]
//
//    val enrichedTrade = trade.copy(enriched = true)
//    val json = Json.stringify(Json.toJson(enrichedTrade))
//
//    client.publish("trades.enriched", json)
//    println(s"Enriched trade: $json")
//  }
//}
