package com.trade.common

case class Trade(
                  trade_id: String,
                  order_id: String,
                  execution_id: String,
                  symbol: String,
                  side: String,
                  quantity: String,
                  price: String,
                  trade_time: String,
                  venue: String,
                  currency: String,
                  account_id: String,
                  broker_id: String,
                  strategy_id: String,
                  exec_type: String,
                  liquidity_flag: String,
                  commission: String,
                  tax: String,
                  gross_amount: String,
                  net_amount: String,
                  received_time: String,
                  status: String
                )

