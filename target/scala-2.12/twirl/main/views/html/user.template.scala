
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
/*2.2*/import java.util.concurrent.CompletableFuture;

object user extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[CompletableFuture[twitter4j.User],CompletableFuture[List[twitter4j.Status]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(user:CompletableFuture[twitter4j.User],recentPost:CompletableFuture[List[twitter4j.Status]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
"""),format.raw/*5.1*/("""<!-- Author Gagan -->
    <html>
        <head>
            <title>User</title>
          
        </head>

        <body>
        
      
        <h2>User Profile</h2>
        <img src=""""),_display_(/*16.20*/user/*16.24*/.get.getBiggerProfileImageURLHttps()),format.raw/*16.60*/("""">
		<p><b>Following:-</b>"""),_display_(/*17.25*/user/*17.29*/.get.getFriendsCount),format.raw/*17.49*/("""</p>
		<p><b>Followers:-</b>"""),_display_(/*18.25*/user/*18.29*/.get.getFollowersCount),format.raw/*18.51*/("""</p>
		<p><b>Likes:-</b>"""),_display_(/*19.21*/user/*19.25*/.get.getFavouritesCount),format.raw/*19.48*/("""</p>
		<p><b>No of Tweets:-</b>"""),_display_(/*20.28*/user/*20.32*/.get.getStatusesCount),format.raw/*20.53*/("""</p>
		<a target="_blank" href="https://twitter.com/"""),_display_(/*21.49*/user/*21.53*/.get.getScreenName),format.raw/*21.71*/("""" >"""),_display_(/*21.75*/user/*21.79*/.get.getName),format.raw/*21.91*/("""</a>
	

        
	<h2>Recent Posts:</h2>
	
	"""),_display_(/*27.3*/if(!recentPost.get.isEmpty)/*27.30*/{_display_(Seq[Any](format.raw/*27.31*/("""
		"""),_display_(/*28.4*/for(status <- recentPost.get) yield /*28.33*/{_display_(Seq[Any](format.raw/*28.34*/("""
			"""),format.raw/*29.4*/("""<p><b>"""),_display_(/*29.11*/status/*29.17*/.getUser().getScreenName()),format.raw/*29.43*/("""</b></p>
		     <p> """),_display_(/*30.13*/status/*30.19*/.getText),format.raw/*30.27*/("""</p>
          <p>------------------------------------------------------------------------------------------------------------------------------------------------</P>		
		""")))}),format.raw/*32.4*/("""
	""")))}),format.raw/*33.3*/("""
 """),format.raw/*34.2*/("""</body>"""))
      }
    }
  }

  def render(user:CompletableFuture[twitter4j.User],recentPost:CompletableFuture[List[twitter4j.Status]]): play.twirl.api.HtmlFormat.Appendable = apply(user,recentPost)

  def f:((CompletableFuture[twitter4j.User],CompletableFuture[List[twitter4j.Status]]) => play.twirl.api.HtmlFormat.Appendable) = (user,recentPost) => apply(user,recentPost)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Aug 13 20:26:07 EDT 2018
                  SOURCE: C:/Users/JASKARN SINGH/Desktop/Assignement-2Updated/Soen6441Assignment2/app/views/user.scala.html
                  HASH: e9f029fdd2f9d756c2b2ef0d3aeb75ad9d267349
                  MATRIX: 651->2|1070->50|1257->144|1284->145|1499->333|1512->337|1569->373|1623->400|1636->404|1677->424|1733->453|1746->457|1789->479|1841->504|1854->508|1898->531|1957->563|1970->567|2012->588|2092->641|2105->645|2144->663|2175->667|2188->671|2221->683|2292->728|2328->755|2367->756|2397->760|2442->789|2481->790|2512->794|2546->801|2561->807|2608->833|2656->854|2671->860|2700->868|2902->1040|2935->1043|2964->1045
                  LINES: 24->2|29->3|34->4|35->5|46->16|46->16|46->16|47->17|47->17|47->17|48->18|48->18|48->18|49->19|49->19|49->19|50->20|50->20|50->20|51->21|51->21|51->21|51->21|51->21|51->21|57->27|57->27|57->27|58->28|58->28|58->28|59->29|59->29|59->29|59->29|60->30|60->30|60->30|62->32|63->33|64->34
                  -- GENERATED --
              */
          