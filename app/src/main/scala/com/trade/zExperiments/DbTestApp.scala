package com.trade.zExperiments
import com.trade.config.DbConfig
import java.sql.DriverManager

//Srimanth System Details

/*
database - Amps
user - sa
pss - Srimanth@9
 */


//checking shared Database & it's System
object DbTestApp extends App{

  val config = DbConfig.dbConfig

  private val driver = config.dbDriver
  private val url = config.dbUrl
  private val user = config.dbUser
  private val password = config.dbPassword

  val conn = DriverManager.getConnection(url, user, password )

  try {
    // Load driver
    Class.forName(driver)

    // Try connection
    val connection = DriverManager.getConnection(url, user, password)

    println("SUCCESS: Connected to remote database!")
    println("DB URL: " + url)

    connection.close()
    println("Connection Closed Successfully.")
  } catch {
    case e: Exception =>
      println("FAILED to connect to database")
      e.printStackTrace()
  }
}


