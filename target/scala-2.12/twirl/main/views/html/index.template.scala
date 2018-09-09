
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[play.mvc.Http.Request,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(request:play.mvc.Http.Request):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        """),format.raw/*6.62*/("""
        """),format.raw/*7.9*/("""<title>Search Tweets</title>
        
        <link rel="stylesheet" media="screen" href=""""),_display_(/*9.54*/routes/*9.60*/.Assets.at("bootstrap-3.3.7-dist/css/bootstrap.min.css")),format.raw/*9.116*/("""">
        
    </head>
    <body >
          <nav class = "navbar navbar-default">
	          <div class="navbar-header">
	      		<p class="navbar-brand" href="#">Tweet Miner</P>
	   		 </div>
				<div class="container">
					<form class="navbar-form navbar-left needs-validation" role="search" id ="formforsearchKeyword">
						 <div class="form-group">
						 <input type="text" class="form-control" placeholder="Search" id="searchTerm" required>
						 </div>
					 	<button type="submit" class="btn btn-default">Search</button>
					</form>  
				 </div>
			</nav>
			

	
	<div id="divForWebsocket" data-ws-url=""""),_display_(/*29.42*/routes/*29.48*/.HomeController.ws.webSocketURL(request)),format.raw/*29.88*/("""">
	    <div id="data"> </div>
  	</div>
		
        <div class="newtweet" style="display:none;">
            <h4 class="" id="title"></h4>
	  		<div class="">
				<span class="" id="">hello</span>
				
				<p>--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>
	  		</div>
		</div>
<script type='text/javascript' src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<script src=""""),_display_(/*42.15*/routes/*42.21*/.Assets.at("javascripts/displayTweets.js")),format.raw/*42.63*/("""" type="text/javascript"></script>
      		
    </body>
    
</html>
 
  

	

"""))
      }
    }
  }

  def render(request:play.mvc.Http.Request): play.twirl.api.HtmlFormat.Appendable = apply(request)

  def f:((play.mvc.Http.Request) => play.twirl.api.HtmlFormat.Appendable) = (request) => apply(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Aug 13 20:26:07 EDT 2018
                  SOURCE: C:/Users/JASKARN SINGH/Desktop/Assignement-2Updated/Soen6441Assignment2/app/views/index.scala.html
                  HASH: 33661319e0f5f92549f575196f157a34cd066ad9
                  MATRIX: 963->1|1088->33|1115->34|1194->139|1229->148|1346->239|1360->245|1437->301|2083->920|2098->926|2159->966|2774->1554|2789->1560|2852->1602
                  LINES: 28->1|33->2|34->3|37->6|38->7|40->9|40->9|40->9|60->29|60->29|60->29|73->42|73->42|73->42
                  -- GENERATED --
              */
          