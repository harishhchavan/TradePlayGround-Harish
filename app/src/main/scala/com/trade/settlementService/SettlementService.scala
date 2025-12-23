package com.trade.settlementService

import com.crankuptheamps.client.{Client, Command, Message, MessageHandler}
import com.trade.SQL.SQL
import com.trade.common.Trade
import com.trade.config.DbConfig
import play.api.libs.json._
import java.sql.{Connection, DriverManager}
import java.time.Instant


object SettlementService {

  def main(args: Array[String]): Unit = {

    val config = DbConfig.dbConfig
    Class.forName(config.dbDriver)

    val figurationAmpsServer = "tcp://192.168.20.169:9007/amps/json"

    println("=" * 60)
    println("HARISH - SETTLEMENT SERVICE")
    println("=" * 60)

    val client = new Client("SettlementService")

    try {
      client.connect(figurationAmpsServer)
      client.logon()
      println("Connected to Figuration Server!")


      val handler = new MessageHandler() {
        override def invoke(msg: Message): Unit = {

          val rawJson = msg.getData
          //Can be unwanted data!

          val cleanJson = {
            if(rawJson.contains("}"))
              rawJson.substring(0, rawJson.indexOf("}") + 1)
            else
              rawJson
          }

          println("\nRAW MESSAGE:")
          println(cleanJson)

          // 🔹 Convert JSON string → Trade
          Json.parse(cleanJson).validate[Trade] match {

            case JsSuccess(trade, _) =>
              val settled = settleTrade(trade)
                println("Trade details filled successfully.")

              val conn: Connection = DriverManager.getConnection(config.dbUrl, config.dbUser, config.dbPassword)

              try {
                conn.setAutoCommit(false)
                updateSettlementInDB(conn, settled)
                println(s"Trade ${settled.trade_id} settled successfully")
                conn.commit()

              } catch {
                case e: Exception =>
                  conn.rollback()
                  println(s"Failed to settle trade ${settled.trade_id}")
                  e.printStackTrace()

              } finally {
                conn.close()
              }


            case JsError(errors) =>
              println("Invalid JSON received!!!")
              println(errors)
          }
        }
      }

      val cmd = new Command("subscribe").setTopic("figurated")
      client.executeAsync(cmd, handler)

      Thread.sleep(300000)

    } finally {
      client.close()
      println("Disconnected")
    }
  }

//calculating trade information-----------------------------------------------------------

  //this is because previous one didn't do their work properly...
  private def settleTrade(t: Trade): Trade = {

    val commission = t.quantity * t.price * 0.003   // 0.3%
    val tax        = t.quantity * t.price * 0.005   // 0.5%
    val gross      = t.quantity * t.price
    val net        = gross - commission - tax

    t.copy(
      price = t.price,                     // final price (or override)
      broker_id = "BRK-101",
      commission = commission,
      tax = tax,
      gross_amount = gross,
      net_amount = net,
      received_time = Instant.now().toString,
      status = "SETTLED"
    )

  }

  //update settled trades-----------------------------------------------

  private def updateSettlementInDB(conn: java.sql.Connection, t: Trade): Unit = {

    val settledTradeInDb = SQL.SETTLE_QUERY

    val ps = conn.prepareStatement(settledTradeInDb)

    ps.setBigDecimal(1, t.price.bigDecimal)
    ps.setString(2, t.broker_id)
    ps.setBigDecimal(3, t.commission.bigDecimal)
    ps.setBigDecimal(4, t.tax.bigDecimal)
    ps.setBigDecimal(5, t.gross_amount.bigDecimal)
    ps.setBigDecimal(6, t.net_amount.bigDecimal)
    ps.setString(7, t.received_time)
    ps.setString(8, t.status)
    ps.setInt(9, t.trade_id)

    ps.executeUpdate()
    ps.close()
  }
}
