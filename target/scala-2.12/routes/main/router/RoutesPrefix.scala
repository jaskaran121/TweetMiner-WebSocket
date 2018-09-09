// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/JASKARN SINGH/Desktop/Assignement-2Updated/Soen6441Assignment2/conf/routes
// @DATE:Mon Aug 13 20:26:06 EDT 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
