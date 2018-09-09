// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/JASKARN SINGH/Desktop/Assignement-2Updated/Soen6441Assignment2/conf/routes
// @DATE:Mon Aug 13 20:26:06 EDT 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_0: controllers.HomeController,
  // @LINE:17
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_0: controllers.HomeController,
    // @LINE:17
    Assets_1: controllers.Assets
  ) = this(errorHandler, HomeController_0, Assets_1, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ws""", """controllers.HomeController.ws"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """user""", """controllers.HomeController.showUser(screenName:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """location""", """controllers.HomeController.getLocation(latitude:Double, longitude:Double)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hashtag""", """controllers.HomeController.searchHashTag(hashtag:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """wordcount""", """controllers.HomeController.createWordCount(keyword:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_HomeController_ws1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ws")))
  )
  private[this] lazy val controllers_HomeController_ws1_invoker = createInvoker(
    HomeController_0.ws,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "ws",
      Nil,
      "GET",
      this.prefix + """ws""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_HomeController_showUser2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("user")))
  )
  private[this] lazy val controllers_HomeController_showUser2_invoker = createInvoker(
    HomeController_0.showUser(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "showUser",
      Seq(classOf[String]),
      "GET",
      this.prefix + """user""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_HomeController_getLocation3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("location")))
  )
  private[this] lazy val controllers_HomeController_getLocation3_invoker = createInvoker(
    HomeController_0.getLocation(fakeValue[Double], fakeValue[Double]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getLocation",
      Seq(classOf[Double], classOf[Double]),
      "GET",
      this.prefix + """location""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_HomeController_searchHashTag4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hashtag")))
  )
  private[this] lazy val controllers_HomeController_searchHashTag4_invoker = createInvoker(
    HomeController_0.searchHashTag(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "searchHashTag",
      Seq(classOf[String]),
      "GET",
      this.prefix + """hashtag""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_HomeController_createWordCount5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("wordcount")))
  )
  private[this] lazy val controllers_HomeController_createWordCount5_invoker = createInvoker(
    HomeController_0.createWordCount(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "createWordCount",
      Seq(classOf[String]),
      "GET",
      this.prefix + """wordcount""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Assets_at6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at6_invoker = createInvoker(
    Assets_1.at(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index)
      }
  
    // @LINE:7
    case controllers_HomeController_ws1_route(params@_) =>
      call { 
        controllers_HomeController_ws1_invoker.call(HomeController_0.ws)
      }
  
    // @LINE:9
    case controllers_HomeController_showUser2_route(params@_) =>
      call(params.fromQuery[String]("screenName", None)) { (screenName) =>
        controllers_HomeController_showUser2_invoker.call(HomeController_0.showUser(screenName))
      }
  
    // @LINE:10
    case controllers_HomeController_getLocation3_route(params@_) =>
      call(params.fromQuery[Double]("latitude", None), params.fromQuery[Double]("longitude", None)) { (latitude, longitude) =>
        controllers_HomeController_getLocation3_invoker.call(HomeController_0.getLocation(latitude, longitude))
      }
  
    // @LINE:11
    case controllers_HomeController_searchHashTag4_route(params@_) =>
      call(params.fromQuery[String]("hashtag", None)) { (hashtag) =>
        controllers_HomeController_searchHashTag4_invoker.call(HomeController_0.searchHashTag(hashtag))
      }
  
    // @LINE:12
    case controllers_HomeController_createWordCount5_route(params@_) =>
      call(params.fromQuery[String]("keyword", None)) { (keyword) =>
        controllers_HomeController_createWordCount5_invoker.call(HomeController_0.createWordCount(keyword))
      }
  
    // @LINE:17
    case controllers_Assets_at6_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at6_invoker.call(Assets_1.at(path, file))
      }
  }
}
