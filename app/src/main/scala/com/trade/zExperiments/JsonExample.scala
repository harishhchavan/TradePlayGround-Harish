package com.trade.zExperiments

import play.api.libs.json._

// Define a case class
case class Trade(id: Int, symbol: String, quantity: Double, price: Double)

object Trade {
  // Create implicit formatter
  implicit val tradeFormat: OFormat[Trade] = Json.format[Trade]
}

object JsonExample extends App {

  // -------------------------
  // 1️⃣ Serialize Scala object → JSON
  // -------------------------
  val trade1 = Trade(1, "AAPL", 100, 150.5)
  val tradeJson: JsValue = Json.toJson(trade1)
  println("Serialized JSON:")
  println(Json.prettyPrint(tradeJson))
  println()

  // -------------------------
  // 2️⃣ Deserialize JSON → Scala object
  // -------------------------
  val jsonString = """{"id":2,"symbol":"GOOG","quantity":50,"price":2800.0}"""
  val jsValue: JsValue = Json.parse(jsonString)
  val trade2: Trade = jsValue.as[Trade]
  println("Deserialized Scala object:")
  println(trade2)
  println()

  // ------------------------------------------
  // 3️⃣ Access fields in JSON
  // ------------------------------------------

  println("Access fields in JSON:")
  val id = (tradeJson \ "id").as[Int]
  val symbol = (tradeJson \ "symbol").as[String]
  val quantity = (tradeJson \ "quantity").as[Double]
  println(s"id=$id, symbol=$symbol, quantity=$quantity")
  println()

  // -------------------------
  // 4️⃣ Create JSON object manually
  // -------------------------
  val manualJson: JsObject = Json.obj(
    "id" -> 3,
    "symbol" -> "MSFT",
    "quantity" -> 200,
    "price" -> 300.0
  )
  println("Manually created JSON object:")
  println(Json.prettyPrint(manualJson))
  println()

  // -------------------------
  // 5️⃣ Convert JSON object → case class
  // -------------------------
  val trade3 = manualJson.as[Trade]
  println("Converted back to Scala object:")
  println(trade3)
}

