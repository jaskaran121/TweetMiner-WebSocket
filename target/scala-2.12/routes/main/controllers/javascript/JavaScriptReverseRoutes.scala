// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/JASKARN SINGH/Desktop/Assignement-2Updated/Soen6441Assignment2/conf/routes
// @DATE:Mon Aug 13 20:26:06 EDT 2018

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def searchHashTag: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.searchHashTag",
      """
        function(hashtag0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "hashtag" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("hashtag", hashtag0)])})
        }
      """
    )
  
    // @LINE:12
    def createWordCount: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.createWordCount",
      """
        function(keyword0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "wordcount" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("keyword", keyword0)])})
        }
      """
    )
  
    // @LINE:7
    def ws: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.ws",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ws"})
        }
      """
    )
  
    // @LINE:10
    def getLocation: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getLocation",
      """
        function(latitude0,longitude1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "location" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[Double]].javascriptUnbind + """)("latitude", latitude0), (""" + implicitly[play.api.mvc.QueryStringBindable[Double]].javascriptUnbind + """)("longitude", longitude1)])})
        }
      """
    )
  
    // @LINE:9
    def showUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.showUser",
      """
        function(screenName0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "user" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("screenName", screenName0)])})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:17
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def at: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.at",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
