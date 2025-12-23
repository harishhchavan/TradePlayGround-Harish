package com.trade.zExperiments

import java.net.Socket
import scala.io.{Source, StdIn}

object SimpleSubscriber {
  def main(args: Array[String]): Unit = {
    println("=== Simple Data Subscriber ===")
    println("Enter publisher IP (default: 192.168.20.122): ")
    val ip = StdIn.readLine().trim match {
      case "" => "192.168.20.249"
      case userIp => userIp
    }
    println(s"Connecting to $ip:9007...")
    try {
      val socket = new Socket(ip, 9007)
      println("Connected! Waiting for data...")
      println("=" * 50)
      // Listen for incoming data
      val reader = Source.fromInputStream(socket.getInputStream)
      reader.getLines().foreach { line =>
        // You can EDIT here to process data differently
        if (line.startsWith("DATA_START")) {
          println("\n" + "=" * 50)
          println("NEW DATA RECEIVED:")
          println("=" * 50)
        } else if (line.startsWith("DATA_END")) {
          println("=" * 50)
          println("End of transmission")
          println("=" * 50 + "\n")
        } else {
          println(line)
        }
      }
    } catch {
      case e: Exception =>
        println(s"Connection failed: ${e.getMessage}")
        println("Make sure the publisher is running on 192.168.20.249:9007")
    }
  }
}
