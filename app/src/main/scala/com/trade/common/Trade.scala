package com.trade.common

import play.api.libs.json.{Format, Json}


case class Trade(
                  trade_id: Int,
                  order_id: String,
                  execution_id: String,
                  symbol: String,
                  side: String,
                  quantity: BigDecimal,
                  price: BigDecimal,
                  trade_time: String,
                  venue: String,
                  currency: String,
                  account_id: String,
                  broker_id: String,
                  commission: BigDecimal,
                  tax: BigDecimal,
                  gross_amount: BigDecimal,
                  net_amount: BigDecimal,
                  received_time: String,
                  status: String
                )

//case class Trade(
//                id: Int,
//                symbol: String,
//                qty: Int,
//                enriched: Boolean = false,
//                price: Option[Double] = None
//                )

object Trade {

  implicit val tradeFormat: Format[Trade] = Json.format[Trade]
}
