package com.trade.common

import play.api.libs.json.{Format, Json}

//case class Trade(
//                  trade_id: String,
//                  order_id: String,
//                  execution_id: String,
//                  symbol: String,
//                  side: String,
//                  quantity: String,
//                  price: String,
//                  trade_time: String,
//                  venue: String,
//                  currency: String,
//                  account_id: String,
//                  broker_id: String,
//                  strategy_id: String,
//                  exec_type: String,
//                  liquidity_flag: String,
//                  commission: String,
//                  tax: String,
//                  gross_amount: String,
//                  net_amount: String,
//                  received_time: String,
//                  status: String
//                )


case class Trade(
                id: Int,
                symbol: String,
                qty: Int,
                enriched: Boolean = false,
                price: Option[Double] = None
                )

object Trade {

  implicit val tradeFormat: Format[Trade] = Json.format[Trade]
}
