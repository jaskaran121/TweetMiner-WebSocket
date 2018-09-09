// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/JASKARN SINGH/Desktop/Assignement-2Updated/Soen6441Assignment2/conf/routes
// @DATE:Mon Aug 13 20:26:06 EDT 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def searchHashTag(hashtag:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "hashtag" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("hashtag", hashtag)))))
    }
  
    // @LINE:12
    def createWordCount(keyword:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "wordcount" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("keyword", keyword)))))
    }
  
    // @LINE:7
    def ws(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "ws")
    }
  
    // @LINE:10
    def getLocation(latitude:Double, longitude:Double): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "location" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[Double]].unbind("latitude", latitude)), Some(implicitly[play.api.mvc.QueryStringBindable[Double]].unbind("longitude", longitude)))))
    }
  
    // @LINE:9
    def showUser(screenName:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "user" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("screenName", screenName)))))
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:17
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def at(file:String): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
    }
  
  }


}
