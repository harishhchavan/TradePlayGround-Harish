package com.trade.zExperiments

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext
import scala.io.StdIn
import scala.util.{Failure, Success}

object Sample extends App {
  implicit val system: ActorSystem = ActorSystem("sample-frontend")

//  implicit val executionContext : ExecutionContext = system.dispatcher

  val route =
//    path("") {
//      get {
//        val html = try {
//          val source = scala.io.Source.fromFile("app/public/index.html")
//          try {
//            source.mkString
//          }finally{
//            source.close()
//          }
//        }catch {
//          case e : Exception =>
//            """
//               <html>
//                  <body>
//                    <p>HTML file not found</p>
//                    <p>Create file in this path - app/public/index.html</p>
//                  </body>
//               </html>
//            """
//        }
//        complete(HttpEntity(ContentTypes.`text/html(UTF-8)` ,html))
//      }
//    }~
      path("submit") {
        post {
          formFields(Symbol("name") , Symbol("password")) {
            (name , password) =>
              println("="*60)
              println("User Registered")
              println(s"Name : $name")
              println(s"Password : $password")
              println("="*60)

//              complete(HttpEntity(ContentTypes.`text/html(UTF-8)` ,
//                s"""
//                   <html>
//                      <body>
//                        <h2>USER REGISTERED</h2>
//                        <p><strong>Name : $name</strong></p>
//                        <p><strong>Password : $password</strong></p>
//                        <a href = "/">GO BACK</a>
//                      </body>
//                   </html>
//                """
//              ))
              complete("done")
          }
        }
      }

  val bindingFuture = Http()
    .newServerAt("localhost" , 9000)
    .bindFlow(route)

//  println("Server is started at http://localhost:9000")
//  println("Press ENTER to end...")
//
//
//  bindingFuture.onComplete {
//    case Success(binding) =>
//      val address = binding.localAddress
//
//      println(s"Server online at http://${address.getHostName}:${address.getPort}")
//
//    case Failure(exception) =>
//      println(s"Failed to create HTTP Server : ${exception.getMessage}")
//      system.terminate()
//  }
//
//  StdIn.readLine()
//
//  bindingFuture
//    .flatMap(_.unbind())
//    .onComplete{ _ =>
//      println("Server stopped")
//      system.terminate()
//    }
}
